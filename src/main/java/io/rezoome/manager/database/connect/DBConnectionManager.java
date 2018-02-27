package io.rezoome.manager.database.connect;


import java.sql.Connection;

import io.rezoome.manager.database.DatabaseManagerImpl;



public class DBConnectionManager extends AbstractDBConnectionManager {
  protected DBConnectionPoolManager connMgr;

  
  private static class Singleton {
    private static final DatabaseManagerImpl instance = new DBConnectionManager();
  }

  public static DatabaseManagerImpl getInstance() {
    return Singleton.instance;
  }
  
  public DBConnectionManager(){
    
    if("ORACLE".equals(dbType.toUpperCase())){
      connecter = new OracleConnecter();
    }else if("MYSQL".equals(dbType.toUpperCase())){
      connecter = new MysqlConnecter();    
    }
    
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
