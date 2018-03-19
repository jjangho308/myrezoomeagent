package io.rezoome.manager.network.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.rezoome.constants.Constants;
import io.rezoome.constants.ErrorCodeConstants;
import io.rezoome.exception.ServiceException;
import io.rezoome.lib.json.JSON;
import io.rezoome.manager.network.entity.RequestObject;
import io.rezoome.manager.network.entity.ResponsePacketEntity;

public class HttpClientImpl implements HttpClient {

  private final int CONNECT_TIMEOUT = 10000;
  private final int READ_TIMEOUT = 10000;

  @Override
  public ResponsePacketEntity request(RequestObject obj) {

    String response = null;

    try {
      HttpURLConnection connection = (HttpURLConnection) new URL(obj.getUrl()).openConnection();

      if (connection instanceof HttpsURLConnection) {
        SSLContext ctx = SSLContext.getInstance("TLS");
        ctx.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, new SecureRandom());
        final SSLSocketFactory sslSocketFactory = ctx.getSocketFactory();
        SSLContext.setDefault(ctx);
        HttpsURLConnection httpsConnection = (HttpsURLConnection) connection;
        httpsConnection.setHostnameVerifier(new HostnameVerifier() {
          @Override
          public boolean verify(String arg0, SSLSession arg1) {
            return true;
          }
        });
        httpsConnection.setSSLSocketFactory(sslSocketFactory);
      }

      connection.setDoOutput(true);

      connection.setConnectTimeout(CONNECT_TIMEOUT);
      connection.setReadTimeout(READ_TIMEOUT);

      for (Entry<String, String> entry : obj.getHeader().entrySet()) {
        connection.setRequestProperty(entry.getKey(), entry.getValue());
      }

      connection.connect();

      if (obj.getData() != null) {
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(obj.getData().toString().getBytes());

        outputStream.flush();
        outputStream.close();
      }

      response = getResponse(connection.getInputStream());

      if (Constants.HTTP_STATUS_CODE_200 != connection.getResponseCode()) {
        throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNDEFINED);
      }

      System.out.println(response);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return JSON.fromJson(response, ResponsePacketEntity.class);
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

  private static class DefaultTrustManager implements X509TrustManager {
    @Override
    public void checkClientTrusted(X509Certificate[] arg0, String arg1)
        throws CertificateException {}

    @Override
    public void checkServerTrusted(X509Certificate[] arg0, String arg1)
        throws CertificateException {}

    @Override
    public X509Certificate[] getAcceptedIssuers() {
      return null;
    }
  }

}
