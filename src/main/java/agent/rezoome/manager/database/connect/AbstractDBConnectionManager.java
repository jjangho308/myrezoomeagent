package agent.rezoome.manager.database.connect;

import java.sql.Connection;

import agent.rezoome.manager.Manager;
import agent.rezoome.manager.database.DatabaseManagerImpl;

public abstract class AbstractDBConnectionManager extends DatabaseManagerImpl {
  public abstract Connection getConnection();
  public abstract void freeConnection(Connection conn);
  
}



