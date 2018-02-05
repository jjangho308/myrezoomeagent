package DatabaseManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnecter extends DatabaseConnecter {

  //use singleton design patern 
  private static MySQLConnecter instance;
  public static MySQLConnecter getInstance(){
      if(instance == null){
          instance = new MySQLConnecter();
      }
      return instance;
  }
  
  // init database constants
  private static String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
  private static String DATABASE_URL = "jdbc:mysql://";
  private static String USERNAME = "";
  private static String PASSWORD = "";
  private static String MAX_POOL = "0";
  
  // init connection object
  private Connection connection;
  // init properties object
  private Properties properties;
  
  
  
  public void setConnectInfo(String dbDriverName, String host, String port, String dbName, String userName, String userPwd, String maxPoolSize){
    this.DATABASE_DRIVER = dbDriverName;
    this.DATABASE_URL = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
    this.USERNAME = userName;
    this.PASSWORD = userPwd;
    this.MAX_POOL = maxPoolSize;    
  }
  
  
  // create properties
  protected Properties getProperties() {
    if(properties == null) {
      properties = new Properties();
      properties.setProperty("user", USERNAME);
      properties.setProperty("password", PASSWORD);
      properties.setProperty("MaxPooledStatements", MAX_POOL);
    }
    return properties;
  }
  
  // connect database
  public Connection connect() {
      if (connection == null) {
          try {
              Class.forName(DATABASE_DRIVER);
              connection = (Connection) DriverManager.getConnection(DATABASE_URL, getProperties());
          } catch (ClassNotFoundException | SQLException e) {
              e.printStackTrace();
          }
      }
      return connection;
  }
  
  // disconnect database
  public void disconnect() {
    if(connection != null) {
      try {
        connection.close();
        connection = null;
      } catch (SQLException e) {
          e.printStackTrace();
      }
    }
  }    


}
