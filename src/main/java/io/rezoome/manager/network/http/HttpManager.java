package io.rezoome.manager.network.http;

import java.util.Map;

import io.rezoome.exception.ServiceException;

public interface HttpManager {

  public String post(Map<String, Object> headers, Object parameters) throws ServiceException;
  public String get(Map<String, Object> headers) throws ServiceException;

}
