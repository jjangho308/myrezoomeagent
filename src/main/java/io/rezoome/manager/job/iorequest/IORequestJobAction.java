package io.rezoome.manager.job.iorequest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.rezoome.constants.ErrorCodeConstants;
import io.rezoome.entity.RzmRsltEntity;
import io.rezoome.exception.ServiceException;
import io.rezoome.external.opic.entity.OpicResultEntity;
import io.rezoome.lib.json.JSON;
import io.rezoome.manager.database.convert.DBConverter;
import io.rezoome.manager.database.dao.DaoManagerImpl;
import io.rezoome.manager.database.entity.DBRsltEntity;
import io.rezoome.manager.job.JobRsltEntity;
import io.rezoome.manager.job.entity.AbstractJob;
import io.rezoome.manager.mapper.Mapper;
import io.rezoome.manager.mapper.MapperEntity;
import io.rezoome.manager.network.entity.RequestPacket;
import io.rezoome.manager.network.entity.request.RequestArgsEntity;
import io.rezoome.manager.network.entity.request.RequestPacketEntity;
import io.rezoome.manager.network.entity.request.RequestSearchRecordsEntity;
import io.rezoome.manager.network.entity.response.ResponsePacketEntity;
import io.rezoome.manager.provider.ManagerProvider;

public class IORequestJobAction extends AbstractJob<IORequestJobEntity> {

  private final Logger LOG = LoggerFactory.getLogger("AGENT_LOG");

  private enum STATUS {
    USER_EXIST, USER_NOT_EXIST, REQUIRED_KEY
  }

  private STATUS status;

  public IORequestJobAction() {
    super();
  }

