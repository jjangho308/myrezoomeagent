package agent.rezoome.database;

import java.sql.Connection;
import java.util.Properties;

public interface DbaseConnecter {

  public void setConnectInfo(String dbDriverName, String host, String port, String dbName, String userName, String userPwd, String maxPoolSize) throws Exception;

  public Connection connect();

  public void disconnect();

  public Properties getProperties();

}
