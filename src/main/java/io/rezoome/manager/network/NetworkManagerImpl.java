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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

  private Logger LOG = LoggerFactory.getLogger("AGENT_LOG");

  private int CONNECT_TIMEOUT;
  private int READ_TIMEOUT;
  private int RETRIES;
  private int RETRY_DELAY_SEC;

  private String PORTAL_URL;

  private static class Singleton {
    private static final NetworkManager instance = new NetworkManagerImpl();
  }

  public static NetworkManager getInstance() {
    return Singleton.instance;
  }

  @Override
  public void initialize(InitialEvent event) {
    // TODO Auto-generated method stub
    PORTAL_URL = ManagerProvider.property().getProperty(PropertyEnum.PORTAL_URL, true);
    CONNECT_TIMEOUT = Integer.parseInt(ManagerProvider.property().getProperty(PropertyEnum.CONNECT_TIMEOUT, true));
    READ_TIMEOUT = Integer.parseInt(ManagerProvider.property().getProperty(PropertyEnum.READ_TIMEOUT, true));
    RETRIES = Integer.parseInt(ManagerProvider.property().getProperty(PropertyEnum.RETRIES, true));
    RETRY_DELAY_SEC = Integer.parseInt(ManagerProvider.property().getProperty(PropertyEnum.RETRY_DELAY_SEC, true));
    LOG.info("{} Init Complete", this.getClass());
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
    return requestEntity;
  }

  @Override
  public ResponsePacketEntity request(RequestPacket packet) throws Exception {
    // TODO Auto-generated method stub

    int retry = 0;
    String response = null;
    HttpURLConnection connection = null;

    if (packet.getData() != null)
      LOG.debug("RequestPacket : {}", packet.getData().toString());

    try {
      do {
        if (retry > 0) {
          LOG.debug("retry to connect server after {}sec, retry:{}", 3, retry);
          Thread.sleep(RETRY_DELAY_SEC);
        }
        connection = (HttpURLConnection) new URL(PORTAL_URL + packet.getSid()).openConnection();

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

        switch (connection.getResponseCode()) {
          case HttpURLConnection.HTTP_OK:
            response = getResponse(connection.getInputStream());
            LOG.debug("ResponsePacket : {}", response);
            connection.disconnect();
            return JSON.fromJson(response, ResponsePacketEntity.class);
          // case HttpURLConnection.HTTP_GATEWAY_TIMEOUT:
          // LOG.error("response code {}", HttpURLConnection.HTTP_GATEWAY_TIMEOUT);
          // break;
          // case HttpURLConnection.HTTP_UNAVAILABLE:
          // LOG.error("response code {}", HttpURLConnection.HTTP_UNAVAILABLE);
          // break;
          // case HttpURLConnection.HTTP_UNAUTHORIZED:
          // LOG.error("response code {}", HttpURLConnection.HTTP_UNAUTHORIZED);
          // break;
          default:
            LOG.error("Unknown response code {}", connection.getResponseCode());
            break;
        }
        connection.disconnect();
        retry++;
      } while (retry < RETRIES);
    } catch (Exception e) {
      throw new ServiceException(ErrorCodeConstants.ERROR_CODE_FAIL_TO_CONNECT_PORTAL_SERVER, e);
    }
    return null;
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
