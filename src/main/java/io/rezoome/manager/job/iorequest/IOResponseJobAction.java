package io.rezoome.manager.job.iorequest;

import io.rezoome.manager.database.DatabaseManagerImpl;
import io.rezoome.manager.database.dao.agency.InhaUniversityDaoImpl;
import io.rezoome.manager.database.entity.DatabaseEntity;
import io.rezoome.manager.database.entity.DatabaseResultEntity;
import io.rezoome.manager.job.JobResult;
import io.rezoome.manager.job.entity.AbstractJob;
import io.rezoome.manager.provider.ManagerProvider;

public class IOResponseJobAction extends AbstractJob<IOResponseJob> {

	public IOResponseJobAction(IOResponseJob entity) {
		super(entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected JobResult processInternal(IOResponseJob entity) {
		
		
	  
		DatabaseEntity dbEntity = ManagerProvider.database().getConvertManager().getConverter().convert(entity);
		DatabaseResultEntity dbRsltEntity = ManagerProvider.database().getDaoManager().getDao().getRecord(dbEntity);
		
		
		Mapper specificMapper = ManagerProvider.database().getMapper(dbRsltEntity);
		RezoomeEntity response = specificMapper.convertToRezoomeEntity(grade, score);
		
		ManagerProvider.database().getDao().getSpecificDao().getRecord(entity);
		
	    // Http request
	    
	    
	    // log 
	    ManagerProvider.log();
	    return null;
	}
	

}
