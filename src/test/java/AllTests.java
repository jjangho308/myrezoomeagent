

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.junit.Before;
import org.junit.Test;

import io.rezoome.constants.Constants;
import io.rezoome.core.ServiceInitializer;
import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.lib.json.JSON;
import io.rezoome.manager.amq.AMQMessageEntity;
import io.rezoome.manager.amq.AMQMessageHandlerImpl;
import io.rezoome.manager.network.entity.RequestPacket;
import io.rezoome.manager.network.entity.request.RequestPacketEntity;
import io.rezoome.manager.network.entity.request.RequestSearchRecordArgsEntity;
import io.rezoome.manager.network.entity.response.ResponsePacketEntity;
import io.rezoome.manager.provider.ManagerProvider;
import junit.framework.TestSuite;

public class AllTests extends TestSuite {



  static {

  }

  @Before
  public void initialize() {
    ManagerProvider.property().initialize(InitialEvent.RUNTIME);
    ManagerProvider.clsarrange().initialize(InitialEvent.RUNTIME);
    ManagerProvider.pushcommand().initialize(InitialEvent.RUNTIME);
  }

  @Test
  public void amqMessageTest() {
    AMQMessageEntity msg = this.AMQMessageParseTest();
    System.out.println("msg : " + msg);
    AMQMessageHandlerImpl.getInstance().handleMessage(msg);
  }

  public AMQMessageEntity AMQMessageParseTest() {
    AMQMessageEntity entity = null;
    entity = new AMQMessageEntity();
    String msg = "{\r\n" +
        "  cmd : \"Search\",\r\n" +
        "  mid : \"leifajlsif\",\r\n" +
        "  token : \"welajslkdjfasdf\",\r\n" +
        "  args : {\r\n" +
        "    username : 'ATS',\r\n" +
        "    birth : '1987-03-18',\r\n" +
        "    gender : 1,\r\n" +
        "    phone : '010-6464-4554',\r\n" +
        "    ci : '123456789abcdeftg',\r\n" +
        "    email : 'exle@nate.com'\r\n" +
        "  }\r\n" +
        "}";


    System.out.println(entity);
    entity = JSON.fromJson(msg, AMQMessageEntity.class);
    System.out.println(entity);
    return entity;
  }

  @Test
  public void initializeTest() {
    InitialEvent event = InitialEvent.RUNTIME;
    ServiceInitializer.initialize(event);
  }

  @Test
  public void httpClientTest() {
    ManagerProvider.network().initialize(InitialEvent.RUNTIME);

    RequestPacketEntity requestEntity = new RequestPacketEntity();

    requestEntity.setCmd("SearchResult");
    RequestSearchRecordArgsEntity argsEntity = new RequestSearchRecordArgsEntity();
    argsEntity.setEncryptedData("setEncryptedData");
    argsEntity.setEncryptedKey("setEncryptedKey");
    argsEntity.setHashedData("setHashedData");

    requestEntity.setArgs(argsEntity);

    RequestPacket packet = new RequestPacket("/", JSON.toJson(requestEntity));
    ResponsePacketEntity responseEntity = ManagerProvider.network().request(packet);
  }



  @Test
  public void httpClientTest2() throws IOException {
    URL url = new URL("http://devportalalb-1075047289.ap-northeast-2.elb.amazonaws.com:3000/agent/");
    // URL url = new
    // URL("https://www.google.com/accounts/ClientLogin?accountType=GOOGLE&Email=im.pkingkong@gmail.com&Passwd=phu1224!");

    try {
      HttpURLConnection connection = (HttpURLConnection) url
          .openConnection();

      if (connection instanceof HttpsURLConnection) {

        SSLContext ctx = SSLContext.getInstance("TLS");
        ctx.init(new KeyManager[0],
            new TrustManager[] { new DefaultTrustManager() },
            new SecureRandom());
        final SSLSocketFactory sslSocketFactory = ctx
            .getSocketFactory();
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

      connection.setConnectTimeout(60000);
      connection.setReadTimeout(60000);

      connection.connect();

      String response = getResponse(connection.getInputStream());
      System.out.println(response);

    } catch (Exception e) {
      e.printStackTrace();
    }
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


