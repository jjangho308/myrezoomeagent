package agent.rezoome.http;

import java.util.Map;

import agent.rezoome.exception.ServiceException;

public interface HttpManager {

  public String post(Map<String, Object> headers, Object parameters) throws ServiceException;

  public String get(Map<String, Object> headers) throws ServiceException;

}
