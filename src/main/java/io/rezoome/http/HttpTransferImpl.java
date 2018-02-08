package io.rezoome.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import io.rezoome.agentException.ServiceException;
import io.rezoome.constants.Constants;
import io.rezoome.constants.ErrorCodeConstants;

public class HttpTransferImpl implements HttpTrasfer {
  private HttpURLConnection httpURLConnection;
  private HttpsURLConnection httpsURLConnection;

  private final int CONNECT_TIMEOUT = 10000;
  private final int READ_TIMEOUT = 10000;

  
  @Override
  public void callHttpPostBasic(String endPoint, Map<String, Object> headers, Map<String, Object> parameters) {

    URL url;
    try {
      url = new URL(endPoint);
      httpURLConnection = (HttpURLConnection) url.openConnection();

      httpURLConnection.setRequestMethod(Constants.PARAM_METHOD_POST);
      httpURLConnection.setDoInput(true);
      httpURLConnection.setDoOutput(true);

      httpURLConnection.setConnectTimeout(CONNECT_TIMEOUT);
      httpURLConnection.setReadTimeout(READ_TIMEOUT);

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

      BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), Constants.PARAM_UTF_8));
      StringBuffer response = new StringBuffer();
      String inputLine;

      while ((inputLine = reader.readLine()) != null) {
        response.append(inputLine);
      }

      reader.close();
      httpURLConnection.disconnect();

    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void callHttpPostJson(String endpoint, Map<String, Object> headers, String bodyJson) {

    URL url;
    try {
      url = new URL(endpoint);
      httpURLConnection = (HttpURLConnection) url.openConnection();

      httpURLConnection.setRequestMethod(Constants.PARAM_METHOD_POST);
      httpURLConnection.setDoInput(true);
      httpURLConnection.setDoOutput(true);

      httpURLConnection.setConnectTimeout(CONNECT_TIMEOUT);
      httpURLConnection.setReadTimeout(READ_TIMEOUT);

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

      BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), Constants.PARAM_UTF_8));
      StringBuffer response = new StringBuffer();
      String inputLine;

      while ((inputLine = reader.readLine()) != null) {
        response.append(inputLine);
      }

      reader.close();
      httpURLConnection.disconnect();

    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void callHttpGet(String endPoint, Map<String, Object> headers){

    URL url;
    try {
      url = new URL(endPoint);
      httpURLConnection = (HttpURLConnection) url.openConnection();

      httpURLConnection.setRequestMethod(Constants.PARAM_METHOD_GET);
      httpURLConnection.setDoInput(true);
      httpURLConnection.setDoOutput(true);

      httpURLConnection.setConnectTimeout(CONNECT_TIMEOUT);
      httpURLConnection.setReadTimeout(READ_TIMEOUT);

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

      BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), Constants.PARAM_UTF_8));
      StringBuffer response = new StringBuffer();
      String inputLine;

      while ((inputLine = reader.readLine()) != null) {
        response.append(inputLine);
      }

      reader.close();
      httpURLConnection.disconnect();

    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }



  @Override
  public void callHttpsPostBasic(String endPoint, Map<String, Object> headers, Map<String, Object> parameters)  {

    URL url;
    try {
      url = new URL(endPoint);
      httpsURLConnection = (HttpsURLConnection) url.openConnection();

      httpsURLConnection.setRequestMethod(Constants.PARAM_METHOD_POST);
      httpsURLConnection.setDoInput(true);
      httpsURLConnection.setDoOutput(true);

      httpsURLConnection.setConnectTimeout(CONNECT_TIMEOUT);
      httpsURLConnection.setReadTimeout(READ_TIMEOUT);

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

      BufferedReader reader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream(), Constants.PARAM_UTF_8));
      StringBuffer response = new StringBuffer();
      String inputLine;

      while ((inputLine = reader.readLine()) != null) {
        response.append(inputLine);
      }

      reader.close();
      httpsURLConnection.disconnect();

    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void callHttpsPostJson(String endpoint, Map<String, Object> headers, String bodyJson)  {

    URL url;
    try {
      url = new URL(endpoint);
      httpsURLConnection = (HttpsURLConnection) url.openConnection();

      httpsURLConnection.setRequestMethod(Constants.PARAM_METHOD_POST);
      httpsURLConnection.setDoInput(true);
      httpsURLConnection.setDoOutput(true);

      httpsURLConnection.setConnectTimeout(CONNECT_TIMEOUT);
      httpsURLConnection.setReadTimeout(READ_TIMEOUT);

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

      BufferedReader reader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream(), Constants.PARAM_UTF_8));
      StringBuffer response = new StringBuffer();
      String inputLine;

      while ((inputLine = reader.readLine()) != null) {
        response.append(inputLine);
      }

      reader.close();
      httpsURLConnection.disconnect();

    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void callHttpsGet(String endPoint, Map<String, Object> headers){

    URL url;
    try {
      url = new URL(endPoint);
      httpsURLConnection = (HttpsURLConnection) url.openConnection();

      httpsURLConnection.setRequestMethod(Constants.PARAM_METHOD_GET);
      httpsURLConnection.setDoInput(true);
      httpsURLConnection.setDoOutput(true);

      httpsURLConnection.setConnectTimeout(CONNECT_TIMEOUT);
      httpsURLConnection.setReadTimeout(READ_TIMEOUT);

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

      BufferedReader reader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream(), Constants.PARAM_UTF_8));
      StringBuffer response = new StringBuffer();
      String inputLine;

      while ((inputLine = reader.readLine()) != null) {
        response.append(inputLine);
      }

      reader.close();
      httpsURLConnection.disconnect();

    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
