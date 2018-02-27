package io.rezoome.manager.database.connect;

import java.sql.Connection;

import io.rezoome.manager.Manager;
import io.rezoome.manager.database.DatabaseManagerImpl;

public abstract class AbstractDBConnectionManager extends DatabaseManagerImpl {
  public abstract Connection getConnection();
  public abstract void freeConnection(Connection conn);
  
}



