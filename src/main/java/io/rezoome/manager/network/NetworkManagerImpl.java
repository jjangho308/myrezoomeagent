package io.rezoome.manager.network;

import io.rezoome.core.ServiceInitializer.InitialEvent;

import io.rezoome.manager.network.entity.RequestPacketEntity;
import io.rezoome.manager.network.entity.ResponsePacketEntity;
import io.rezoome.manager.network.http.HttpConnecter;
import io.rezoome.manager.network.http.HttpManager;
import io.rezoome.manager.network.http.HttpsConnecter;

public class NetworkManagerImpl implements NetworkManager {

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
    //httpConnecter = HttpConnecter.get
    HttpConnecter.getInstance();
    HttpsConnecter.getInstance();
    
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
  public ResponsePacketEntity request(RequestPacketEntity entity) {
    // TODO Auto-generated method stub
    if("HTTP".equals(entity.getProtocol().toUpperCase())){
      //HttpConnecter.getInstance().post(headers, parameters);
    }else if("HTTPS".equals(entity.getProtocol().toUpperCase())){
      //HttpsConnecter.getInstance().post(headers, parameters);
    }
    return null;
  }

}
