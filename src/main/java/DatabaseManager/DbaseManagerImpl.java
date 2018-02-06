package DatabaseManager;

import java.util.HashMap;


public class DbaseManagerImpl implements DbaseManager{
  
  public static HashMap<String, DbaseConnecter> dbConnHmp = new HashMap<String, DbaseConnecter>();
  
  private static class Singleton {
    private static final DbaseManagerImpl instance = new DbaseManagerImpl();
  }
  
  public static DbaseManagerImpl getInstance () {
    System.out.println("create instance");
    return Singleton.instance;
  }  
  
  public int getConnCount(){
    return dbConnHmp.size();
  }
  
  public DbaseConnecter getConnecter(){
    if(!dbConnHmp.isEmpty()){
      
      //dbConnHmp.values().toArray(ArrayList<DbaseManagerImpl> a);
    }
    
    return null;
  }
  
  public DbaseConnecter getConnecter(String connName){
    
   if(dbConnHmp.containsKey(connName)) return dbConnHmp.get(connName);
   else return null;
  }
  
  public DbaseConnecter createConnection(String connName, String dbDriverName, String host, String port, String dbName, String userName, String userPwd, String maxPoolSize){
    HashMap<String , DbaseConnecter> connHmp = new HashMap<String, DbaseConnecter>();
    DbaseConnecter dbConn = null;
    try {
      dbConn = DbaseConnecterFactory.createMySQLConnecter(dbDriverName, host, port, dbName, userName, userPwd, maxPoolSize);
   
    dbConnHmp.put(connName, dbConn);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return dbConn;  
    
  }
  
  public DbaseConnecter createConnection(String dbDriverName, String host, String port, String dbName, String userName, String userPwd, String maxPoolSize){
    HashMap<String , DbaseConnecter> connHmp = new HashMap<String, DbaseConnecter>();
    DbaseConnecter dbConn = null;
    try {
      dbConn = DbaseConnecterFactory.createMySQLConnecter(dbDriverName, host, port, dbName, userName, userPwd, maxPoolSize);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    dbConnHmp.put("", dbConn);
    return dbConn;
  }
  

}
