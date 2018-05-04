package io.rezoome.manager.database.connect;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.rezoome.manager.database.DatabaseManagerImpl;

public class DBConnectionManagerImpl extends DatabaseManagerImpl implements DBConnectionManager {

  private final Logger LOG = LoggerFactory.getLogger("AGENT_LOG");

  protected DBConnectionPoolManager connMgr;

  private static class Singleton {
    private static final DatabaseManagerImpl instance = new DBConnectionManagerImpl();
  }

  public static DatabaseManagerImpl getInstance() {
    return Singleton.instance;
  }

  public DBConnectionManagerImpl() {

  }

  @SuppressWarnings("static-access")
  @Override
  public void createConnection() {
    if ("ORACLE".equals(super.dbType.toUpperCase())) {
      super.connecter = new OracleConnecter();
    } else if ("MYSQL".equals(super.dbType.toUpperCase())) {
      super.connecter = new MysqlConnecter();
    } else if("MSSQL".equals(super.dbType.toUpperCase())) {
      super.connecter = new MssqlConnecter();
    }
    LOG.debug("{} created connector.", super.dbType.toUpperCase());
  }

  @Override
  public boolean isPrepared() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public Connection getConnection(String poolName) {
    return (connMgr.getConnection(poolName));
  }

  @Override
  public void freeConnection(Connection conn) {
    connMgr.freeConnection(poolName, conn);
  }

  public int getDriverNumber() {
    return connMgr.getDriverNumber();
  }

}
