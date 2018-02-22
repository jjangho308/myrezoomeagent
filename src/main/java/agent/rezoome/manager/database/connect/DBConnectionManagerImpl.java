package agent.rezoome.manager.database.connect;


import java.sql.Connection;

import agent.rezoome.core.ServiceInitializer.InitialEvent;
import agent.rezoome.manager.property.PropertyEnum;
import agent.rezoome.manager.provider.ManagerProvider;

public class DBConnectionManagerImpl extends DBConnectionManager {
  protected DBConnectionPoolManager connMgr;

  
  private static class Singleton {
    private static final DBConnectionManager instance = new DBConnectionManagerImpl();
  }

  public static DBConnectionManager getInstance() {
    return Singleton.instance;
  }
  


  @Override
  public boolean isPrepared() {
    // TODO Auto-generated method stub
    return false;
  }
 
  public Connection getConnection() {
    return (connMgr.getConnection(poolName));
  }

  public void freeConnection(Connection conn) {
    connMgr.freeConnection(poolName, conn);
  }


  public int getDriverNumber() {
    return connMgr.getDriverNumber();
  }

  
}
