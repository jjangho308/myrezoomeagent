package io.rezoome.jdbc;

import java.sql.Connection;

import io.rezoome.manager.Manager;

public interface ConnectionManager extends Manager {
  public Connection getConnection();
  public void freeConnection(Connection conn);
  
}
