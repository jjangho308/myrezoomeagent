package io.rezoome.manager.job.iorequest;

import java.io.IOException;
import java.util.List;

import io.rezoome.entity.RzmRsltEntity;
import io.rezoome.manager.database.convert.DBConverter;
import io.rezoome.manager.database.dao.DaoManagerImpl;
import io.rezoome.manager.database.entity.DBEntity;
import io.rezoome.manager.database.entity.DBRsltEntity;
import io.rezoome.manager.job.JobRsltEntity;
import io.rezoome.manager.job.entity.AbstractJob;
import io.rezoome.manager.mapper.Mapper;
import io.rezoome.manager.mapper.MapperEntity;
import io.rezoome.manager.network.entity.RequestPacketEntity;
import io.rezoome.manager.network.entity.ResponsePacketEntity;
import io.rezoome.manager.property.PropertyEnum;
import io.rezoome.manager.provider.ManagerProvider;

public class IORequestJobAction extends AbstractJob<IORequestJobEntity> {

  public IORequestJobAction() {
    super();
  }

  @Override

  protected JobRsltEntity processInternal(IORequestJobEntity entity) {

    try {
      System.out.println("IORequest Job");

      /*
       * List<DBRsltEntity> dbRsltList = null; dbRsltList = (List<DBRsltEntity>)
       * daoMgr.getDao().getRecodrd(dbEntity); for(DBRsltEntity rslt : dbRsltList){
       * System.out.println(rslt); }
       */      

      
      DBRsltEntity dbRsltEntity = getDBData(entity);
      System.out.println("[DBRsltEntity] : " + dbRsltEntity);

      Mapper mapper = ManagerProvider.mapper().getMapper();
      
            
      MapperEntity mapperRsltEntity = mapper.convert(dbRsltEntity);
      System.out.println("[MapperRsltEntity] : " + mapperRsltEntity.toString());

      String agentKey ="AGENCY PUBLIC KEY - ";
      
      String keyEnc = ManagerProvider.crypto().encryptRSA(entity.getPkey(), agentKey);
      String dataEnc = ManagerProvider.crypto().encryptAES(mapperRsltEntity);
      String dataHash = ManagerProvider.crypto().hash(mapperRsltEntity);
      
      RzmRsltEntity rzmRsltEntity = new RzmRsltEntity();      
      rzmRsltEntity.setDataEnc(dataEnc);
      rzmRsltEntity.setKeyEnc(keyEnc);
      rzmRsltEntity.setDataHash(dataHash);
      
      RequestPacketEntity requestEntity = new RequestPacketEntity();
      requestEntity.setArgs(rzmRsltEntity);
      requestEntity.setCmd(entity.getCmd());
      requestEntity.setCode("return Code");
      requestEntity.setMid(entity.getMid());     
      
      //RequestPacketEntity requestEntity = ManagerProvider.network().convert(rzmRsltEntity, "SearchResult");
      ResponsePacketEntity responseEntity = ManagerProvider.network().request(requestEntity, "http", "post", entity.getSid());
      System.out.println(responseEntity.toString());

      // log
      ManagerProvider.log();

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;

  }

  private DBRsltEntity getDBData(IORequestJobEntity entity) throws IOException {

    DBConverter converter = ManagerProvider.database().getConvertManager().getConverter();
    DBEntity dbEntity = converter.convert(entity);

    DaoManagerImpl daoMgr = ManagerProvider.database().getDaoManager();
    DBRsltEntity dbRsltEntity = null;
    List<DBRsltEntity> dbRsltEntityList = null;

    dbRsltEntity = daoMgr.getDao().getRecord(dbEntity);

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
    return dbRsltEntity;
  }

}
