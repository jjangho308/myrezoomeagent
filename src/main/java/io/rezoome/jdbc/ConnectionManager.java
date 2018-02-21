package io.rezoome.jdbc;

import java.sql.Connection;

import io.rezoome.manager.Manager;

<<<<<<< HEAD
public interface ConnectionManager extends Manager {
  public Connection getConnection();
  public void freeConnection(Connection conn);
  
=======
  public ConnectionManager(String pool) {
    poolName = pool;
    configFile = "C:/jdbc/" + poolName + ".properties";

    try {
      dbProperties = readProperties();
      dbServer = getProperty("dbServer");
      port = getProperty("port");
      dbName = getProperty("dbName");
      userID = getProperty("userID");
      passwd = getProperty("passwd");
      maxConn = Integer.parseInt(getProperty("maxConn"));
      initConn = Integer.parseInt(getProperty("initConn"));
      maxWait = Integer.parseInt(getProperty("maxWait"));
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  public Connection getConnection() {
    return (connMgr.getConnection(poolName));
  }

  public void freeConnection(Connection conn) {
    connMgr.freeConnection(poolName, conn);
  }

  private String getProperty(String prop) throws IOException {
    return (dbProperties.getProperty(prop));
  }

  protected synchronized Properties readProperties() throws IOException {
    Properties tempProperties = new Properties();
    FileInputStream in = new FileInputStream(configFile);
    tempProperties.load(in);
    return tempProperties;
  }

  public int getDriverNumber() {
    return connMgr.getDriverNumber();
  }
>>>>>>> branch 'development' of https://github.com/Team-REZOOME/agent.git
}
