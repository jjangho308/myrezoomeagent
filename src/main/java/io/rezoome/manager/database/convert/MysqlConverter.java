package io.rezoome.manager.database.convert;

import io.rezoome.manager.database.entity.DatabaseEntity;
import io.rezoome.manager.job.entity.AbstractJobEntity;

public class MysqlConverter extends DBConvertManager implements DBConverter{
  private static class Singleton {
    private static final AbstractDBConverter instance = new MysqlConverter();
  }

  public static AbstractDBConverter getInstance() {
    return (AbstractDBConverter) Singleton.instance;
  }
  
  
  public MysqlConverter() {
  }


  @Override
  public DatabaseEntity convert(AbstractJobEntity job) {
    // TODO Auto-generated method stub
    
    
    return null;
  }
  
}
