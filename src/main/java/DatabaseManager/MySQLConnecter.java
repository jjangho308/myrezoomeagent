package DatabaseManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnecter extends DbaseConnecterImpl {

  //use singleton design patern 
  private static MySQLConnecter instance;
  public static MySQLConnecter getInstance(){
      if(instance == null){
          instance = new MySQLConnecter();
      }
      return instance;
  }
}
