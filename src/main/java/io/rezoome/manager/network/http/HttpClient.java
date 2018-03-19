package io.rezoome.manager.network.http;

import io.rezoome.manager.network.entity.RequestObject;
import io.rezoome.manager.network.entity.ResponsePacketEntity;

public interface HttpClient {
  public ResponsePacketEntity request(RequestObject obj);
}
