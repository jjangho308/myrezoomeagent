package agent.rezoome.manager.database;

import java.sql.Connection;

import agent.rezoome.manager.Manager;

public interface ConnectionManager extends Manager {
  public Connection getConnection();
  public void freeConnection(Connection conn);
  
}
