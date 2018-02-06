package DatabaseManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnecterImpl implements DatabaseConnecter {
//init database constants
 protected static String DATABASE_DRIVER = "";
 protected static String DATABASE_URL = "";
 protected static String USERNAME = "";
 protected static String PASSWORD = "";
 protected static String MAX_POOL = "0";
 
 // init connection object
 protected Connection connection;
 // init properties object
 protected Properties properties;
  
 protected static final String MYSQL_DATABASE_USR = "jdbc:mysql://";
 protected static final String ORACLE_DATABASE_USR = "jdbc:oracle://";
 
 public void setConnectInfo(String dbDriverName, String host, String port, String dbName, String userName, String userPwd, String maxPoolSize) throws Exception{
   this.DATABASE_DRIVER = dbDriverName;
   if("com.mysql.jdbc.Driver".equals(dbDriverName)){
     this.DATABASE_URL = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
   }else if("com.oracle.jdbc.Driver".equals(dbDriverName)){
     this.DATABASE_URL = "jdbc:oracle://" + host + ":" + port + "/" + dbName;
   }else{
     throw new Exception();
   }
   
   this.USERNAME = userName;
   this.PASSWORD = userPwd;
   this.MAX_POOL = maxPoolSize;    
 }
 
 
 // create properties
 public Properties getProperties() {
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
