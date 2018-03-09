package io.rezoome.manager.database.connect;

import java.sql.Connection;

public interface DBConnectionManager {
	public void createConnection();

	public abstract Connection getConnection(String pooName);

	public abstract void freeConnection(Connection conn);

}
