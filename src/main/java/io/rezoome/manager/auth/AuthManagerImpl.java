package io.rezoome.manager.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.rezoome.constants.GlobalEntity;
import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.core.annotation.ManagerType;
import io.rezoome.lib.json.JSON;
import io.rezoome.manager.AbstractManager;
import io.rezoome.manager.network.entity.RequestPacket;
import io.rezoome.manager.network.entity.request.RequestAuthenticationArgsEntity;
import io.rezoome.manager.network.entity.request.RequestPacketEntity;
import io.rezoome.manager.network.entity.response.ResponsePacketEntity;
import io.rezoome.manager.property.PropertyEnum;
import io.rezoome.manager.provider.ManagerProvider;

@ManagerType(value = "Auth", initPriority = 40)
public class AuthManagerImpl extends AbstractManager implements AuthManager {

  private final Logger LOG = LoggerFactory.getLogger("AGENT_LOG");

  private String orgCode;
  private String orgName;
  private String orgPasscode;

  private RequestPacket packet;

  private static class Singleton {
    private static final AuthManager instance = new AuthManagerImpl();
  }

  public static AuthManager getInstance() {
    return Singleton.instance;
  }

  @Override
  public void initialize(InitialEvent event) {
    // TODO Auto-generated method stub
    orgCode = ManagerProvider.property().getProperty(PropertyEnum.ORG_CODE, true);
    orgName = ManagerProvider.property().getProperty(PropertyEnum.ORG_NAME, true);
    orgPasscode = ManagerProvider.property().getProperty(PropertyEnum.ORG_PASSCODE, true);

    packet = new RequestPacket("", JSON.toJson(convertRequestPacketEntity()));

    if (authentication()) {
      LOG.info("{} Init Complete", this.getClass());
      setPrepared();
    } else {
      LOG.debug("auth fail");
    }
  }

  @Override
  public void initializeOnThread(InitialEvent event) {
    // TODO Auto-generated method stub
  }

  @Override
  public boolean authentication() {
    try {
      ResponsePacketEntity responseEntity = ManagerProvider.network().request(packet);

      // TODO AUTH 결과에 따른 처리 ex) 토큰 설정
      GlobalEntity.token = "token";
      return true;
    } catch (Exception e) {
      LOG.debug("file to connect Portal server");
      return false;
    }
  }

  private RequestPacketEntity convertRequestPacketEntity() {
    RequestPacketEntity requestEntity = new RequestPacketEntity();
    requestEntity.setCmd("Auth");

    RequestAuthenticationArgsEntity argsEntity = new RequestAuthenticationArgsEntity();
    argsEntity.setOrgCode(orgCode);
    argsEntity.setOrgPasscode(orgPasscode);
    argsEntity.setOrgName(orgName);
    requestEntity.setArgs(argsEntity);

    return requestEntity;
  }

}
