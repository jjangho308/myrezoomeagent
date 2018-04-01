package io.rezoome.manager.health;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.rezoome.constants.Constants;
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
import io.rezoome.thread.AsyncService;

@ManagerType(Constants.MANAGER_TYPE_HEALTH)
public class HealthCheckManagerImpl extends AbstractManager implements HealthCheckManager {

  private final Logger LOG = LoggerFactory.getLogger("AGENT_LOG");

  private int HEALTH_CHECK_INTERVAL;
  private String orgCode;
  private ExecutorService executor;
  private RequestPacket packet;

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
    packet = new RequestPacket("", JSON.toJson(convertRequestPacketEntity()));
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
        executor = Executors.newFixedThreadPool(1);
        while (true) {
          try {
            Callable<ResponsePacketEntity> callable = new AsyncService(packet);
            Future<ResponsePacketEntity> future = executor.submit(callable);
            ResponsePacketEntity responseEntity = future.get(10, TimeUnit.SECONDS);
            // TODO 헬스체크 결과에 따른 처리

            Thread.sleep(HEALTH_CHECK_INTERVAL);
          } catch (Exception e) {
            e.printStackTrace();
            break;
          }
        }
        executor.shutdown();
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
