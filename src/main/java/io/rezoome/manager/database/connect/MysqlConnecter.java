package io.rezoome.manager.database.connect;

public class MysqlConnecter extends DBConnectionManagerImpl {
	public MysqlConnecter() {
		String JDBCDriver = "com.mysql.jdbc.Driver";
		String JDBCDriverType = "jdbc:mysql://";
		// String url = JDBCDriverType + ":@" + dbHost + ":" + dbPort + ":" +
		// dbName;
		String url = JDBCDriverType + dbHost + ":" + dbPort + "/" + dbName;
		connMgr = DBConnectionPoolManager.getInstance();
		connMgr.init(poolName, JDBCDriver, url, dbUserID, dbPasswd, maxConn,
				initConn, maxWait);
	}
}
