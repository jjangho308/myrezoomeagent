package io.rezoome.manager.database.connect;

import java.sql.Connection;

import io.rezoome.manager.Manager;
import io.rezoome.manager.database.DatabaseManagerImpl;

public interface DBConnectionManager  {
  public void createConnection();
  public abstract Connection getConnection(String pooName);
  public abstract void freeConnection(Connection conn);
  
}



