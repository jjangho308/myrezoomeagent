package DatabaseManager;

import java.sql.Connection;
import java.util.Properties;

public class OracleConnecter extends DatabaseConnecter {
  //use singleton design patern 
  private static OracleConnecter instance;
  public static OracleConnecter getInstance(){
      if(instance == null){
          instance = new OracleConnecter();
      }
      return instance;
  }
  @Override
  public void setConnectInfo(String dbDriverName, String host, String port, String dbName, String userName, String userPwd, String maxPoolSize) {
    // TODO Auto-generated method stub
    
  }
  @Override
  public Connection connect() {
    // TODO Auto-generated method stub
    return null;
  }
  @Override
  public void disconnect() {
    // TODO Auto-generated method stub
    
  }
  @Override
  protected Properties getProperties() {
    // TODO Auto-generated method stub
    return null;
  }
  
  
  
}
