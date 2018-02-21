package io.rezoome.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.manager.property.PropertyEnum;
import io.rezoome.manager.provider.ManagerProvider;

public class ConnectionManagerImpl implements ConnectionManager {
  protected DBConnectionPoolManager connMgr;
  protected String poolName, dbHost, dbServer, dbName, dbPort, dbUserID, dbPasswd;
  protected int maxConn, initConn, maxWait;
  
  private static class Singleton {
    private static final ConnectionManager instance = new ConnectionManagerImpl();
  }

  public static ConnectionManager getInstance() {
    return Singleton.instance;
  }
  
  @Override
  public void initialize(InitialEvent event) {
    
    // TODO Auto-generated method stub    
    dbHost = ManagerProvider.property().getProperty(PropertyEnum.DB_HOST, true);
    dbPort = ManagerProvider.property().getProperty(PropertyEnum.DB_PORT, true);
    dbName = ManagerProvider.property().getProperty(PropertyEnum.DB_NAME, true);
    dbUserID = ManagerProvider.property().getProperty(PropertyEnum.DB_USER_ID, true);
    dbPasswd = ManagerProvider.property().getProperty(PropertyEnum.DB_PASSWORD, true);
    maxConn = Integer.parseInt(ManagerProvider.property().getProperty(PropertyEnum.DB_MAX_CONNECTION, true));
    initConn = Integer.parseInt(ManagerProvider.property().getProperty(PropertyEnum.DB_INIT_CONNECTION, true));
    maxWait = Integer.parseInt(ManagerProvider.property().getProperty(PropertyEnum.DB_MAX_WAIT, true));
  }

  @Override
  public void initializeOnThread(InitialEvent event) {
    // TODO Auto-generated method stub
    
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
