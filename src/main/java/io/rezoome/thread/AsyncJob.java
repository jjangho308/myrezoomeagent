package io.rezoome.thread;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import io.rezoome.http.HttpManager;
import io.rezoome.http.HttpManagerImpl;
import io.rezoome.jdbc.ConnectionManager;
import io.rezoome.jdbc.OracleConnectionManager;

public class AsyncJob implements Callable<Map<String, Object>> {

  private ConnectionManager connectionManager;
  private Connection conn = null;
  Statement stmt = null;
  ResultSet rs = null;

  private HttpManager httpManager = null;

  public AsyncJob() {
    // TODO Auto-generated constructor stub
    connectionManager = new OracleConnectionManager();
    conn = connectionManager.getConnection();
    httpManager = new HttpManagerImpl("", "");
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
      httpManager.callHttpPostJson(null, "json string");
    } catch (Exception e) {

    }

    return response;
  }

}
