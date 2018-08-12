package io.rezoome.manager.auth;

import java.net.HttpURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.rezoome.constants.Constants;
import io.rezoome.constants.GlobalEntity;
import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.core.annotation.ManagerType;
import io.rezoome.exception.ServiceException;
import io.rezoome.lib.json.JSON;
import io.rezoome.manager.AbstractManager;
import io.rezoome.manager.auth.entity.RequestAuthenticationArgsEntity;
import io.rezoome.manager.auth.entity.ResponseAuthenticationArgsEntity;
import io.rezoome.manager.keyprovision.entity.RequestKeyProvisionArgsEntity;
import io.rezoome.manager.network.entity.request.RequestPacket;
import io.rezoome.manager.network.entity.request.RequestPacketEntity;
import io.rezoome.manager.network.entity.response.ResponsePacket;
import io.rezoome.manager.property.PropertyEnum;
import io.rezoome.manager.provider.ManagerProvider;

@ManagerType(value = Constants.COMMAND_AUTH, initPriority = 15)
public class AuthManagerImpl extends AbstractManager implements AuthManager {

  private final Logger LOG = LoggerFactory.getLogger(Constants.AGENT_LOG);

  private String orgId;
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
  public void initialize(InitialEvent event) throws ServiceException {
    // TODO Auto-generated method stub

    try {
      orgId = ManagerProvider.property().getProperty(PropertyEnum.ORG_ID);
      orgName = ManagerProvider.property().getProperty(PropertyEnum.ORG_NAME);
      orgPasscode = ManagerProvider.property().getProperty(PropertyEnum.ORG_PASSCODE);

      
      
      packet = new RequestPacket(ManagerProvider.property().getProperty(PropertyEnum.PORTAL_URL, false), JSON.toJson(convertAuthPacketEntity()));

      if (authentication()) {
        LOG.info("{} Init Complete", this.getClass());
        setPrepared();
      } else {
        // throw new ServiceException("Fail to auth.");
      }
    } catch (Exception e) {
      // throw new ServiceException("Fail to initialize auth manager.", e);
    }
    
  }

  @Override
  public void initializeOnThread(InitialEvent event) {
    // TODO Auto-generated method stub
  }

  @Override
  public boolean authentication() throws ServiceException {
    try {
      ResponsePacket responseEntity = ManagerProvider.network().request(packet);
      if (responseEntity != null ) {
        ResponseAuthenticationArgsEntity args = (ResponseAuthenticationArgsEntity) responseEntity.getResult();

        // TODO AUTH 결과에 따른 처리
        GlobalEntity.TOKEN = args.getToken();
        if ("N".equals(args.getKeyStored())) {
          // keyProvision
          packet = new RequestPacket("", JSON.toJson(convertAuthPacketEntity()));
          responseEntity = ManagerProvider.network().request(packet);
        }
        return true;
      } else {
        return false;
      }
    } catch (Exception e) {
      throw new ServiceException("file to connect Portal server", e);
    }
  }

  private RequestPacketEntity convertAuthPacketEntity() {
    RequestPacketEntity requestEntity = new RequestPacketEntity();
    requestEntity.setCmd(Constants.COMMAND_AUTH);

    RequestAuthenticationArgsEntity argsEntity = new RequestAuthenticationArgsEntity();
    argsEntity.setOrgId(orgId);
    argsEntity.setOrgPasscode(orgPasscode);
    argsEntity.setOrgName(orgName);
    requestEntity.setArgs(argsEntity);

    return requestEntity;
  }


}
