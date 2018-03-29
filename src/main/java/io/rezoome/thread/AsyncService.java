package io.rezoome.thread;

import java.util.concurrent.Callable;

import io.rezoome.manager.network.entity.RequestPacket;
import io.rezoome.manager.network.entity.response.ResponsePacketEntity;
import io.rezoome.manager.provider.ManagerProvider;

public class AsyncService implements Callable<ResponsePacketEntity> {
  private RequestPacket packet;

  public AsyncService(RequestPacket packet) {
    this.packet = packet;
  }

  @Override
  public ResponsePacketEntity call() throws Exception {
    // TODO Auto-generated method stub
    ResponsePacketEntity responseEntity = null;
    try {
      responseEntity = ManagerProvider.network().request(packet);
    } catch (Exception e) {
      return null;
    }
    return responseEntity;
  }
}
