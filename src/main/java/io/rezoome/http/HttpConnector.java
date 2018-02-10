package io.rezoome.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import io.rezoome.constants.Constants;
import io.rezoome.constants.ErrorCodeConstants;
import io.rezoome.exception.ServiceException;

public class HttpConnector implements HttpManager {

  private HttpURLConnection httpURLConnection;
  private URL url;

  private final int CONNECT_TIMEOUT = 10000;
  private final int READ_TIMEOUT = 10000;
  private String response = "";

  public HttpConnector(String endpoint) {
    // TODO Auto-generated constructor stub

    try {
      url = new URL(endpoint);

      httpURLConnection = (HttpURLConnection) url.openConnection();

      httpURLConnection.setDoInput(true);
      httpURLConnection.setDoOutput(true);

      httpURLConnection.setConnectTimeout(CONNECT_TIMEOUT);
      httpURLConnection.setReadTimeout(READ_TIMEOUT);
    } catch (Exception e) {
      throw new ServiceException("failed to initiallize");
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public String post(Map<String, Object> headers, Object parameters) throws ServiceException {
    // TODO Auto-generated method stub

    try {
      httpURLConnection.setRequestMethod(Constants.PARAM_METHOD_POST);
      httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

      // set request header
      if (headers != null) {
        for (Map.Entry<String, Object> header : headers.entrySet()) {
          httpURLConnection.setRequestProperty(header.getKey(), header.getValue().toString());
        }
      }

      String body = "";
      if (parameters != null) {
        if (httpURLConnection.getRequestProperty("Content-Type").contains("form")) {
          // set request parameter
          StringBuilder bodyBuilder = new StringBuilder();

          Map<String, Object> parameterMap = (Map<String, Object>) parameters;

          if (parameterMap != null && parameterMap.size() > 0) {
            for (Map.Entry<String, Object> parameter : parameterMap.entrySet()) {
              bodyBuilder.append(parameter.getKey().toString());
              bodyBuilder.append("=");
              bodyBuilder.append(parameter.getValue().toString());
              bodyBuilder.append("&");
            }
          }
          body = bodyBuilder.toString();
          body = body.substring(0, body.length() - 1);
        } else if (httpURLConnection.getRequestProperty("Content-Type").contains("json")) {

          body = (String) parameters;

        }
      }
      OutputStream outputStream = httpURLConnection.getOutputStream();
      outputStream.write(body.toString().getBytes());

      outputStream.flush();
      outputStream.close();

      int responseCode = httpURLConnection.getResponseCode();
      if (responseCode != Constants.HTTP_STATUS_CODE_200) {
        throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNDEFINED);
      }

      response = getResponse(httpURLConnection.getInputStream());

      if (response == "") {
        throw new ServiceException("failed to get response");
      }

      httpURLConnection.disconnect();

    } catch (

    MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return response;
  }

  @Override
  public String get(Map<String, Object> headers) throws ServiceException {

    try {
      httpURLConnection.setRequestMethod(Constants.PARAM_METHOD_GET);

      // set request header
      if (headers != null) {
        for (Map.Entry<String, Object> header : headers.entrySet()) {
          httpURLConnection.setRequestProperty(header.getKey(), header.getValue().toString());
        }
      }

      int responseCode = httpURLConnection.getResponseCode();
      if (responseCode != Constants.HTTP_STATUS_CODE_200) {
        System.out.println(responseCode);
        throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNDEFINED);
      }

      response = getResponse(httpURLConnection.getInputStream());

      if (response == "") {
        throw new ServiceException("failed to get response");
      }

      httpURLConnection.disconnect();

    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return response;
  }

  private String getResponse(InputStream inputStream) throws UnsupportedEncodingException, IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Constants.PARAM_UTF_8));
    StringBuffer response = new StringBuffer();
    String inputLine;

    while ((inputLine = reader.readLine()) != null) {
      response.append(inputLine);
    }
    reader.close();
    return response.toString();
  }
}


