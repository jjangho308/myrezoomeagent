package io.rezoome.manager.database.convert;

import io.rezoome.lib.json.JSON;
import io.rezoome.manager.database.entity.DBEntity;
import io.rezoome.manager.job.entity.AbstractJobEntity;

public class OracleConverter extends DBConvertManagerImpl implements DBConverter {


  @Override
  public DBEntity convert(AbstractJobEntity job) {
    // TODO Auto-generated method stub
    DBEntity entity = JSON.fromJson(job.toJSON(), DBEntity.class);
    return entity;
  }

}
