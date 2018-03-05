package io.rezoome.manager.database;

import io.rezoome.manager.Manager;
import io.rezoome.manager.database.connect.DBConnectionManagerImpl;
import io.rezoome.manager.database.convert.DBConvertManagerImpl;
import io.rezoome.manager.database.dao.DaoManagerImpl;

public interface DatabaseManager extends Manager {
  public DBConnectionManagerImpl getConnectManager();
  public DBConvertManagerImpl getConvertManager();
  public DaoManagerImpl getDaoManager();
}
