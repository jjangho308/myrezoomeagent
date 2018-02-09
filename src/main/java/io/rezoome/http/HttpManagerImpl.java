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

import javax.net.ssl.HttpsURLConnection;

import io.rezoome.constants.Constants;
import io.rezoome.constants.ErrorCodeConstants;
import io.rezoome.exception.ServiceException;

public class HttpManagerImpl implements HttpManager {

  private HttpURLConnection httpURLConnection;
  private HttpsURLConnection httpsURLConnection;

  private final int CONNECT_TIMEOUT = 10000;
  private final int READ_TIMEOUT = 10000;
  private String response = "";

  public HttpManagerImpl(String endpoint, String protocol) {
    // TODO Auto-generated constructor stub

    URL url;
    try {
      url = new URL(endpoint);
      if ("HTTP".equalsIgnoreCase(protocol)) {
        httpURLConnection = (HttpURLConnection) url.openConnection();

        httpURLConnection.setRequestMethod(Constants.PARAM_METHOD_POST);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);

        httpURLConnection.setConnectTimeout(CONNECT_TIMEOUT);
        httpURLConnection.setReadTimeout(READ_TIMEOUT);
      } else if ("HTTPS".equalsIgnoreCase(protocol)) {
        httpsURLConnection = (HttpsURLConnection) url.openConnection();

        httpsURLConnection.setRequestMethod(Constants.PARAM_METHOD_POST);
        httpsURLConnection.setDoInput(true);
        httpsURLConnection.setDoOutput(true);

        httpsURLConnection.setConnectTimeout(CONNECT_TIMEOUT);
        httpsURLConnection.setReadTimeout(READ_TIMEOUT);
      }
    } catch (Exception e) {
      throw new ServiceException("failed to initiallize http/s");
    }
  }

  @Override
  public String callHttpPostBasic(Map<String, Object> headers, Map<String, Object> parameters) throws ServiceException {

    try {
      httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

      // set request header
      if (headers != null) {
        for (Map.Entry<String, Object> header : headers.entrySet()) {
          httpURLConnection.setRequestProperty(header.getKey(), header.getValue().toString());
        }
      }

      // set request parameter
      StringBuilder bodyBuilder = new StringBuilder();
      String body;
      if (parameters != null && parameters.size() > 0) {
        for (Map.Entry<String, Object> parameter : parameters.entrySet()) {
          bodyBuilder.append(parameter.getKey().toString());
          bodyBuilder.append("=");
          bodyBuilder.append(parameter.getValue().toString());
          bodyBuilder.append("&");
        }
      }
      body = bodyBuilder.toString();
      body = body.substring(0, body.length() - 1);

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

    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return response;
  }

  @Override
  public String callHttpPostJson(Map<String, Object> headers, String bodyJson) throws ServiceException {

    try {
      httpURLConnection.setRequestProperty("Content-Type", "application/json");

      // set request header
      if (headers != null) {
        for (Map.Entry<String, Object> header : headers.entrySet()) {
          httpURLConnection.setRequestProperty(header.getKey(), header.getValue().toString());
        }
      }

      OutputStream outputStream = httpURLConnection.getOutputStream();
      outputStream.write(bodyJson.toString().getBytes());

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

    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return response;
  }

  @Override
  public String callHttpGet(Map<String, Object> headers) {

    try {
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



  @Override
  public String callHttpsPostBasic(Map<String, Object> headers, Map<String, Object> parameters) throws ServiceException {

    try {
      httpsURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

      // set request header
      if (headers != null) {
        for (Map.Entry<String, Object> header : headers.entrySet()) {
          httpsURLConnection.setRequestProperty(header.getKey(), header.getValue().toString());
        }
      }

      // set request parameter
      StringBuilder bodyBuilder = new StringBuilder();
      String body;
      if (parameters != null && parameters.size() > 0) {
        for (Map.Entry<String, Object> parameter : parameters.entrySet()) {
          bodyBuilder.append(parameter.getKey().toString());
          bodyBuilder.append("=");
          bodyBuilder.append(parameter.getValue().toString());
          bodyBuilder.append("&");
        }
      }
      body = bodyBuilder.toString();
      body = body.substring(0, body.length() - 1);

      OutputStream outputStream = httpsURLConnection.getOutputStream();
      outputStream.write(body.toString().getBytes());

      outputStream.flush();
      outputStream.close();


      int responseCode = httpsURLConnection.getResponseCode();
      if (responseCode != Constants.HTTP_STATUS_CODE_200) {
        throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNDEFINED);
      }

      response = getResponse(httpsURLConnection.getInputStream());

      if (response == "") {
        throw new ServiceException("failed to get response");
      }

      httpsURLConnection.disconnect();

    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return response;
  }

  @Override
  public String callHttpsPostJson(Map<String, Object> headers, String bodyJson) throws ServiceException {

    try {
      httpsURLConnection.setRequestProperty("Content-Type", "application/json");

      // set request header
      if (headers != null) {
        for (Map.Entry<String, Object> header : headers.entrySet()) {
          httpsURLConnection.setRequestProperty(header.getKey(), header.getValue().toString());
        }
      }

      OutputStream outputStream = httpsURLConnection.getOutputStream();
      outputStream.write(bodyJson.toString().getBytes());

      outputStream.flush();
      outputStream.close();

      int responseCode = httpsURLConnection.getResponseCode();
      if (responseCode != Constants.HTTP_STATUS_CODE_200) {
        throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNDEFINED);
      }

      response = getResponse(httpsURLConnection.getInputStream());

      if (response == "") {
        throw new ServiceException("failed to get response");
      }

      httpsURLConnection.disconnect();

    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return response;
  }

  @Override
  public String callHttpsGet(Map<String, Object> headers) throws ServiceException {

    try {
      // set request header
      if (headers != null) {
        for (Map.Entry<String, Object> header : headers.entrySet()) {
          httpsURLConnection.setRequestProperty(header.getKey(), header.getValue().toString());
        }
      }

      int responseCode = httpsURLConnection.getResponseCode();
      if (responseCode != Constants.HTTP_STATUS_CODE_200) {
        System.out.println(responseCode);
        throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNDEFINED);
      }

      response = getResponse(httpsURLConnection.getInputStream());

      if (response == "") {
        throw new ServiceException("failed to get response");
      }

      httpsURLConnection.disconnect();

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
    return reader.toString();
  }

}
