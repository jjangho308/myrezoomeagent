package io.rezoome.manager.database;

import io.rezoome.manager.Manager;
import io.rezoome.manager.database.connect.DBConnectionManager;
import io.rezoome.manager.database.convert.DBConvertManager;
import io.rezoome.manager.database.dao.DaoManager;

public interface DatabaseManager extends Manager {
  public DBConnectionManager getConnectManager();
  public DBConvertManager getConvertManager();
  public DaoManager getDaoManager();
}
