package io.rezoome.manager.network;

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
import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.core.annotation.ManagerType;
import io.rezoome.entity.RzmRsltEntity;
import io.rezoome.exception.ServiceException;
import io.rezoome.lib.json.JSON;
import io.rezoome.manager.AbstractManager;
import io.rezoome.manager.network.entity.RequestPacket;
import io.rezoome.manager.network.entity.request.RequestAuthenticationArgsEntity;
import io.rezoome.manager.network.entity.request.RequestPacketEntity;
import io.rezoome.manager.network.entity.request.RequestSearchRecordArgsEntity;
import io.rezoome.manager.network.entity.response.ResponsePacketEntity;
import io.rezoome.manager.property.PropertyEnum;
import io.rezoome.manager.provider.ManagerProvider;

@ManagerType(value = "Network", initPriority = 30)
public class NetworkManagerImpl extends AbstractManager implements NetworkManager {

  private final int CONNECT_TIMEOUT = 10000;
  private final int READ_TIMEOUT = 10000;

  // private HttpClient httpClient;
  // private HttpConnector httpConnector;
  // private HttpsConnector httpsConnector;
  private String portalUrl;
  // private Map<String, Object> headers;

  private static class Singleton {
    private static final NetworkManager instance = new NetworkManagerImpl();
  }

  public static NetworkManager getInstance() {
    return Singleton.instance;
  }

  // protected HttpManager httpManager;

  @Override
  public void initialize(InitialEvent event) {
    // TODO Auto-generated method stub
    // httpClient = new HttpClientImpl();
    // httpConnector = new HttpConnector();
    // httpsConnector = new HttpsConnector();
    portalUrl = ManagerProvider.property().getProperty(PropertyEnum.PORTAL_URL, true);

    // Content-type is application/json
    // headers = new HashMap<String, Object>();
    // headers.put("Content-type", "application/json");
    setPrepared();
  }

  @Override
  public void initializeOnThread(InitialEvent event) {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean isPrepared() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public RequestPacketEntity convert(RzmRsltEntity entity, String cmd) {
    RequestPacketEntity requestEntity = new RequestPacketEntity();

    if ("Registration".equals(cmd)) {
      requestEntity.setCmd("Registration");

      RequestAuthenticationArgsEntity argsEntity = new RequestAuthenticationArgsEntity();
      argsEntity.setOrgCode("code001");
      argsEntity.setOrgPasscode("passcode");
      argsEntity.setOrgName("orgName");
      requestEntity.setArgs(argsEntity);
    } else if ("SearchResult".equals(cmd)) {
      requestEntity.setCmd("SearchResult");
      RequestSearchRecordArgsEntity argsEntity = new RequestSearchRecordArgsEntity();

      argsEntity.setOrgCode("code001");
      if (entity == null) {
        argsEntity.setEncryptedData(null);
        argsEntity.setEncryptedKey(null);
        argsEntity.setHashedData(null);
      } else {
        argsEntity.setEncryptedData("setEncryptedData");
        argsEntity.setEncryptedKey("setEncryptedKey");
        argsEntity.setHashedData("setHashedData");
      }
      requestEntity.setArgs(argsEntity);
    }

    System.out.println(requestEntity.toString());
    return requestEntity;
  }

  @Override
  public ResponsePacketEntity request(RequestPacketEntity entity, String protocol, String method, String path) {
    // TODO Auto-generated method stub

    // String response = null;
    //
    // if ("HTTPS".equals(protocol.toUpperCase())) {
    // if ("GET".equals(method.toUpperCase())) {
    // response = httpsConnector.sendGet(portalUrl + path, headers);
    // } else if ("POST".equals(method.toUpperCase())) {
    // response = httpsConnector.sendPost(portalUrl + path, headers, JSON.toJson(entity));
    // }
    // } else {
    // if ("GET".equals(method.toUpperCase())) {
    // response = httpConnector.sendGet(portalUrl + path, headers);
    // } else if ("POST".equals(method.toUpperCase())) {
    // response = httpConnector.sendPost(portalUrl + path, headers, JSON.toJson(entity));
    // }
    // }
    //
    // System.out.println("http response : " + response);
    // ResponsePacketEntity responseEntity = JSON.fromJson(response, ResponsePacketEntity.class);
    // return responseEntity;
    return null;
  }


  @Override
  public ResponsePacketEntity request(RequestPacket packet) {
    // TODO Auto-generated method stub
    String response = null;

    try {
      HttpURLConnection connection = (HttpURLConnection) new URL(portalUrl + packet.getSid()).openConnection();

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

      for (Entry<String, String> entry : packet.getHeader().entrySet()) {
        connection.setRequestProperty(entry.getKey(), entry.getValue());
      }

      connection.connect();

      if (packet.getData() != null) {
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(packet.getData().toString().getBytes());

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
