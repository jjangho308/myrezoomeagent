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
import io.rezoome.manager.database.entity.DBEntity;
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
      RequestPacketEntity requestEntity = convertRequestPacketEntity(entity);
      LOG.debug("requestEntity {}", requestEntity.toString());

      // RequestPacketEntity requestEntity2 = ManagerProvider.network().convert(rzmRsltEntity,
      // "SearchResult");

      // ResponsePacketEntity responseEntity = ManagerProvider.network().request(requestEntity,
      // "http", "post", entity.getSid());

      RequestPacket packet = new RequestPacket(entity.getSid(), JSON.toJson(requestEntity));
      ResponsePacketEntity responseEntity = null;

      try {
        responseEntity = ManagerProvider.network().request(packet);
      } catch (ServiceException e) {

      }

      // log
      ManagerProvider.log();

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;

  }

  private RequestPacketEntity convertRequestPacketEntity(IORequestJobEntity entity) throws IOException {

    List<DBRsltEntity> dbRsltEntity = getDBData(entity);
    LOG.debug("DBRsltEntity {}", dbRsltEntity);    

    RequestPacketEntity requestEntity = new RequestPacketEntity();
    RzmRsltEntity rzmRsltEntity = new RzmRsltEntity();
    MapperEntity mapperRsltEntity;
    Mapper mapper = ManagerProvider.mapper().getMapper();

    if (dbRsltEntity == null) {
      rzmRsltEntity.setDataEnc("");
      rzmRsltEntity.setKeyEnc("");
      rzmRsltEntity.setDataHash("");

      requestEntity.setArgs(rzmRsltEntity);
      requestEntity.setCmd(entity.getCmd());
      requestEntity.setCode("EMPTY");
      requestEntity.setMid(entity.getMid());

    } else if (dbRsltEntity.size() == 1) {
      mapperRsltEntity = mapper.convert(dbRsltEntity.get(0));      

      String agentKey = "AGENCY PUBLIC KEY - ";

      String keyEnc = ManagerProvider.crypto().encryptRSA(entity.getPkey(), agentKey);
      String dataEnc = ManagerProvider.crypto().encryptAES(mapperRsltEntity);
      String dataHash = ManagerProvider.crypto().hash(mapperRsltEntity);

      rzmRsltEntity.setDataEnc(dataEnc);
      rzmRsltEntity.setKeyEnc(keyEnc);
      rzmRsltEntity.setDataHash(dataHash);

      requestEntity.setArgs(rzmRsltEntity);
      requestEntity.setCmd(entity.getCmd());
      requestEntity.setCode("OK");
      requestEntity.setMid(entity.getMid());

    } else if (dbRsltEntity.size() > 1) {
      rzmRsltEntity.setDataEnc("");
      rzmRsltEntity.setKeyEnc("");
      rzmRsltEntity.setDataHash("");

      requestEntity.setArgs(rzmRsltEntity);
      requestEntity.setCmd(entity.getCmd());
      requestEntity.setCode("REQUIRED KEY");
      requestEntity.setMid(entity.getMid());
    }

    return requestEntity;
  }

  private List<DBRsltEntity> getDBData(IORequestJobEntity entity) throws IOException {

    DBConverter converter = ManagerProvider.database().getConvertManager().getConverter();
    DBEntity dbEntity = converter.convert(entity);

    DaoManagerImpl daoMgr = ManagerProvider.database().getDaoManager();
    DBRsltEntity dbRsltEntity = null;
    List<DBRsltEntity> dbRsltEntityList = null;

    dbRsltEntityList = daoMgr.getDao().getRecords(dbEntity);

    for (DBRsltEntity record : dbRsltEntityList) {
      System.out.println(record);
    }

    return dbRsltEntityList;

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
