package agent.rezoome.manager.database;

import agent.rezoome.core.ServiceInitializer.InitialEvent;
import agent.rezoome.manager.Manager;
import agent.rezoome.manager.database.connect.DBConnectionManager;
import agent.rezoome.manager.database.connect.MysqlConnecter;
import agent.rezoome.manager.database.connect.OracleConnecter;
import agent.rezoome.manager.database.convert.DBConvertManager;
import agent.rezoome.manager.database.convert.MysqlConverter;
import agent.rezoome.manager.database.convert.OracleConverter;
import agent.rezoome.manager.database.dao.AbstractDaoManager;
import agent.rezoome.manager.database.dao.DaoManager;
import agent.rezoome.manager.database.dao.MysqlDao;
import agent.rezoome.manager.database.dao.OracleDao;
import agent.rezoome.manager.property.PropertyEnum;
import agent.rezoome.manager.provider.ManagerProvider;

public class DatabaseManagerImpl implements DatabaseManager, Manager{
  
  protected String poolName, dbType, dbVersion, dbHost, dbServer, dbName, dbPort, dbUserID, dbPasswd;
  protected String mybatisConfigXmlPath;
  //= "org/mybatis/example/mybatis-config.xml";
  protected int maxConn, initConn, maxWait;
  
  protected DBConnectionManager connecter = null;
  protected DBConvertManager converter = null;
  protected DaoManager dao = null;
  
  
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
  public DBConnectionManager getConnecter() {
    // TODO Auto-generated method stub
    if(connecter == null) return null;
    else return connecter;
  }

  @Override
  public DBConvertManager getConeverter() {
    // TODO Auto-generated method stub
    if(converter == null) return null;
    else return converter;
  }

  @Override
  public DaoManager getDao() {
    // TODO Auto-generated method stub
    if(dao == null) return null;
    else return dao;
  }

 
  
}
