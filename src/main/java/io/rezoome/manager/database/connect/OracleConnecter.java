package io.rezoome.manager.database.connect;

public class OracleConnecter extends DBConnectionManagerImpl {

  public OracleConnecter() {
    String JDBCDriver = "oracle.jdbc.driver.OracleDriver";
    // String JDBCDriverType = "jdbc:oracle:";
    String JDBCDriverType = "jdbc:oracle:thin";
    String url = JDBCDriverType + ":@" + dbHost + ":" + dbPort + "/" + dbName;
    connMgr = DBConnectionPoolManager.getInstance();
    connMgr.init(poolName, JDBCDriver, url, dbUserID, dbPasswd, maxConn, initConn, maxWait);
  }
}
