

import java.io.BufferedReader;
import java.io.FileInputStream;
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
import java.util.Properties;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import io.rezoome.constants.Constants;
import io.rezoome.core.ServiceInitializer;
import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.exception.ServiceException;
import io.rezoome.lib.json.JSON;
import io.rezoome.manager.amq.AMQMessageEntity;
import io.rezoome.manager.amq.AMQMessageHandlerImpl;
import io.rezoome.manager.network.entity.request.RequestArgsEntity;
import io.rezoome.manager.network.entity.request.RequestPacketEntity;
import io.rezoome.manager.network.entity.request.RequestSearchArgsEntity;
import io.rezoome.manager.network.entity.request.RequestSearchRecordsEntity;
import io.rezoome.manager.network.entity.response.ResponseAuthenticationArgsEntity;
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
  public void DBUserExistAndDataExistTest() {
    // throw new ServiceException("error");

   /* InitialEvent event = InitialEvent.RUNTIME;
    try {
      ServiceInitializer.initialize(event);
    } catch (Throwable e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }*/

    AMQMessageEntity msg = this.AMQMessageUserExistAndDataExist();
    System.out.println(msg.toString());
    AMQMessageHandlerImpl.getInstance().handleMessage(msg);
  }
/*
  @Test
  public void DBUserNotExistTest() {
    // throw new ServiceException("error");

    InitialEvent event = InitialEvent.RUNTIME;
    try {
      ServiceInitializer.initialize(event);
    } catch (Throwable e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    AMQMessageEntity msg = this.AMQMessageUserNotExist();
    AMQMessageHandlerImpl.getInstance().handleMessage(msg);
  }

  @Test
  public void DBRequriedKeyTest() {
    // throw new ServiceException("error");

    InitialEvent event = InitialEvent.RUNTIME;
    try {
      ServiceInitializer.initialize(event);
    } catch (Throwable e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    AMQMessageEntity msg = this.AMQMessageRequiredKey();
    AMQMessageHandlerImpl.getInstance().handleMessage(msg);
  }
*/
  public AMQMessageEntity AMQMessageUserExistAndDataExist() {
    AMQMessageEntity entity = new AMQMessageEntity();
    String msg = "{\r\n" +
        " cmd : \"SearchRecord\",\r\n" +
        " mid : \"leifajlsif\",\r\n" +
        " sid : \"serverID\",\r\n" +
        " args : {\r\n" +
        " familyNameEN : \"familyNameEN\",\r\n" +
        " firstNameEN : \"firstNameEN\",\r\n" +
        " fullNameEN : \"fullNameEN\",\r\n" +
        " familyNameKO : \"familyNameKO\",\r\n" +
        " firstNameKO : \"firstNameKO\",\r\n" +
        " fullNameKO : \"fullNameKO\",\r\n" +
        " birth : '1987-03-18',\r\n" +
        " gender : 1,\r\n" +
        " phone : '010-6474-9283',\r\n" +
        " ci : '123456789abcdeftg',\r\n" +
        " pkey : 'pkey',\r\n" +
        " subIDs : ['sub1', 'sub2'],\r\n" +
        " require : ['requireKey1', 'prequireKey2'],\r\n" +
        " records : ['sub1', 'hash1']\r\n" +
        " }\r\n" +
        "}";
    
    try {
      entity = JSON.fromJson(msg, AMQMessageEntity.class);
      System.out.println("AMQMessageUserExistAndDataExist : ");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return entity;
  }

  public AMQMessageEntity AMQMessageRequiredKey() {
    AMQMessageEntity entity = null;
    entity = new AMQMessageEntity();
    String msg = "{\r\n" +
        " cmd : \"SearchRecord\",\r\n" +
        " mid : \"leifajlsif\",\r\n" +
        " token : \"welajslkdjfasdf\",\r\n" +
        " args : {\r\n" +
        " username : 'PARKHUNWOOK',\r\n" +
        " birth : '1987-03-18',\r\n" +
        " gender : 1,\r\n" +
        " phone : '010-6474-9283',\r\n" +
        " ci : '123456789abcdeftg',\r\n" +
        " email : 'exle@nate.com'\r\n" +
        " }\r\n" +
        "}";
    try {
      entity = JSON.fromJson(msg, AMQMessageEntity.class);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return entity;
  }

  public AMQMessageEntity AMQMessageUserNotExist() {
    AMQMessageEntity entity = null;
    entity = new AMQMessageEntity();
    String msg = "{\r\n" +
        " cmd : \"SearchRecord\",\r\n" +
        " mid : \"leifajlsif\",\r\n" +
        " token : \"welajslkdjfasdf\",\r\n" +
        " args : {\r\n" +
        " username : 'PARK',\r\n" +
        " birth : '1987-03-18',\r\n" +
        " gender : 1,\r\n" +
        " phone : '010-6474-9283',\r\n" +
        " ci : '123456789abcdeftg',\r\n" +
        " email : 'exle@nate.com'\r\n" +
        " }\r\n" +
        "}";
    try {
      entity = JSON.fromJson(msg, AMQMessageEntity.class);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return entity;
  }
/*
  @Test
  public void initializeTest() {
    InitialEvent event = InitialEvent.RUNTIME;
    try {
      ServiceInitializer.initialize(event);
    } catch (Throwable e) {
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
      System.out.println("EEEEeee");
      e.printStackTrace();
      // throw new ServiceException(e.getMessage(), e);
    }
  }

  public void throwError() throws ServiceException {
    int[] arr = new int[5];
    try {
      arr[6] = 1;
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServiceException("", e);
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



  public void throwError2() throws Exception {
    int[] arr = new int[5];
    arr[6] = 1;
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
  public void cryptoTest() {
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
    RequestSearchArgsEntity rzmResultEntity = new RequestSearchArgsEntity();
    rzmResultEntity.setOrgCode("ORG001");
    rzmResultEntity.setKey("");
    rzmResultEntity.setIv("");

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
    record.setData(encData);
    record.setHash(hashData);
    record.setStored("Y");
    record.setCertcode("EN");
    records.add(record);

    // 2
    record = new RequestSearchRecordsEntity();
    encData = ManagerProvider.crypto().encryptAES("test2", aesKey, iv);
    hashData = ManagerProvider.crypto().hash("test2");
    record.setData(encData);
    record.setHash(hashData);
    record.setStored("N");
    record.setCertcode("EN");
    records.add(record);


    rzmResultEntity.setKey(encKey);
    rzmResultEntity.setIv(encIv);
    rzmResultEntity.setRecords(records);
    requestPacket.setCode("USER_EXIST_AND_DATA_EXIST");
    requestPacket.setArgs(rzmResultEntity);
    requestPacket.setCmd("cmd");
    requestPacket.setMid("mid");

    System.out.println(JSON.toJson(requestPacket));
  }

  @Test
  public void fromJsonTest2() {
    // String jsonString = "{'mid':'mid##555######','cmd':'Auth','code':200, 'result':
    // {'code':'200','msg':'msg','passcode':'asdlfkjasdlkfjasdf'}}";
    String jsonAuth = "{'mid':'mid##555######','cmd':'Auth','code':200, 'result': {'code':'200','msg':'msg','token':'asdlfkjasdlkfjasdf'}}";
    String jsonHealth = "{'mid':'mid##6666######','cmd':'HealthCheck','code':200, 'result': {'code':'200','msg':'msg'}}";
    String jsonSearch = "{'mid':'mid##5444444######','cmd':'SearchRecord','code':200, 'result': {'code':'200','msg':'msg','token':'asdlfkjasdlkfjasdf'}}";

    try {
      ResponsePacketEntity packet = new ResponsePacketEntity();
      packet = JSON.fromJson(jsonAuth, ResponsePacketEntity.class);

      System.out.println(packet);
      System.out.println(((ResponseAuthenticationArgsEntity) packet.getResult()).getToken());

      packet = new ResponsePacketEntity();
      packet = JSON.fromJson(jsonHealth, ResponsePacketEntity.class);
      System.out.println(packet);

      packet = new ResponsePacketEntity();
      packet = JSON.fromJson(jsonSearch, ResponsePacketEntity.class);
      System.out.println(packet);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public interface Response {

  }

  public class ResponseImpl2 implements Response {
    private String orgcode2;
    private String name2;
    private String passcode2;
    private String id2;

    public String getOrgcode2() {
      return orgcode2;
    }

    public void setOrgcode2(String orgcode2) {
      this.orgcode2 = orgcode2;
    }

    public String getName2() {
      return name2;
    }

    public void setName2(String name2) {
      this.name2 = name2;
    }

    public String getPasscode2() {
      return passcode2;
    }

    public void setPasscode2(String passcode2) {
      this.passcode2 = passcode2;
    }

    public String getId2() {
      return id2;
    }

    public void setId2(String id2) {
      this.id2 = id2;
    }

    @Override
    public String toString() {
      return "ResponseImpl2 [orgcode2=" + orgcode2 + ", name2=" + name2 + ", passcode2=" + passcode2 + ", id2=" + id2 + "]";
    }

  }

  public class ResponseImpl implements Response {
    private String orgcode;
    private String name;
    private String passcode;

    public ResponseImpl(String orgcode, String name, String passcode) {
      super();
      this.orgcode = orgcode;
      this.name = name;
      this.passcode = passcode;
    }

    @Override
    public String toString() {
      return "ResponseImpl [orgcode=" + orgcode + ", name=" + name + ", passcode=" + passcode + "]";
    }
  }

  public class ResponsePacket {
    private String mid;
    private String cmd;
    private String code;

    @SerializedName("result")
    private Response result;

    public ResponsePacket(String mid, String cmd, String code, Response result) {
      super();
      this.mid = mid;
      this.cmd = cmd;
      this.code = code;
      this.result = result;
    }

    @Override
    public String toString() {
      return "ResponsePacket [mid=" + mid + ", cmd=" + cmd + ", code=" + code + ", result=" + result + "]";
    }
  }

  @Test
  public void getPropertiesTest() {
    String configFile = "./agent.prop";
    Properties properties;
    try {
      properties = readProperties(configFile);
      // properties.list(System.out);

      for (String key : properties.stringPropertyNames()) {
        Object value = properties.getProperty(key);
        if (!"".equals(value)) {
          System.out.println(key + " : " + value);
        }
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private synchronized Properties readProperties(String configFile) throws IOException {
    Properties tempProperties = new Properties();
    FileInputStream in = new FileInputStream(configFile);
    tempProperties.load(in);
    return tempProperties;
  }
*/
}


