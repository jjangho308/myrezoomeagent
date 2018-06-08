package io.rezoome.manager.database.connect;

public class MssqlConnecter extends DBConnectionManagerImpl  {
  public MssqlConnecter() {
    String JDBCDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String JDBCDriverType = "jdbc:sqlserver://";
    // String url = JDBCDriverType + ":@" + dbHost + ":" + dbPort + ":" +
    // dbName;
    String url = JDBCDriverType + dbHost + ":" + dbPort + ";databaseName=" + dbName + ";user=" + dbUserID+";password=" + dbPasswd ;
    connMgr = DBConnectionPoolManager.getInstance();
    connMgr.init(poolName, JDBCDriver, url, dbUserID, dbPasswd, maxConn, initConn, maxWait);
  }
}