  @Override
  protected JobRsltEntity processInternal(IORequestJobEntity entity) {
    try {
      ResponsePacketEntity responseEntity = null;

      // database
      List<DBRsltEntity> dbResultEntityList = getDBData(entity);
      LOG.debug("dbResultEntityList {}", dbResultEntityList);

      // convert
      RequestPacketEntity requestEntity = new RequestPacketEntity();
      convertRequestPacket(entity, dbResultEntityList, requestEntity);

      // http
      RequestPacket packet = new RequestPacket(entity.getSid(), JSON.toJson(requestEntity));
      responseEntity = ManagerProvider.network().request(packet);

      if (responseEntity == null) {
        throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNABLE_TO_GET_CORRECT_RESPONSE_CODE);
      }
    } catch (Exception e) {
      throw new ServiceException(e.getMessage(), e);
    }
    return null;
  }

  private void convertRequestPacket(IORequestJobEntity entity, List<DBRsltEntity> dbResultEntityList, RequestPacketEntity requestEntity) {
    // TODO Auto-generated method stub
    RzmRsltEntity rzmResultEntity = new RzmRsltEntity();

    rzmResultEntity.setOrgCode("ORG001");
    rzmResultEntity.setEncKey("");
    rzmResultEntity.setEncIv("");

    if (status == STATUS.USER_NOT_EXIST) {
      requestEntity.setCode("USER_NOT_EXIST");
    } else if (status == STATUS.REQUIRED_KEY) {
      requestEntity.setCode("REQUIRED_KEY");
    } else if (dbResultEntityList.size() == 0) {
      requestEntity.setCode("USER_EXIST_BUT_DATA_EMPTY");
    } else {
      String aesKey = ManagerProvider.crypto().generateAES();
      String iv = ManagerProvider.crypto().generateIV();
      String encKey = ManagerProvider.crypto().encryptRSA(aesKey, entity.getPkey());
      String encIv = ManagerProvider.crypto().encryptRSA(iv, entity.getPkey());

      MapperEntity mapperResultEntity;
      Mapper mapper = ManagerProvider.mapper().getMapper();
      List<RequestArgsEntity> records = new ArrayList<RequestArgsEntity>();
      RequestSearchRecordsEntity record = null;

      for (DBRsltEntity dbEntity : dbResultEntityList) {
        mapperResultEntity = mapper.convert(dbEntity);
        record = new RequestSearchRecordsEntity();
        String encData = ManagerProvider.crypto().encryptAES(mapperResultEntity.toString(), aesKey, iv);
        String hashData = ManagerProvider.crypto().hash(mapperResultEntity.toString());
        record.setEncData(encData);
        record.setHashData(hashData);
        record.setStored(isStoredHashData(entity, hashData));
        records.add(record);
      }

      rzmResultEntity.setEncKey(encKey);
      rzmResultEntity.setEncIv(encIv);
      rzmResultEntity.setRecords(records);
      requestEntity.setCode("USER_EXIST_AND_DATA_EXIST");
    }
    requestEntity.setArgs(rzmResultEntity);
    requestEntity.setCmd(entity.getCmd());
    requestEntity.setMid(entity.getMid());
  }

  private List<DBRsltEntity> getDBData(IORequestJobEntity entity) throws ServiceException {

    DBConverter converter = ManagerProvider.database().getConvertManager().getConverter();
    DaoManagerImpl daoMgr = ManagerProvider.database().getDaoManager();
    List<DBRsltEntity> dbResultEntityList = null;

    // 1. 사용자 확인
    // 2. 기관정보 데이터 확인
    try {
      if (true) { // CI 가 있는 경우에만 수행
        int userCount = daoMgr.getDao().getUserCountByCI(converter.convert(entity));

        if (userCount == 1) {
          dbResultEntityList = daoMgr.getDao().getCertRecords(converter.convert(entity));
          status = STATUS.USER_EXIST;
          return dbResultEntityList;
        } else if (userCount == 0) {
          status = STATUS.USER_NOT_EXIST;
          return dbResultEntityList;
        }
      }
      // 결과가 없는 경우에는 이름, 생년월일, 성별로 다시 확인
      // 기관에 CI 가 없을 경우에는 바로 이름, 생년월일, 성별 조회부터 시작
      dbResultEntityList = daoMgr.getDao().getUserRecordByName(converter.convert(entity));
      if (dbResultEntityList.size() >= 1) {
        // 해당 결과가 1개 이상인 경우 전화번호로 다시 확인
        for (DBRsltEntity userEntity : dbResultEntityList) {
          // 전화번호가 맞으면 해당 정보로 기관 정보 다시 요청
          OpicResultEntity user = (OpicResultEntity) userEntity;
          if (entity.getPhone() != null && entity.getPhone().equals(user.getPhone())) {
            dbResultEntityList = daoMgr.getDao().getCertRecords(converter.convert(entity));
            status = STATUS.USER_EXIST;
            return dbResultEntityList;
          }
        }
        // 전화번호 조회 결과가 없으면 필수정보 요청
        status = STATUS.REQUIRED_KEY;
      } else {
        // 이름 검색 결과가 없을 때는 가입된 사용자가 없는것으로 판단.
        status = STATUS.USER_NOT_EXIST;
        dbResultEntityList = null;
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNABLE_TO_GET_DB_DATA, e);
    }

    return dbResultEntityList;
  }

  private String isStoredHashData(IORequestJobEntity entity, String hashData) {
    // TODO entity.getHashList 로 유도
    List<String> hashList = new ArrayList<String>(); //
    for (String hash : hashList) {
      if (hash.equals(hashData)) {
        return "Y";
      }
    }
    return "N";
  }



  // private void convertRequestPacket(IORequestJobEntity entity, List<DBRsltEntity>
  // dbResultEntityList, RequestPacketEntity requestEntity) {
  // // TODO Auto-generated method stub
  //
  // RzmRsltEntity rzmResultEntity = new RzmRsltEntity();
  // MapperEntity mapperResultEntity;
  // Mapper mapper = ManagerProvider.mapper().getMapper();
  //
  // rzmResultEntity.setDataEnc("");
  // rzmResultEntity.setKeyEnc("");
  // rzmResultEntity.setDataHash("");
  //
  // if (dbResultEntityList == null) {
  // requestEntity.setCode("EMPTY");
  // } else if (dbResultEntityList.size() > 1) {
  // requestEntity.setCode("REQUIRED KEY");
  // } else if (dbResultEntityList.size() == 1) {
  // mapperResultEntity = mapper.convert(dbResultEntityList.get(0));
  // // initially generate AES key
  // String aesKey = ManagerProvider.crypto().generateAES();
  // String iv = ManagerProvider.crypto().generateIV();
  //
  // // String keyEnc = ManagerProvider.crypto().encryptRSA(aesKey, entity.getPkey());
  // String keyEnc = "ENCKEY";
  // String dataEnc = ManagerProvider.crypto().encryptAES(mapperResultEntity.toString(), aesKey,
  // iv);
  // String dataHash = ManagerProvider.crypto().hash(mapperResultEntity.toString());
  //
  // rzmResultEntity.setDataEnc(dataEnc);
  // rzmResultEntity.setKeyEnc(keyEnc);
  // rzmResultEntity.setDataHash(dataHash);
  // requestEntity.setCode("OK");
  // }
  // requestEntity.setArgs(rzmResultEntity);
  // requestEntity.setCmd(entity.getCmd());
  // requestEntity.setMid(entity.getMid());
  // }
  //
  // private List<DBRsltEntity> getDBData(IORequestJobEntity entity) throws ServiceException {
  //
  // DBConverter converter = ManagerProvider.database().getConvertManager().getConverter();
  // DaoManagerImpl daoMgr = ManagerProvider.database().getDaoManager();
  // List<DBRsltEntity> dbResultEntityList = null;
  //
  // try {
  // dbResultEntityList = daoMgr.getDao().getRecords(converter.convert(entity));
  // } catch (IOException e) {
  // // TODO Auto-generated catch block
  // throw new ServiceException("database error", e);
  // }
  //
  // for (DBRsltEntity record : dbResultEntityList) {
  // LOG.debug("db record {}", record);
  // }
  //
  // return dbResultEntityList;
  // }
}
