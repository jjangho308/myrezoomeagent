package io.rezoome.manager.database.convert;

import io.rezoome.lib.json.JSON;
import io.rezoome.manager.database.entity.UserEntity;
import io.rezoome.manager.job.entity.AbstractJobEntity;

public class MysqlConverter extends DBConvertManagerImpl implements DBConverter {

  @Override
  public UserEntity convert(AbstractJobEntity job) {
    // TODO Auto-generated method stub
   
    
    System.out.println("JOB : " + job.toString());
    UserEntity entity = JSON.fromJson(job.toString(), UserEntity.class);
    return entity;
  }
}