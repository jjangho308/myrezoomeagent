package agent.rezoome.manager.database;

import java.sql.Connection;
import java.util.Hashtable;
import java.util.Vector;

public class DBConnectionPoolManager {
  // DBConnectionPoolManager
  static private DBConnectionPoolManager instance;
  private Vector<String> drivers = new Vector<String>();
  private Hashtable<String, DBConnectionPool> pools = new Hashtable<String, DBConnectionPool>();

  // DBConnectionPoolManager
  // @return DBConnectionManger
  static synchronized public DBConnectionPoolManager getInstance() {
    if (instance == null) {
      instance = new DBConnectionPoolManager();
    }

    return instance;
  }

  // Default Constructor
  private DBConnectionPoolManager() {}

  // @param name : Pool Name
  // @param con : Connection
  public void freeConnection(String name, Connection con) {
    DBConnectionPool pool = pools.get(name);
    if (pool != null) {
      pool.freeConnection(con);
    }
    System.out.println("One Connection of " + name + " was freed");
  }

  // Open Connection
  // @param name : Pool Name
  // @return Connection : The connection or null
  public Connection getConnection(String name) {
    DBConnectionPool pool = pools.get(name);
    if (pool != null) {
      return pool.getConnection(10);
    }
    return null;
  }

  // Connection Pool
  // @param url : DB URL
  // @param user : DB UserID
  // @param password : DB Password
  private void createPools(String poolName,
      String url,
      String user,
      String password,
      int maxConn,
      int initConn,
      int maxWait) {

    DBConnectionPool pool = new DBConnectionPool(poolName, url, user, password, maxConn, initConn, maxWait);
    pools.put(poolName, pool);
    System.out.println("Initialized pool " + poolName);
  }

  public void init(String poolName,
      String driver,
      String url,
      String user,
      String passwd,
      int maxConn,
      int initConn,
      int maxWait) {

    loadDrivers(driver);
    createPools(poolName, url, user, passwd, maxConn, initConn, maxWait);
  }

  // JDBC Driver Loading
  // @param driverClassName : 
  private void loadDrivers(String driverClassName) {
    try {
      Class.forName(driverClassName);
      drivers.addElement(driverClassName);
      System.out.println("Registered JDBC driver " + driverClassName);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Hashtable<String, DBConnectionPool> getPools() {
    return pools;
  }

  public int getDriverNumber() {
    return drivers.size();
  }
}
