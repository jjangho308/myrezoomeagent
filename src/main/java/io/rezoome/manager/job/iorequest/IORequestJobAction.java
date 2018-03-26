package io.rezoome.manager.job.iorequest;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.rezoome.entity.RzmRsltEntity;
import io.rezoome.exception.ServiceException;
import io.rezoome.lib.json.JSON;
import io.rezoome.manager.database.convert.DBConverter;
import io.rezoome.manager.database.dao.DaoManagerImpl;
import io.rezoome.manager.database.entity.DBRsltEntity;
import io.rezoome.manager.job.JobRsltEntity;
import io.rezoome.manager.job.entity.AbstractJob;
import io.rezoome.manager.mapper.Mapper;
import io.rezoome.manager.mapper.MapperEntity;
import io.rezoome.manager.network.entity.RequestPacket;
import io.rezoome.manager.network.entity.request.RequestPacketEntity;
import io.rezoome.manager.network.entity.response.ResponsePacketEntity;
import io.rezoome.manager.provider.ManagerProvider;

public class IORequestJobAction extends AbstractJob<IORequestJobEntity> {

  private final Logger LOG = LoggerFactory.getLogger("AGENT_LOG");

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
      LOG.debug("requestEntity {}", requestEntity.toString());

      // http
      RequestPacket packet = new RequestPacket(entity.getSid(), JSON.toJson(requestEntity));
      responseEntity = ManagerProvider.network().request(packet);
      if (responseEntity == null) {
        LOG.error("fail to connect server");
        throw new ServiceException("fail to connect server 3");
      }
    } catch (Exception e) {
      LOG.error("error {}", e);
      throw new ServiceException(e.getMessage(), e);
    }
    return null;
  }

  private void convertRequestPacket(IORequestJobEntity entity, List<DBRsltEntity> dbResultEntityList, RequestPacketEntity requestEntity) {
    // TODO Auto-generated method stub

    RzmRsltEntity rzmResultEntity = new RzmRsltEntity();
    MapperEntity mapperResultEntity;
    Mapper mapper = ManagerProvider.mapper().getMapper();

    rzmResultEntity.setDataEnc("");
    rzmResultEntity.setKeyEnc("");
    rzmResultEntity.setDataHash("");

    if (dbResultEntityList == null) {
      requestEntity.setCode("EMPTY");
    } else if (dbResultEntityList.size() > 1) {
      requestEntity.setCode("REQUIRED KEY");
    } else if (dbResultEntityList.size() == 1) {
      mapperResultEntity = mapper.convert(dbResultEntityList.get(0));
      // initially generate AES key
      String aesKey = ManagerProvider.crypto().generateAES();
      String iv = ManagerProvider.crypto().generateIV();

      // String keyEnc = ManagerProvider.crypto().encryptRSA(aesKey, entity.getPkey());
      String keyEnc = "ENCKEY";
      String dataEnc = ManagerProvider.crypto().encryptAES(mapperResultEntity.toString(), aesKey, iv);
      String dataHash = ManagerProvider.crypto().hash(mapperResultEntity.toString());

      rzmResultEntity.setDataEnc(dataEnc);
      rzmResultEntity.setKeyEnc(keyEnc);
      rzmResultEntity.setDataHash(dataHash);
      requestEntity.setCode("OK");
    }
    requestEntity.setArgs(rzmResultEntity);
    requestEntity.setCmd(entity.getCmd());
    requestEntity.setMid(entity.getMid());
  }

  private List<DBRsltEntity> getDBData(IORequestJobEntity entity) throws ServiceException {

    DBConverter converter = ManagerProvider.database().getConvertManager().getConverter();
    DaoManagerImpl daoMgr = ManagerProvider.database().getDaoManager();
    List<DBRsltEntity> dbResultEntityList = null;

    try {
      dbResultEntityList = daoMgr.getDao().getRecords(converter.convert(entity));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      throw new ServiceException("database error", e);
    }

    for (DBRsltEntity record : dbResultEntityList) {
      LOG.debug("db record {}", record);
    }

    return dbResultEntityList;

    // // step1. select * from tbl where ci
    // dbRsltEntity = daoMgr.getDao().getRecordByCi(dbEntity);
    //
    // if (dbRsltEntity == null) {
    // // step2. select * from tbl where name, birthday, gender
    // dbRsltEntityList = daoMgr.getDao().getRecordsByName(dbEntity);
    //
    // if (dbRsltEntityList.size() >= 1) {
    // // step3. select * from tbl where phone, email
    // dbRsltEntityList = daoMgr.getDao().getRecordsByPhone(dbEntity);
    //
    // if (dbRsltEntityList.size() == 1) {
    // dbRsltEntity = dbRsltEntityList.get(0);
    // }
    // }
    // }
  }

}
