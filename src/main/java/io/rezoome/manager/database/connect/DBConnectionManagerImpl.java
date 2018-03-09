package io.rezoome.manager.database.connect;

import java.sql.Connection;

import io.rezoome.manager.database.DatabaseManagerImpl;

public class DBConnectionManagerImpl extends DatabaseManagerImpl implements DBConnectionManager {
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
		}
		System.out.println("Create Connecter - " + super.dbType.toUpperCase());
	}

	@Override
	public boolean isPrepared() {
		// TODO Auto-generated method stub
		return false;
	}

	public Connection getConnection(String poolName) {
		return (connMgr.getConnection(poolName));
	}

	public void freeConnection(Connection conn) {
		connMgr.freeConnection(poolName, conn);
	}

	public int getDriverNumber() {
		return connMgr.getDriverNumber();
	}

}
