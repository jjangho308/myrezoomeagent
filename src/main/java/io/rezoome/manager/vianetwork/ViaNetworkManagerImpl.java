package io.rezoome.manager.vianetwork;

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
import io.rezoome.exception.ServiceException;
import io.rezoome.lib.json.JSON;
import io.rezoome.manager.AbstractManager;
import io.rezoome.manager.property.PropertyEnum;
import io.rezoome.manager.provider.ManagerProvider;
import io.rezoome.manager.vianetwork.entity.request.ViaRequestPacketEntity;
import io.rezoome.manager.vianetwork.entity.response.ViaResponsePacketEntity;


@ManagerType(value = Constants.MANAGER_TYPE_VIA, initPriority = 30)
public class ViaNetworkManagerImpl extends AbstractManager implements ViaNetworkManager{

  private Logger LOG = LoggerFactory.getLogger(Constants.AGENT_LOG);

  private static class Singleton {
    private static final ViaNetworkManager instance = new ViaNetworkManagerImpl();
  }

  public static ViaNetworkManager getInstance() {
    return Singleton.instance;
  }
    
  private String VIA_AGENCY_URL;
  private int VIA_CONNECT_TIMEOUT;
  private int VIA_READ_TIMEOUT;
  private int VIA_AGENT_RETRIES;
  private int VIA_RETRY_DELAY_SEC;
  
  @Override
  public void initialize(InitialEvent event) throws ServiceException {
    // TODO Auto-generated method stub
    // TODO Auto-generated method stub
    VIA_AGENCY_URL = ManagerProvider.property().getProperty(PropertyEnum.VIA_AGENCY_URL);
    VIA_CONNECT_TIMEOUT = Integer.parseInt(ManagerProvider.property().getProperty(PropertyEnum.VIA_CONNECT_TIMEOUT));
    VIA_READ_TIMEOUT = Integer.parseInt(ManagerProvider.property().getProperty(PropertyEnum.VIA_READ_TIMEOUT));
    VIA_AGENT_RETRIES = Integer.parseInt(ManagerProvider.property().getProperty(PropertyEnum.VIA_AGENT_RETRIES));
    VIA_RETRY_DELAY_SEC = Integer.parseInt(ManagerProvider.property().getProperty(PropertyEnum.VIA_RETRY_DELAY_SEC));
  }

  @Override
  public void initializeOnThread(InitialEvent event) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public ViaResponsePacketEntity request(ViaRequestPacketEntity packet, ViaResponsePacketEntity agencyResultArgs) {
    int retry = 0;
    String response = null;
    HttpURLConnection connection = null;

    if (packet.getData() != null) {
      LOG.debug("RequestUrl : {}", VIA_AGENCY_URL);
      LOG.debug("RequestPacket : {}", packet.getData().toString());
    }

    try {
      do {
        if (retry > 0) {
          LOG.debug("retry to connect server after {}sec, retry:{}", 3, retry);
          Thread.sleep(VIA_RETRY_DELAY_SEC);
        }
        connection = (HttpURLConnection) new URL(VIA_AGENCY_URL).openConnection();

        if (connection instanceof HttpsURLConnection) {
          SSLContext ctx = SSLContext.getInstance(Constants.PARAM_TLS);
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
        connection.setConnectTimeout(VIA_CONNECT_TIMEOUT);
        connection.setReadTimeout(VIA_READ_TIMEOUT);

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
            ViaResponsePacketEntity responsePacket = agencyResultArgs;
            response = getResponse(connection.getInputStream());
            connection.disconnect();
            System.out.println("mkresponse : " + response);
            responsePacket = JSON.fromJson(response, responsePacket.getClass());
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
      } while (retry < VIA_AGENT_RETRIES);
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
