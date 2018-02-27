package io.rezoome.manager.database.convert;

import io.rezoome.manager.database.entity.DatabaseEntity;
import io.rezoome.manager.job.entity.AbstractJobEntity;

public class OracleConverter extends DBConvertManager implements DBConverter{
  private static class Singleton {
    private static final DBConvertManager instance = new OracleConverter();
  }

  public static OracleConverter getInstance() {
    return (OracleConverter) Singleton.instance;
  }
  
  public OracleConverter() {
  }

  @Override
  public DatabaseEntity convert(AbstractJobEntity job) {
    // TODO Auto-generated method stub
    return null;
  }
  
}
