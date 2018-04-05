package io.rezoome.manager.job.iorequest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.rezoome.constants.Constants;
import io.rezoome.constants.ErrorCodeConstants;
import io.rezoome.exception.ServiceException;
import io.rezoome.external.opic.entity.OpicResultEntity;
import io.rezoome.lib.json.JSON;
import io.rezoome.manager.database.convert.DBConverter;
import io.rezoome.manager.database.dao.DaoManagerImpl;
import io.rezoome.manager.database.entity.DBRsltEntity;
import io.rezoome.manager.job.JobRsltEntity;
import io.rezoome.manager.job.entity.AbstractJob;
import io.rezoome.manager.mapper.Mapper;
import io.rezoome.manager.network.entity.RequestPacket;
import io.rezoome.manager.network.entity.request.RequestArgsEntity;
import io.rezoome.manager.network.entity.request.RequestPacketEntity;
import io.rezoome.manager.network.entity.request.RequestSearchArgsEntity;
import io.rezoome.manager.network.entity.request.RequestSearchRecordsEntity;
import io.rezoome.manager.network.entity.response.ResponsePacketEntity;
import io.rezoome.manager.provider.ManagerProvider;
import io.rezoome.manager.pushcommand.entity.search.HashRecordEntity;

public class IORequestJobAction extends AbstractJob<IORequestJobEntity> {

  private final Logger LOG = LoggerFactory.getLogger(Constants.AGENT_LOG);

  private enum STATUS {
    USER_EXIST, USER_NOT_EXIST, REQUIRE_KEY
  }

  private STATUS status;

  public IORequestJobAction() {
    super();
  }

  @Override
  protected JobRsltEntity processInternal(IORequestJobEntity entity) throws ServiceException {
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

  private void convertRequestPacket(IORequestJobEntity entity, List<DBRsltEntity> dbResultEntityList, RequestPacketEntity requestEntity) throws ServiceException {
    // TODO Auto-generated method stub
    RequestSearchArgsEntity searchRecordEntity = new RequestSearchArgsEntity();

    searchRecordEntity.setOrgCode("orgcode");
    searchRecordEntity.setKey("");
    searchRecordEntity.setIv("");

    if (status == STATUS.USER_NOT_EXIST) {
      requestEntity.setCode(Constants.RESULT_CODE_USER_NOT_EXIST);
    } else if (status == STATUS.REQUIRE_KEY) {
      requestEntity.setCode(Constants.RESULT_CODE_NEED_TO_REQUIRE_KEY);
    } else if (dbResultEntityList.size() == 0) {
      requestEntity.setCode(Constants.RESULT_CODE_DATA_IS_EMPTY);
    } else {
      String aesKey = ManagerProvider.crypto().generateAES();
      String iv = ManagerProvider.crypto().generateIV();
      String encKey = ManagerProvider.crypto().encryptRSA(aesKey, entity.getPkey());
      String encIv = ManagerProvider.crypto().encryptRSA(iv, entity.getPkey());

      Mapper mapper = ManagerProvider.mapper().getMapper();
      List<RequestArgsEntity> records = new ArrayList<RequestArgsEntity>();
      RequestSearchRecordsEntity record = null;

      String dbEntityString = null;
      for (DBRsltEntity dbEntity : dbResultEntityList) {
        dbEntityString = JSON.toJson(mapper.convert(dbEntity));
        record = new RequestSearchRecordsEntity();
        String encData = ManagerProvider.crypto().encryptAES(dbEntityString, aesKey, iv);
        String hashData = ManagerProvider.crypto().hash(dbEntityString);
        record.setData(encData);
        record.setHash(hashData);
        record.setCertcode("certcode");
        record.setStored(isStoredHashData(entity, hashData));
        records.add(record);
      }
      searchRecordEntity.setKey(encKey);
      searchRecordEntity.setIv(encIv);
      searchRecordEntity.setRecords(records);
      requestEntity.setCode(Constants.RESULT_CODE_SUCCESS);
    }
    requestEntity.setArgs(searchRecordEntity);
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
        System.out.println(converter.convert(entity));
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
        status = STATUS.REQUIRE_KEY;
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

  private String isStoredHashData(IORequestJobEntity entity, String hashData) throws ServiceException {
    // TODO entity.getHashList 로 유도
    List<HashRecordEntity> hashList = new ArrayList<HashRecordEntity>(); //
    for (HashRecordEntity hashEntity : hashList) {
      if (hashEntity.getHashed() != null &&
          hashEntity.getHashed().equals(hashData)) {
        return "Y";
      }
    }
    return "N";
  }
}
