package agent.rezoome.manager.database.connect;


public class OracleConnecter extends DBConnectionManagerImpl {
  
  public OracleConnecter() {
    String JDBCDriver = "oracle.jdbc.driver.OracleDriver";
    String JDBCDriverType = "jdbc:oracle://";
    String url = JDBCDriverType + ":@" + dbServer + ":" + super.dbPort + ":" + super.dbName;
    connMgr = DBConnectionPoolManager.getInstance();
    connMgr.init(poolName, JDBCDriver, url, super.dbUserID, super.dbPasswd, super.maxConn, super.initConn, super.maxWait);
  }
}
