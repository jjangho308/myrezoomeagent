package io.rezoome.manager.job.iorequest;

import java.io.IOException;

import io.rezoome.entity.RzmRsltEntity;
import io.rezoome.manager.database.convert.DBConverter;
import io.rezoome.manager.database.dao.DaoManagerImpl;
import io.rezoome.manager.database.entity.DBEntity;
import io.rezoome.manager.database.entity.DBRsltEntity;
import io.rezoome.manager.job.JobRsltEntity;
import io.rezoome.manager.job.entity.AbstractJob;
import io.rezoome.manager.mapper.Mapper;
import io.rezoome.manager.network.entity.RequestPacketEntity;
import io.rezoome.manager.network.entity.ResponsePacketEntity;
import io.rezoome.manager.provider.ManagerProvider;

public class IORequestJobAction extends AbstractJob<IORequestJobEntity> {

  public IORequestJobAction() {
    super();
  }

  @Override

  protected JobRsltEntity processInternal(IORequestJobEntity entity) {

    try {
      System.out.println("IORequest Job");
      System.out.println(entity);

      DBConverter converter = ManagerProvider.database().getConvertManager().getConverter();

      DBEntity dbEntity = converter.convert(entity);
      DaoManagerImpl daoMgr = ManagerProvider.database().getDaoManager();

      /*
       * List<DBRsltEntity> dbRsltList = null; dbRsltList = (List<DBRsltEntity>)
       * daoMgr.getDao().getRecodrd(dbEntity); for(DBRsltEntity rslt : dbRsltList){
       * System.out.println(rslt); }
       */

      DBRsltEntity dbRsltEntity = daoMgr.getDao().getRecord(dbEntity);
      System.out.println(dbRsltEntity);

      // Agency Mapping
      Mapper mapper = ManagerProvider.mapper().getMapper();
      RzmRsltEntity response = mapper.convert(dbRsltEntity);

      // List<DBRsltEntity> dbRsltList = null;
      // // step1. select * from tbl where ci
      // dbRsltList = daoMgr.getDao().getRecords(dbEntity);
      //
      // if (dbRsltList.size() == 0) {
      // // step2. select * from tbl where name, birthday, gender
      // dbRsltList = daoMgr.getDao().getRecords(dbEntity);
      //
      // if (dbRsltList.size() == 0) {
      // // step3. select * from tbl where phone, email
      // dbRsltList = daoMgr.getDao().getRecords(dbEntity);
      //
      // } else if (dbRsltList.size() == 1) {
      // // step3. select * from tbl where phone, email
      // dbRsltList = daoMgr.getDao().getRecords(dbEntity);
      //
      // if (dbRsltList.size() == 0) {
      // // response required info
      // }
      // } else {
      // // response required info
      // }
      // } else if (dbRsltList.size() >= 2) {
      // // throw error;
      // }
      //
      // Mapper mapper = ManagerProvider.mapper().getMapper();
      // RzmRsltEntity response = mapper.convert(dbRsltList.get(0));

      // RequestPacketEntity requestEntity =
      // ManagerProvider.network().convert(response, "http", "Post");
      RequestPacketEntity requestEntity = ManagerProvider.network().convert(response, "SearchResult");
      System.out.println(requestEntity);
      ResponsePacketEntity responseEntity = ManagerProvider.network().request(requestEntity, "http", "post");

      // TODO 택수 마무리좀

      // log
      ManagerProvider.log();

    } catch (

    IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;

  }

}
