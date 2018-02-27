package io.rezoome.manager.database.connect;

public class MysqlConnecter extends DBConnectionManager {
  public MysqlConnecter() {
    String JDBCDriver = "com.mysql.jdbc.Driver";
    String JDBCDriverType = "jdbc:mysql://";
    String url = JDBCDriverType + ":@" + super.dbServer + ":" + super.dbPort + ":" + super.dbName;
    connMgr = DBConnectionPoolManager.getInstance();
    connMgr.init(poolName, JDBCDriver, url, super.dbUserID, super.dbPasswd, super.maxConn, super.initConn, super.maxWait);
  }
}
