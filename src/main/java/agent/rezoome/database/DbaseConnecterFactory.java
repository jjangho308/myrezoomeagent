package agent.rezoome.database;

public class DbaseConnecterFactory {

  /*
   * public static MySQLConnecter createMySQLConnecter(String dbDriverName, String host, String
   * port, String dbName, String userName, String userPwd, String maxPoolSize) throws Exception{
   * MySQLConnecter myConn = new MySQLConnecter(); myConn.setConnectInfo(dbDriverName, host, port,
   * dbName, userName, userPwd, maxPoolSize); return myConn; }
   * 
   * public static OracleConnecter createOracleConnecter(String dbDriverName, String host, String
   * port, String dbName, String userName, String userPwd, String maxPoolSize) throws Exception{
   * OracleConnecter oraConn = new OracleConnecter(); oraConn.setConnectInfo(dbDriverName, host,
   * port, dbName, userName, userPwd, maxPoolSize); return oraConn; }
   */


  public static DbaseConnecterImpl createConnecter(String dbDriverName, String host, String port, String dbName, String userName, String userPwd, String maxPoolSize) throws Exception {
    DbaseConnecterImpl dbConn = new DbaseConnecterImpl();
    dbConn.setConnectInfo(dbDriverName, host, port, dbName, userName, userPwd, maxPoolSize);
    return dbConn;
  }
}