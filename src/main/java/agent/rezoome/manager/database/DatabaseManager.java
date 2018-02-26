package agent.rezoome.manager.database;

import agent.rezoome.manager.database.connect.DBConnectionManager;
import agent.rezoome.manager.database.convert.DBConvertManager;
import agent.rezoome.manager.database.dao.DaoManager;

public interface DatabaseManager {
  public DBConnectionManager getConnecter();
  public DBConvertManager getConeverter();
  public DaoManager getDao();
}
