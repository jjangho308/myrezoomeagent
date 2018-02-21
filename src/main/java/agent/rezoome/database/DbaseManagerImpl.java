package agent.rezoome.database;

import java.util.HashMap;


public class DbaseManagerImpl implements DbaseManager {

  public static HashMap<String, DbaseConnecter> dbConnHmp = new HashMap<String, DbaseConnecter>();
  public static HashMap<String, DbaseConverter> dbConvHmp = new HashMap<String, DbaseConverter>();
  public static HashMap<String, DbaseConverter> dbDaoHmp = new HashMap<String, DbaseConverter>();

  private static class Singleton {
    private static final DbaseManagerImpl instance = new DbaseManagerImpl();
  }

  public static DbaseManagerImpl getInstance() {
    System.out.println("create instance");
    return Singleton.instance;
  }

  public int getConnCount() {
    return dbConnHmp.size();
  }

  public DbaseConnecter getConnecter() {
    if (!dbConnHmp.isEmpty()) {
      return dbConnHmp.get(dbConnHmp.keySet().iterator().next());
    } else {
      return null;
    }

  }

  public DbaseConnecter getConnecter(String connName) {

    if (dbConnHmp.containsKey(connName))
      return dbConnHmp.get(connName);
    else
      return null;
  }

  public DbaseConnecter createConnection(String connName, String dbDriverName, String host, String port, String dbName, String userName, String userPwd, String maxPoolSize) {
    DbaseConnecter dbConn = null;
    try {
      dbConn = DbaseConnecterFactory.createConnecter(dbDriverName, host, port, dbName, userName, userPwd, maxPoolSize);
      dbConnHmp.put(connName, dbConn);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return dbConn;

  }

  public DbaseConnecter createConnection(String dbDriverName, String host, String port, String dbName, String userName, String userPwd, String maxPoolSize) {
    DbaseConnecter dbConn = null;
    try {
      dbConn = DbaseConnecterFactory.createConnecter(dbDriverName, host, port, dbName, userName, userPwd, maxPoolSize);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    dbConnHmp.put("", dbConn);
    return dbConn;
  }

  public DbaseConverter createConverter(String convName, String dbDriverName) {
    DbaseConverter dbConv = null;
    try {
      dbConv = DbaseConverterFactory.createConverter(convName, dbDriverName);
      dbConvHmp.put(convName, dbConv);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return dbConv;

  }

  public DbaseConverter createConverter(String dbDriverName) {
    DbaseConverter dbConv = null;
    try {
      dbConv = DbaseConverterFactory.createConverter("", dbDriverName);
      dbConvHmp.put("", dbConv);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return dbConv;

  }


  public void createAll(String name, String dbDriverName, String host, String port, String dbName, String userName, String userPwd, String maxPoolSize) {
    try {
      DbaseConnecter dbConn = DbaseConnecterFactory.createConnecter(dbDriverName, host, port, dbName, userName, userPwd, maxPoolSize);
      DbaseConverter dbConv = DbaseConverterFactory.createConverter(name, dbDriverName);

      dbConnHmp.put(name, dbConn);
      dbConvHmp.put(name, dbConv);

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }


  }


  public DbaseConverter getConverter() {
    if (!dbConvHmp.isEmpty()) {
      return dbConvHmp.get(dbConvHmp.keySet().iterator().next());
    } else {
      return null;
    }

  }

  public DbaseConverter getConverter(String connName) {

    if (dbConvHmp.containsKey(connName))
      return dbConvHmp.get(connName);
    else
      return null;
  }

}
