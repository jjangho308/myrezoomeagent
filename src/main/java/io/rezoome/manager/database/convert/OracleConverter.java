package io.rezoome.manager.database.convert;

import io.rezoome.lib.json.JSON;
import io.rezoome.manager.database.convert.entity.MySQLEntity;
import io.rezoome.manager.database.entity.DBEntity;
import io.rezoome.manager.job.entity.AbstractJobEntity;

public class OracleConverter extends DBConvertManagerImpl implements DBConverter {

<<<<<<< HEAD
  @Override
  public DBEntity convert(AbstractJobEntity job) {
    // TODO Auto-generated method stub

    DBEntity entity =  JSON.fromJson(job.toJSON(), MySQLEntity.class);
    
    System.out.println("Oracle Converter : " + entity);
    return entity;
  }  
=======
	@Override
	public DBEntity convert(AbstractJobEntity job) {
		// TODO Auto-generated method stub
		return null;
	}

>>>>>>> branch 'development' of https://github.com/Team-REZOOME/agent.git
}
