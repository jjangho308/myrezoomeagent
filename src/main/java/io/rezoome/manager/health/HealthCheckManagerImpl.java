package io.rezoome.manager.health;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.core.annotation.ManagerType;
import io.rezoome.exception.ServiceException;
import io.rezoome.lib.json.JSON;
import io.rezoome.manager.AbstractManager;
import io.rezoome.manager.network.entity.RequestPacket;
import io.rezoome.manager.network.entity.request.RequestHealthCheckArgsEntity;
import io.rezoome.manager.network.entity.request.RequestPacketEntity;
import io.rezoome.manager.network.entity.response.ResponsePacketEntity;
import io.rezoome.manager.property.PropertyEnum;
import io.rezoome.manager.provider.ManagerProvider;

@ManagerType(value = "Health")
public class HealthCheckManagerImpl extends AbstractManager implements HealthCheckManager {

  private final Logger LOG = LoggerFactory.getLogger("AGENT_LOG");

  private String orgCode;
  private int HEALTH_CHECK_INTERVAL;

  private static class Singleton {
    private static final HealthCheckManager instance = new HealthCheckManagerImpl();
  }

  public static HealthCheckManager getInstance() {
    return Singleton.instance;
  }

  @Override
  public void initialize(InitialEvent event) {
    // TODO Auto-generated method stub

    orgCode = ManagerProvider.property().getProperty(PropertyEnum.ORG_CODE, true);
    HEALTH_CHECK_INTERVAL = Integer.parseInt(ManagerProvider.property().getProperty(PropertyEnum.HEALTH_CHECK_INTERVAL, true));

    this.runHealthCheck();
    setPrepared();

    LOG.info("{} Init Complete.", this.getClass());
  }

  @Override
  public void initializeOnThread(InitialEvent event) {
    // TODO Auto-generated method stub
  }

  private void request() {
    try {
      RequestPacketEntity requestEntity = convertRequestPacketEntity();
      RequestPacket packet = new RequestPacket("", JSON.toJson(requestEntity));
      ResponsePacketEntity responseEntity = ManagerProvider.network().request(packet);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("fail");
    }
  }

  private RequestPacketEntity convertRequestPacketEntity() throws ServiceException {
    // TODO Auto-generated method stub
    RequestPacketEntity requestEntity = new RequestPacketEntity();
    requestEntity.setCmd("HealthCheck");
    RequestHealthCheckArgsEntity argsEntity = new RequestHealthCheckArgsEntity();
    argsEntity.setOrgCode(orgCode);
    requestEntity.setArgs(argsEntity);
    return requestEntity;
  }

  private void runHealthCheck() {
    new Thread(new Runnable() {
      @Override
      public void run() {
        while (true) {
          try {
            request();
            Thread.sleep(HEALTH_CHECK_INTERVAL);
          } catch (ServiceException e) {
            e.printStackTrace();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }).start();
  }
}
