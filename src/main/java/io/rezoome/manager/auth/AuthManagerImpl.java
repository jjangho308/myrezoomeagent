package io.rezoome.manager.auth;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.core.annotation.ManagerType;
import io.rezoome.entity.RzmRsltEntity;
import io.rezoome.manager.AbstractManager;
import io.rezoome.manager.network.entity.RequestPacketEntity;
import io.rezoome.manager.network.entity.ResponsePacketEntity;
import io.rezoome.manager.provider.ManagerProvider;

@ManagerType(value = "Auth", initPriority = 40)
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
    // this.authentication();
    setPrepared();
  }

  @Override
  public void initializeOnThread(InitialEvent event) {
    // TODO Auto-generated method stub

  }

  @Override
  public void authentication() {
    try {
      RzmRsltEntity entity = new RzmRsltEntity();
      RequestPacketEntity requestEntity = ManagerProvider.network().convert(entity, "registration");
      ResponsePacketEntity responseEntity = ManagerProvider.network().request(requestEntity, "https", "post");
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("file to connect Portal server");
      // System.exit(1);
    }
  }

}
