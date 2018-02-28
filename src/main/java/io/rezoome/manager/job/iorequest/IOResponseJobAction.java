package io.rezoome.manager.job.iorequest;

import java.io.IOException;

import io.rezoome.entity.RzmRsltEntity;
import io.rezoome.manager.database.DatabaseManagerImpl;
import io.rezoome.manager.database.dao.agency.InhaUniversityDaoImpl;
import io.rezoome.manager.database.entity.DBEntity;
import io.rezoome.manager.database.entity.DBRsltEntity;
import io.rezoome.manager.job.JobRsltEntity;
import io.rezoome.manager.job.entity.AbstractJob;
import io.rezoome.manager.mapper.AbstractMapper;
import io.rezoome.manager.mapper.AbstractMapper;
import io.rezoome.manager.mapper.MapperManager;
import io.rezoome.manager.network.entity.RequestPacketEntity;
import io.rezoome.manager.provider.ManagerProvider;

public class IOResponseJobAction extends AbstractJob<IOResponseJob> {

	public IOResponseJobAction(IOResponseJob entity) {
		super(entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected JobRsltEntity processInternal(IOResponseJob entity) {
		
	  try {
	  
	    // Database
  		DBEntity dbEntity = ManagerProvider.database().getConvertManager().getConverter().convert(entity);
  		DBRsltEntity dbRsltEntity;
      dbRsltEntity = ManagerProvider.database().getDaoManager().getDao().getRecord(dbEntity);
     
      // Mapping
      AbstractMapper mapper = ManagerProvider.mapper().getMapper();
  		RzmRsltEntity response = mapper.convert(dbRsltEntity);
  		RequestPacketEntity packetEntity = mapper.convert(response);
  	  
  		// Http
  		ManagerProvider.network().request(packetEntity);
  	  
  	  // log 
  	  ManagerProvider.log();
	
	  } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ReflectiveOperationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
	  return null;
	  
	}
	

}
