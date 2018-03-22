package io.rezoome.manager.job.iorequest;

import java.io.IOException;
import java.net.HttpURLConnection;
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

    ResponsePacketEntity responseEntity = null;

    try {
      // database
      List<DBRsltEntity> dbResultEntityList = getDBData(entity);
      LOG.debug("dbResultEntityList {}", dbResultEntityList);

      // convert
      RequestPacketEntity requestEntity = new RequestPacketEntity();
      convertRequestPacket(entity, dbResultEntityList, requestEntity);
      LOG.debug("requestEntity {}", requestEntity.toString());

      // http
      RequestPacket packet = new RequestPacket(entity.getSid(), JSON.toJson(requestEntity));

      try {
        responseEntity = ManagerProvider.network().request(packet);
      } catch (ServiceException e) {

      }
    } catch (Exception e) {
      // throw error
    }

    if (HttpURLConnection.HTTP_OK == Integer.parseInt(responseEntity.getCode())) {
      // throw ok
      return null;
    } else {
      // throw error
      return null;
    }
  }

  private void convertRequestPacket(IORequestJobEntity entity, List<DBRsltEntity> dbResultEntityList, RequestPacketEntity requestEntity) {
    // TODO Auto-generated method stub

    RzmRsltEntity rzmResultEntity = new RzmRsltEntity();
    MapperEntity mapperResultEntity;
    Mapper mapper = ManagerProvider.mapper().getMapper();

    if (dbResultEntityList == null) {
      rzmResultEntity.setDataEnc("");
      rzmResultEntity.setKeyEnc("");
      rzmResultEntity.setDataHash("");

      requestEntity.setArgs(rzmResultEntity);
      requestEntity.setCmd(entity.getCmd());
      requestEntity.setCode("EMPTY");
      requestEntity.setMid(entity.getMid());

    } else if (dbResultEntityList.size() == 1) {
      mapperResultEntity = mapper.convert(dbResultEntityList.get(0));

      String agentKey = "AGENCY PUBLIC KEY - ";

      String keyEnc = ManagerProvider.crypto().encryptRSA(entity.getPkey(), agentKey);
      String dataEnc = ManagerProvider.crypto().encryptAES(mapperResultEntity);
      String dataHash = ManagerProvider.crypto().hash(mapperResultEntity);

      rzmResultEntity.setDataEnc(dataEnc);
      rzmResultEntity.setKeyEnc(keyEnc);
      rzmResultEntity.setDataHash(dataHash);

      requestEntity.setArgs(rzmResultEntity);
      requestEntity.setCmd(entity.getCmd());
      requestEntity.setCode("OK");
      requestEntity.setMid(entity.getMid());

    } else if (dbResultEntityList.size() > 1) {
      rzmResultEntity.setDataEnc("");
      rzmResultEntity.setKeyEnc("");
      rzmResultEntity.setDataHash("");

      requestEntity.setArgs(rzmResultEntity);
      requestEntity.setCmd(entity.getCmd());
      requestEntity.setCode("REQUIRED KEY");
      requestEntity.setMid(entity.getMid());
    }
  }

  private List<DBRsltEntity> getDBData(IORequestJobEntity entity) throws IOException {

    DBConverter converter = ManagerProvider.database().getConvertManager().getConverter();
    DaoManagerImpl daoMgr = ManagerProvider.database().getDaoManager();
    List<DBRsltEntity> dbResultEntityList = daoMgr.getDao().getRecords(converter.convert(entity));

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
