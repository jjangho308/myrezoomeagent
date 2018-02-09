package io.rezoome.jdbc;

public class MySQLConnectionManager extends ConnectionManager {
  public MySQLConnectionManager() {
    super("mysql");
    String JDBCDriver = "com.mysql.jdbc.Driver";
    String JDBCDriverType = "jdbc:mysql://";
    String url = JDBCDriverType + ":@" + dbServer + ":" + port + ":" + dbName;
    connMgr = DBConnectionPoolManager.getInstance();
    connMgr.init(poolName, JDBCDriver, url, userID, passwd, maxConn, initConn, maxWait);
  }
}
