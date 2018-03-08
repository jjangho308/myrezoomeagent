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

      System.out.println("dbentity before: ");
      // Database
      
      DBConverter converter = ManagerProvider.database().getConvertManager().getConverter();
      
      DBEntity dbEntity = converter.convert(entity);
      System.out.println("dbentity : ");
      System.out.println(dbEntity);
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

      // convert to Networking
      RequestPacketEntity packetEntity = ManagerProvider.network().convert(response, "http", "Post");

      // request to server using api
      ManagerProvider.network().request(packetEntity);

      // log
      ManagerProvider.log();

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;

  }

}
