package agent.rezoome.manager.database.convert;


public class DBConvertManager extends AbstractDBConvertManager {


  
  private static class Singleton {
    private static final AbstractDBConvertManager instance = new DBConvertManager();
  }

  public static AbstractDBConvertManager getInstance() {
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
  


}
