package io.rezoome.manager.database.convert;

import io.rezoome.manager.database.DatabaseManagerImpl;

public class DBConvertManagerImpl extends DatabaseManagerImpl implements DBConverterManager {


  
  private static class Singleton {
    private static final DatabaseManagerImpl instance = new DBConvertManagerImpl();
  }

  public static DatabaseManagerImpl getInstance() {
    return Singleton.instance;
  }
  
  public DBConvertManagerImpl(){
   
    
  }
  
  @SuppressWarnings("static-access")
  @Override
  public void createConverter(){
    System.out.println(super.dbType);
    if("ORACLE".equals(super.dbType.toUpperCase())){
      super.converter =  new OracleConverter();
    }else if("MYSQL".equals(super.dbType.toUpperCase())){
      super.converter = new MysqlConverter();    
    }
    
  }
  
  @Override
  public boolean isPrepared() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public DBConverter getConverter() {
    // TODO Auto-generated method stub
    return (DBConverter) converter;
  }



}
