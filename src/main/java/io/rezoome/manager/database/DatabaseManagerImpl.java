package io.rezoome.manager.database;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.core.annotation.ManagerType;
import io.rezoome.manager.AbstractManager;
import io.rezoome.manager.database.connect.DBConnectionManagerImpl;
import io.rezoome.manager.database.convert.DBConvertManagerImpl;
import io.rezoome.manager.database.dao.DaoManagerImpl;
import io.rezoome.manager.property.PropertyEnum;
import io.rezoome.manager.provider.ManagerProvider;

@ManagerType("DB")
public class DatabaseManagerImpl extends AbstractManager implements DatabaseManager{
  
  protected static String poolName, dbType, dbVersion, dbHost, dbServer, dbName, dbPort, dbUserID, dbPasswd;
  protected static String mybatisConfigXmlPath;
  protected static String daoClass;
  protected static int maxConn, initConn, maxWait;
  
  protected static DBConnectionManagerImpl connecter = null;
  protected static DBConvertManagerImpl converter = null;
  protected static DaoManagerImpl dao = null;
  
  private static class Singleton {
	    private static final DatabaseManager instance = new DatabaseManagerImpl();
	  }

	  public static DatabaseManager getInstance() {
	    return Singleton.instance;
	  }
	  
  @Override
  public void initialize(InitialEvent event) {
    
    // TODO Auto-generated method stub 
    dbType = ManagerProvider.property().getProperty(PropertyEnum.DBMS_TYPE, true);
    dbVersion = ManagerProvider.property().getProperty(PropertyEnum.DBMS_VERSION, true);
    dbHost = ManagerProvider.property().getProperty(PropertyEnum.DB_HOST, true);
    dbPort = ManagerProvider.property().getProperty(PropertyEnum.DB_PORT, true);
    dbName = ManagerProvider.property().getProperty(PropertyEnum.DB_NAME, true);
    poolName = ManagerProvider.property().getProperty(PropertyEnum.CONNECTION_POOL_NAME, true);
    dbUserID = ManagerProvider.property().getProperty(PropertyEnum.DB_USER_ID, true);
    dbPasswd = ManagerProvider.property().getProperty(PropertyEnum.DB_PASSWORD, true);
    maxConn = Integer.parseInt(ManagerProvider.property().getProperty(PropertyEnum.DB_MAX_CONNECTION, true));
    initConn = Integer.parseInt(ManagerProvider.property().getProperty(PropertyEnum.DB_INIT_CONNECTION, true));
    maxWait = Integer.parseInt(ManagerProvider.property().getProperty(PropertyEnum.DB_MAX_WAIT, true));
    
    // mybatis Config file 
    mybatisConfigXmlPath = ManagerProvider.property().getProperty(PropertyEnum.MYBATIS_CONFIG_FILE_PATH, true);
    daoClass = ManagerProvider.property().getProperty(PropertyEnum.DAO_CLASS_NAME, true);
    
    connecter = ((DBConnectionManagerImpl) DBConnectionManagerImpl.getInstance());
    connecter.createConnection();
    converter = (DBConvertManagerImpl) DBConvertManagerImpl.getInstance();
    converter.createConverter();
    dao = ((DaoManagerImpl)DaoManagerImpl.getInstance());
    dao.createDao();
    setPrepared();
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
  public DBConnectionManagerImpl getConnectManager() {
    // TODO Auto-generated method stub
    if(connecter == null) return null;
    else return connecter;
  }

  @Override
  public DBConvertManagerImpl getConvertManager() {
    // TODO Auto-generated method stub
    if(converter == null) return null;
    else return converter;
  }

  @Override
  public DaoManagerImpl getDaoManager() {
    // TODO Auto-generated method stub
    if(dao == null) return null;
    else return dao;
  }

 
  
}
