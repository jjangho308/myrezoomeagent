package DatabaseManager;

import java.sql.Connection;
import java.util.Properties;

public abstract class DatabaseConnecter{
/*
//use singleton design patern 
  private static DatabaseConnecter instance;
  public static DatabaseConnecter getInstance(){
      if(instance == null){
          instance = new DatabaseConnecter();
      }
      return instance;
  }
*/
  public final DatabaseConnecter createConnecter(String dbType) throws Exception{
    if("mysql".equals(dbType)){
      return MySQLConnecter.getInstance();
    }else if("oracle".equals(dbType)){
      return OracleConnecter.getInstance();
    }else{
      throw new Exception();
    }
  }
  
  public abstract void setConnectInfo(String dbDriverName, String host, String port, String dbName, String userName, String userPwd, String maxPoolSize);
  public abstract Connection connect();
  public abstract void disconnect();
  protected abstract Properties getProperties();
  
  
}
