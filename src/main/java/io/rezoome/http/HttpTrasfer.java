package io.rezoome.http;

import java.util.Map;

public interface HttpTrasfer {
  
  public void callHttpPostBasic(String endPoint, Map<String, Object> headers, Map<String, Object> parameters);
  public void callHttpPostJson(String endpoint, Map<String, Object> headers, String bodyJson);
  public void callHttpGet(String endPoint, Map<String, Object> headers);
  public void callHttpsPostBasic(String endPoint, Map<String, Object> headers, Map<String, Object> parameters);
  public void callHttpsPostJson(String endpoint, Map<String, Object> headers, String bodyJson);
  public void callHttpsGet(String endPoint, Map<String, Object> headers);
  
}
