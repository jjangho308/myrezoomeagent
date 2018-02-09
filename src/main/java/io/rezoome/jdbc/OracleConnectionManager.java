package io.rezoome.jdbc;

public class OracleConnectionManager extends ConnectionManager {
  public OracleConnectionManager() {
    super("oracle");
    String JDBCDriver = "oracle.jdbc.driver.OracleDriver";
    String JDBCDriverType = "jdbc:oracle://";
    String url = JDBCDriverType + ":@" + dbServer + ":" + port + ":" + dbName;
    connMgr = DBConnectionPoolManager.getInstance();
    connMgr.init(poolName, JDBCDriver, url, userID, passwd, maxConn, initConn, maxWait);
  }
}
