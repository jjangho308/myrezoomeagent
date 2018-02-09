package io.rezoome.http;

import java.util.Map;

import io.rezoome.exception.ServiceException;

public interface HttpManager {

  public String callHttpPostBasic(Map<String, Object> headers, Map<String, Object> parameters) throws ServiceException;

  public String callHttpPostJson(Map<String, Object> headers, String bodyJson) throws ServiceException;

  public String callHttpGet(Map<String, Object> headers) throws ServiceException;

  public String callHttpsPostBasic(Map<String, Object> headers, Map<String, Object> parameters) throws ServiceException;

  public String callHttpsPostJson(Map<String, Object> headers, String bodyJson) throws ServiceException;

  public String callHttpsGet(Map<String, Object> headers) throws ServiceException;

}
