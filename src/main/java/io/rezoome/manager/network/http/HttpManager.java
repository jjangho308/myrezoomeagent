package io.rezoome.manager.network.http;

import java.util.Map;

import io.rezoome.exception.ServiceException;

public interface HttpManager {

	public String sendPost(String endpoint, Map<String, Object> headers, Object parameters) throws ServiceException;

	public String sendGet(String endpoint, Map<String, Object> headers) throws ServiceException;

}
