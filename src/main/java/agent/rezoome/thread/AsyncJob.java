package agent.rezoome.thread;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import agent.rezoome.http.HttpConnector;
import agent.rezoome.http.HttpManager;
import agent.rezoome.manager.database.connect.DBConnectionManagerImpl;
import agent.rezoome.manager.database.connect.OracleConnecter;

public class AsyncJob implements Callable<Map<String, Object>> {

  private DBConnectionManagerImpl connectionManager;
  private Connection conn = null;
  Statement stmt = null;
  ResultSet rs = null;

  private HttpManager httpManager = null;

  public AsyncJob() {
    // TODO Auto-generated constructor stub
    connectionManager = new OracleConnecter();
    conn = connectionManager.getConnection();
    // httpManager = new HttpManagerImpl("", "");
    httpManager = new HttpConnector("");
  }

  @Override
  public Map<String, Object> call() throws Exception {
    // TODO Auto-generated method stub

    // 1. db connect
    stmt = conn.createStatement();
    // ....

    // 2. http connect

    Map<String, Object> response = new HashMap<String, Object>();

    try {
      // httpManager.callHttpPostJson(null, "json string");
      httpManager.post(null, "SSSS");

    } catch (Exception e) {

    }

    return response;
  }

}
