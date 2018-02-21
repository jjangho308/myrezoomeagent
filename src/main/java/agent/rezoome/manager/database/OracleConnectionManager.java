package agent.rezoome.manager.database;

public class OracleConnectionManager extends ConnectionManagerImpl {
  public OracleConnectionManager() {
    String JDBCDriver = "oracle.jdbc.driver.OracleDriver";
    String JDBCDriverType = "jdbc:oracle://";
    String url = JDBCDriverType + ":@" + dbServer + ":" + super.dbPort + ":" + super.dbName;
    connMgr = DBConnectionPoolManager.getInstance();
    connMgr.init(poolName, JDBCDriver, url, super.dbUserID, super.dbPasswd, super.maxConn, super.initConn, super.maxWait);
  }
}
