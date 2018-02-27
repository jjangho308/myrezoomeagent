package io.rezoome.manager.database.convert;



public class DBConvertManager extends AbstractDBConverter {


  
  private static class Singleton {
    private static final AbstractDBConverter instance = new DBConvertManager();
  }

  public static AbstractDBConverter getInstance() {
    return Singleton.instance;
  }
  
  public DBConvertManager(){
    
    if("ORACLE".equals(dbType.toUpperCase())){
      converter = new OracleConverter();
    }else if("MYSQL".equals(dbType.toUpperCase())){
      converter = new MysqlConverter();    
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
