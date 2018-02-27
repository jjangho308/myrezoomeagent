package io.rezoome.manager.database;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.manager.Manager;
import io.rezoome.manager.database.connect.DBConnectionManager;
import io.rezoome.manager.database.convert.DBConvertManager;
import io.rezoome.manager.database.dao.DaoManager;
import io.rezoome.manager.property.PropertyEnum;
import io.rezoome.manager.provider.ManagerProvider;

public class DatabaseManagerImpl implements DatabaseManager, Manager{
  
  protected String poolName, dbType, dbVersion, dbHost, dbServer, dbName, dbPort, dbUserID, dbPasswd;
  protected String mybatisConfigXmlPath;
  protected int maxConn, initConn, maxWait;
  
  protected DBConnectionManager connecter = null;
  protected DBConvertManager converter = null;
  protected DaoManager dao = null;
  
  private static class Singleton {
	    private static final DatabaseManager instance = new DatabaseManagerImpl();
	  }

	  public static DatabaseManagerImpl getInstance() {
	    return (DaoManager) Singleton.instance;
	  }
	  
  @Override
  public void initialize(InitialEvent event) {
    
    // TODO Auto-generated method stub 
    dbType = ManagerProvider.property().getProperty(PropertyEnum.DBMS_TYPE, true);
    dbVersion = ManagerProvider.property().getProperty(PropertyEnum.DBMS_VERSION, true);
    dbHost = ManagerProvider.property().getProperty(PropertyEnum.DB_HOST, true);
    dbPort = ManagerProvider.property().getProperty(PropertyEnum.DB_PORT, true);
    dbName = ManagerProvider.property().getProperty(PropertyEnum.DB_NAME, true);
    poolName = ManagerProvider.property().getProperty(PropertyEnum.DB_NAME, true);
    dbUserID = ManagerProvider.property().getProperty(PropertyEnum.DB_USER_ID, true);
    dbPasswd = ManagerProvider.property().getProperty(PropertyEnum.DB_PASSWORD, true);
    maxConn = Integer.parseInt(ManagerProvider.property().getProperty(PropertyEnum.DB_MAX_CONNECTION, true));
    initConn = Integer.parseInt(ManagerProvider.property().getProperty(PropertyEnum.DB_INIT_CONNECTION, true));
    maxWait = Integer.parseInt(ManagerProvider.property().getProperty(PropertyEnum.DB_MAX_WAIT, true));
    
    // mybatis Config file 
    mybatisConfigXmlPath = ManagerProvider.property().getProperty(PropertyEnum.MYBATIS_CONFIG_FILE_PATH, true);
   
    connecter = (DBConnectionManager) DBConnectionManager.getInstance();
    converter = (DBConvertManager) DBConvertManager.getInstance();
    dao = DaoManager.getInstance();
  }

  @Override
  public void initializeOnThread(InitialEvent event) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public boolean isPrepared() {
    // TODO Auto-generated method stub
    if(connecter != null && converter != null && dao != null)
      return true;
    else
      return false;
  }

  @Override
  public DBConnectionManager getConnectManager() {
    // TODO Auto-generated method stub
    if(connecter == null) return null;
    else return connecter;
  }

  @Override
  public DBConvertManager getConvertManager() {
    // TODO Auto-generated method stub
    if(converter == null) return null;
    else return converter;
  }

  @Override
  public DaoManager getDaoManager() {
    // TODO Auto-generated method stub
    if(dao == null) return null;
    else return dao;
  }

 
  
}
