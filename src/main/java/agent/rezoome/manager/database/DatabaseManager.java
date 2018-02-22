package agent.rezoome.manager.database;

import agent.rezoome.core.ServiceInitializer.InitialEvent;
import agent.rezoome.manager.Manager;
import agent.rezoome.manager.database.connect.DBConnectionManagerImpl;
import agent.rezoome.manager.database.connect.MysqlConnecter;
import agent.rezoome.manager.database.connect.OracleConnecter;
import agent.rezoome.manager.database.convert.DBConvertManagerImpl;
import agent.rezoome.manager.database.convert.MysqlConverter;
import agent.rezoome.manager.database.convert.OracleConverter;
import agent.rezoome.manager.database.dao.DaoManagerImpl;
import agent.rezoome.manager.database.dao.MysqlDao;
import agent.rezoome.manager.database.dao.OracleDao;
import agent.rezoome.manager.property.PropertyEnum;
import agent.rezoome.manager.provider.ManagerProvider;

public class DatabaseManager implements Manager {
  
  protected String poolName, dbType, dbVersion, dbHost, dbServer, dbName, dbPort, dbUserID, dbPasswd;
  protected int maxConn, initConn, maxWait;
  
  DBConnectionManagerImpl connecter = null;
  DBConvertManagerImpl converter = null;
  DaoManagerImpl dao = null;
  
  
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
    
    // annotation
    if("ORACLE".equals(dbType.toUpperCase())){
      connecter = new OracleConnecter();
      converter = new OracleConverter();
      dao = new OracleDao();
    }else if("MYSQL".equals(dbType.toUpperCase())){
      connecter = new MysqlConnecter();
      converter = new MysqlConverter();
      dao = new MysqlDao();
    }else{
      throw new NullPointerException();
    }    
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

  
}
