package io.rezoome.manager.auth;

import java.util.HashMap;
import java.util.Map;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.core.annotation.ManagerType;
import io.rezoome.lib.json.JSON;
import io.rezoome.manager.AbstractManager;
import io.rezoome.manager.network.entity.RequestPacketEntity;
import io.rezoome.manager.network.entity.RequestRegistrationArgsEntity;
import io.rezoome.manager.network.entity.ResponsePacketEntity;
import io.rezoome.manager.provider.ManagerProvider;

@ManagerType("Auth")
public class AuthManagerImpl extends AbstractManager implements AuthManager {

  private static class Singleton {
    private static final AuthManager instance = new AuthManagerImpl();
  }

  public static AuthManager getInstance() {
    return Singleton.instance;
  }
  
  
  @Override
  public void initialize(InitialEvent event) {
    // TODO Auto-generated method stub
    
    // MID
    
    this.authentication();
    setPrepared();
  }

  @Override
  public void initializeOnThread(InitialEvent event) {
    // TODO Auto-generated method stub
    
    
  }

  @Override
  public void authentication() {
    try {
      Map<String, Object> headers = new HashMap<String, Object>();

      RequestPacketEntity requestEntity = new RequestPacketEntity();
      requestEntity.setCmd("registration");

      RequestRegistrationArgsEntity argsEntity = new RequestRegistrationArgsEntity();
      argsEntity.setOrgCode("code001");
      argsEntity.setOrgPasscode("passcode");
      argsEntity.setOrgName("orgName");

      requestEntity.setArgs(argsEntity);

      System.out.println(JSON.toJson(requestEntity));
      headers.put("Content-type", "application/json");

      
      String result = ManagerProvider.network().getHttpConnecter().sendPost("http://localhost:3000/agent/reg",
          headers, JSON.toJson(requestEntity));
      
      ResponsePacketEntity responseEntity = JSON.fromJson(result, ResponsePacketEntity.class);
      System.out.println(responseEntity.toString());

      if (!"200".equals(responseEntity.getCode())) {
        System.out.println("CONNECTION ERROR!");
        System.exit(1);
      } else {
      
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

}
