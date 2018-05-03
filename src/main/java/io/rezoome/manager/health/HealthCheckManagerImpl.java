package io.rezoome.manager.health;

import java.net.HttpURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.rezoome.constants.Constants;
import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.core.annotation.ManagerType;
import io.rezoome.exception.ServiceException;
import io.rezoome.lib.json.JSON;
import io.rezoome.manager.AbstractManager;
import io.rezoome.manager.health.entity.RequestHealthCheckArgsEntity;
import io.rezoome.manager.network.entity.request.RequestPacket;
import io.rezoome.manager.network.entity.request.RequestPacketEntity;
import io.rezoome.manager.network.entity.response.ResponsePacketEntity;
import io.rezoome.manager.property.PropertyEnum;
import io.rezoome.manager.provider.ManagerProvider;

@ManagerType(Constants.MANAGER_TYPE_HEALTH)
public class HealthCheckManagerImpl extends AbstractManager implements HealthCheckManager {

  private final Logger LOG = LoggerFactory.getLogger(Constants.AGENT_LOG);

  private int FAIL_COUNT;
  private int HEALTH_CHECK_INTERVAL;
  private String orgCode;

  private static class Singleton {
    private static final HealthCheckManager instance = new HealthCheckManagerImpl();
  }

  public static HealthCheckManager getInstance() {
    return Singleton.instance;
  }

  @Override
  public void initialize(InitialEvent event) {
    // TODO Auto-generated method stub
    FAIL_COUNT = 0;
    orgCode = ManagerProvider.property().getProperty(PropertyEnum.ORG_CODE, true);
    HEALTH_CHECK_INTERVAL = Integer.parseInt(ManagerProvider.property().getProperty(PropertyEnum.HEALTH_CHECK_INTERVAL, true));
    setPrepared();
    healthCheck();
    LOG.info("{} Init Complete.", this.getClass());
  }

  @Override
  public void initializeOnThread(InitialEvent event) {
    // TODO Auto-generated method stub
  }

  private void healthCheck() {
    new Thread(new Runnable() {
      @Override
      public void run() {
        RequestPacket packet = new RequestPacket("", JSON.toJson(convertRequestPacketEntity()));

        while (true) {
          try {
            ResponsePacketEntity responseEntity = ManagerProvider.network().request(packet);
            // TODO 헬스체크 결과에 따른 처리
            if (responseEntity != null &&
                String.valueOf(HttpURLConnection.HTTP_OK).equals(responseEntity.getCode())) {
              FAIL_COUNT = 0;
            } else {
              FAIL_COUNT++;
              LOG.error("Fail to health check {} count.", FAIL_COUNT);
            }
            // Thread.sleep(HEALTH_CHECK_INTERVAL);
            Thread.sleep(2400000);
          } catch (Exception e) {
            // FAIL_COUNT++;
            // LOG.error("Fail to health check {} count.", FAIL_COUNT);
            LOG.error("exception");
            break;
          }
        }
      }
    }).start();
  }

  private RequestPacketEntity convertRequestPacketEntity() throws ServiceException {
    // TODO Auto-generated method stub
    RequestPacketEntity requestEntity = new RequestPacketEntity();
    requestEntity.setCmd(Constants.COMMAND_HEALTH_CHECK);
    RequestHealthCheckArgsEntity argsEntity = new RequestHealthCheckArgsEntity();
    argsEntity.setOrgCode(orgCode);
    requestEntity.setArgs(argsEntity);
    return requestEntity;
  }

}
