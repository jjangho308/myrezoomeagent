

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import io.rezoome.entity.RzmRsltEntity;
import io.rezoome.exception.ServiceException;
import io.rezoome.lib.json.JSON;
import io.rezoome.manager.amq.AMQMessageEntity;
import io.rezoome.manager.amq.AMQMessageHandlerImpl;
import io.rezoome.manager.network.entity.RequestPacket;
import io.rezoome.manager.network.entity.request.RequestArgsEntity;
import io.rezoome.manager.network.entity.request.RequestPacketEntity;
import io.rezoome.manager.network.entity.request.RequestSearchRecordArgsEntity;
import io.rezoome.manager.network.entity.request.RequestSearchRecordsEntity;
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
    // throw new ServiceException("error");

    InitialEvent event = InitialEvent.RUNTIME;
    ServiceInitializer.initialize(event);
    // ManagerProvider.property().initialize(InitialEvent.RUNTIME);
    // ManagerProvider.clsarrange().initialize(InitialEvent.RUNTIME);
    // ManagerProvider.pushcommand().initialize(InitialEvent.RUNTIME);

    AMQMessageEntity msg = this.AMQMessageParseTest();
    AMQMessageHandlerImpl.getInstance().handleMessage(msg);
  }

  public AMQMessageEntity AMQMessageParseTest() {

    AMQMessageEntity entity = null;
    entity = new AMQMessageEntity();
    String msg = "{\r\n" +
        " cmd : \"Search\",\r\n" +
        " mid : \"leifajlsif\",\r\n" +
        " token : \"welajslkdjfasdf\",\r\n" +
        " args : {\r\n" +
        " username : 'ATS',\r\n" +
        " birth : '1987-03-18',\r\n" +
        " gender : 1,\r\n" +
        " phone : '010-6464-4554',\r\n" +
        " ci : '123456789abcdeftg',\r\n" +
        " email : 'exle@nate.com'\r\n" +
        " }\r\n" +
        "}";

    try {
      entity = JSON.fromJson(msg, AMQMessageEntity.class);
    } catch (Exception e) {
      e.printStackTrace();
    }
    // entity = JSON.fromJson(msg, AMQMessageEntity.class);
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
    try {
      ResponsePacketEntity responseEntity = ManagerProvider.network().request(packet);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }



  @Test
  public void httpClientTest2() throws IOException {
    // URL url = new
    // URL("http://devportalalb-1075047289.ap-northeast-2.elb.amazonaws.com:3000/agent/");
    URL url = new URL("https://www.google.com/accounts/ClientLogin?accountType=GOOGLE&Email=im.pkingkong@gmail.com&Passwd=phu1224!");

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

  private String getResponse(InputStream inputStream) throws UnsupportedEncodingException,
      IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,
        Constants.PARAM_UTF_8));
    StringBuffer response = new StringBuffer();
    String inputLine;

    while ((inputLine = reader.readLine()) != null) {
      response.append(inputLine);
    }
    reader.close();
    return reader.toString();
  }


  @Test
  public void throwTest() {
    try {
      throwError();
    } catch (Exception e) {
      throw new ServiceException(e.getMessage(), e);
    }
  }

  @Test
  public void throwTest2() {
    try {
      throwError();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void throwError() throws Exception {
    int[] arr = new int[5];
    try {
      arr[6] = 1;
    } catch (Exception e) {
      throw new ServiceException("parsing error", e);
    }
  }

  public void throwError2() throws Exception {
    int[] arr = new int[5];
    arr[6] = 1;
  }

  @Test
  public void httpsClientTest() {
    ManagerProvider.network().initialize(InitialEvent.RUNTIME);

    RequestPacketEntity requestEntity = new RequestPacketEntity();

    requestEntity.setCmd("SearchResult");
    RequestSearchRecordArgsEntity argsEntity = new RequestSearchRecordArgsEntity();
    argsEntity.setEncryptedData("setEncryptedData");
    argsEntity.setEncryptedKey("setEncryptedKey");
    argsEntity.setHashedData("setHashedData");

    requestEntity.setArgs(argsEntity);

    // RequestPacket packet = new RequestPacket("", JSON.toJson(requestEntity));
    RequestPacket packet = new RequestPacket("", null);
    try {
      ResponsePacketEntity responseEntity = ManagerProvider.network().request(packet);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Test
  public void hashTest() {
    String hash = "";
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      md.update("sdfsadfasefasefasefasefasefasef".toString().getBytes());
      byte byteData[] = md.digest();
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < byteData.length; i++) {
        sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
      }
      hash = sb.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }

    System.out.println("hash : " + hash);
  }

  @Test
  public void crypto() {
    ManagerProvider.crypto().initialize(InitialEvent.RUNTIME);

    String data = "test data";

    // AES TEST
    String aesKey = ManagerProvider.crypto().generateAES();
    String iv = ManagerProvider.crypto().generateIV();
    String encData = ManagerProvider.crypto().encryptAES(data, aesKey, iv);
    System.out.println("encData : " + encData);
    String decData = ManagerProvider.crypto().decryptAES(encData, aesKey, iv);
    System.out.println("decData : " + decData);

    // RSA TEST
    Map<String, String> keys = ManagerProvider.crypto().generateRSA();
    String publicKey = keys.get("PUBLIC_KEY");
    String privateKey = keys.get("PRIVATE_KEY");
    System.out.println("publicKey : " + publicKey);
    System.out.println("privateKey : " + privateKey);
    encData = ManagerProvider.crypto().encryptRSA(data, publicKey);
    System.out.println("encData : " + encData);
    decData = ManagerProvider.crypto().decryptRSA(encData, privateKey);
    System.out.println("decData : " + decData);
  }


  @Test
  public void searchRecordJsonTrasnTest() {
    RequestPacketEntity requestPacket = new RequestPacketEntity();
    RzmRsltEntity rzmResultEntity = new RzmRsltEntity();
    rzmResultEntity.setOrgCode("ORG001");
    rzmResultEntity.setEncKey("");
    rzmResultEntity.setEncIv("");

    String aesKey = ManagerProvider.crypto().generateAES();
    String iv = ManagerProvider.crypto().generateIV();
    String encKey = "encKey";
    String encIv = "encIv";

    List<RequestArgsEntity> records = new ArrayList<RequestArgsEntity>();
    RequestSearchRecordsEntity record = null;

    // 1
    record = new RequestSearchRecordsEntity();
    String encData = ManagerProvider.crypto().encryptAES("test", aesKey, iv);
    String hashData = ManagerProvider.crypto().hash("test");
    record.setEncData(encData);
    record.setHashData(hashData);
    record.setStored("Y");
    records.add(record);

    // 2
    record = new RequestSearchRecordsEntity();
    encData = ManagerProvider.crypto().encryptAES("test2", aesKey, iv);
    hashData = ManagerProvider.crypto().hash("test2");
    record.setEncData(encData);
    record.setHashData(hashData);
    record.setStored("N");
    records.add(record);


    rzmResultEntity.setEncKey(encKey);
    rzmResultEntity.setEncIv(encIv);
    rzmResultEntity.setRecords(records);
    requestPacket.setCode("USER_EXIST_AND_DATA_EXIST");
    requestPacket.setArgs(rzmResultEntity);
    requestPacket.setCmd("cmd");
    requestPacket.setMid("mid");

    System.out.println(JSON.toJson(requestPacket));
  }
}


