package io.rezoome.manager.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map.Entry;

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
import io.rezoome.manager.network.entity.request.RequestPacket;
import io.rezoome.manager.network.entity.request.RequestPacketEntity;
import io.rezoome.manager.network.entity.response.ResponsePacket;
import io.rezoome.manager.property.PropertyEnum;
import io.rezoome.manager.provider.ManagerProvider;

@ManagerType(value = Constants.MANAGER_TYPE_NETWORK, initPriority = 10)
public class NetworkManagerImpl extends AbstractManager implements NetworkManager {

  private Logger LOG = LoggerFactory.getLogger(Constants.AGENT_LOG);

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
    PORTAL_URL = ManagerProvider.property().getProperty(PropertyEnum.PORTAL_URL);
    CONNECT_TIMEOUT = Integer.parseInt(ManagerProvider.property().getProperty(PropertyEnum.CONNECT_TIMEOUT));
    READ_TIMEOUT = Integer.parseInt(ManagerProvider.property().getProperty(PropertyEnum.READ_TIMEOUT));
    RETRIES = Integer.parseInt(ManagerProvider.property().getProperty(PropertyEnum.RETRIES));
    RETRY_DELAY_SEC = Integer.parseInt(ManagerProvider.property().getProperty(PropertyEnum.RETRY_DELAY_SEC));
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
    return null;
  }

  @Override
  public ResponsePacket request(RequestPacket packet) {
    // TODO Auto-generated method stub

    int retry = 0;
    String response = null;
    HttpURLConnection connection = null;

    try {
      do {
        if (retry > 0) {
          LOG.debug("retry to connect server after {}sec, retry:{}", 3, retry);
          Thread.sleep(RETRY_DELAY_SEC);
        }
        connection = (HttpURLConnection) new URL(packet.getUrl()).openConnection();

        // if (connection instanceof HttpsURLConnection) {
        // SSLContext ctx = SSLContext.getInstance(Constants.PARAM_TLS);
        // ctx.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, new
        // SecureRandom());
        // final SSLSocketFactory sslSocketFactory = ctx.getSocketFactory();
        // SSLContext.setDefault(ctx);
        // HttpsURLConnection httpsConnection = (HttpsURLConnection) connection;
        // httpsConnection.setHostnameVerifier(new HostnameVerifier() {
        // @Override
        // public boolean verify(String arg0, SSLSession arg1) {
        // return true;
        // }
        // });
        // httpsConnection.setSSLSocketFactory(sslSocketFactory);
        // }

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
            ResponsePacket responsePacket = new ResponsePacket();
            response = getResponse(connection.getInputStream());
            connection.disconnect();
            System.out.println("response : " + response);
            // responsePacket.setResult(result);
            responsePacket = JSON.fromJson(response, ResponsePacket.class);
            LOG.debug("ReponsePacket : {}", responsePacket);
            return responsePacket;
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
    return response.toString();
  }

  // private static class DefaultTrustManager implements X509TrustManager {
  // @Override
  // public void checkClientTrusted(X509Certificate[] arg0, String arg1)
  // throws CertificateException {}
  //
  // @Override
  // public void checkServerTrusted(X509Certificate[] arg0, String arg1)
  // throws CertificateException {}
  //
  // @Override
  // public X509Certificate[] getAcceptedIssuers() {
  // return null;
  // }
  // }

  @Override
  public String createPortalUrl(String sid) {
    return this.PORTAL_URL + sid;
  }
}
