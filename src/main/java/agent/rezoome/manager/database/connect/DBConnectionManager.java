package agent.rezoome.manager.database.connect;

import java.sql.Connection;

import agent.rezoome.manager.Manager;
import agent.rezoome.manager.database.DatabaseManager;

public abstract class DBConnectionManager extends DatabaseManager {
  public abstract Connection getConnection();
  public abstract void freeConnection(Connection conn);
  
}



