package io.rezoome.manager.network;

import java.util.HashMap;
import java.util.Map;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.core.annotation.ManagerType;
import io.rezoome.entity.RzmRsltEntity;
import io.rezoome.lib.json.JSON;
import io.rezoome.manager.AbstractManager;
import io.rezoome.manager.network.entity.RequestPacketEntity;
import io.rezoome.manager.network.entity.RequestRegistrationArgsEntity;
import io.rezoome.manager.network.entity.RequestSearchResultArgsEntity;
import io.rezoome.manager.network.entity.ResponsePacketEntity;
import io.rezoome.manager.network.http.HttpConnector;
import io.rezoome.manager.network.http.HttpManager;
import io.rezoome.manager.network.http.HttpsConnector;
import io.rezoome.manager.property.PropertyEnum;
import io.rezoome.manager.provider.ManagerProvider;

@ManagerType(value = "Network", initPriority = 30)
public class NetworkManagerImpl extends AbstractManager implements NetworkManager {

  private HttpConnector httpConnector;
  private HttpsConnector httpsConnector;
  private String portalUrl;
  private Map<String, Object> headers;

  private static class Singleton {
    private static final NetworkManager instance = new NetworkManagerImpl();
  }

  public static NetworkManager getInstance() {
    return Singleton.instance;
  }

  protected HttpManager httpManager;

  @Override
  public void initialize(InitialEvent event) {
    // TODO Auto-generated method stub
    // httpConnecter = HttpConnecter.get
    // HttpConnecter.getInstance();
    // HttpsConnecter.getInstance();
    // httpConnector = new HttpConnector();
    // httpsConnector = new HttpsConnector();
    portalUrl = ManagerProvider.property().getProperty(PropertyEnum.PORTAL_URL, true);

    // Content-type is application/json
    headers = new HashMap<String, Object>();
    headers.put("Content-type", "application/json");
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
  public ResponsePacketEntity request(RequestPacketEntity entity, String protocol, String method, String path) {
    // TODO Auto-generated method stub
    System.out.println("http request : " + entity);

    String response = null;
    if ("HTTPS".equals(protocol.toUpperCase())) {
      httpsConnector = new HttpsConnector();
      if ("GET".equals(method.toUpperCase())) {
        response = httpsConnector.sendGet(portalUrl + path, headers);
      } else if ("POST".equals(method.toUpperCase())) {
        response = httpsConnector.sendPost(portalUrl + path, headers, JSON.toJson(entity));
      }
    } else {
      httpConnector = new HttpConnector();
      if ("GET".equals(method.toUpperCase())) {
        response = httpConnector.sendGet(portalUrl + path, headers);
      } else if ("POST".equals(method.toUpperCase())) {
        response = httpConnector.sendPost(portalUrl + path, headers, JSON.toJson(entity));
      }
    }

    System.out.println(response);
    ResponsePacketEntity responseEntity = JSON.fromJson(response, ResponsePacketEntity.class);
    return responseEntity;
  }

  @Override
  public RequestPacketEntity convert(RzmRsltEntity entity, String cmd) {
    RequestPacketEntity requestEntity = new RequestPacketEntity();

    if ("Registration".equals(cmd)) {
      requestEntity.setCmd("Registration");

      RequestRegistrationArgsEntity argsEntity = new RequestRegistrationArgsEntity();
      argsEntity.setOrgCode("code001");
      argsEntity.setOrgPasscode("passcode");
      argsEntity.setOrgName("orgName");
      requestEntity.setArgs(argsEntity);
    } else if ("SearchResult".equals(cmd)) {
      requestEntity.setCmd("SearchResult");
      RequestSearchResultArgsEntity argsEntity = new RequestSearchResultArgsEntity();

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
  public HttpConnector getHttpConnecter() {
    // TODO Auto-generated method stub
    return this.httpConnector;
  }

  @Override
  public HttpsConnector getHttpsConnecter() {
    // TODO Auto-generated method stub
    return this.httpsConnector;
  }
}
