package io.rezoome.manager.network;

import io.rezoome.entity.RzmRsltEntity;
import io.rezoome.manager.Manager;
import io.rezoome.manager.network.entity.RequestPacketEntity;
import io.rezoome.manager.network.entity.ResponsePacketEntity;

/**
 * Network manager. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
public interface NetworkManager extends Manager {

  ResponsePacketEntity request(RequestPacketEntity entity, String protocol, String method, String path);

  ResponsePacketEntity request(RequestPacketEntity entity);

  public RequestPacketEntity convert(RzmRsltEntity entity, String cmd);
}
