package io.rezoome.manager.network;

import io.rezoome.entity.RzmRsltEntity;
import io.rezoome.manager.Manager;
import io.rezoome.manager.network.entity.request.RequestPacket;
import io.rezoome.manager.network.entity.request.RequestPacketEntity;
import io.rezoome.manager.network.entity.response.ResponsePacketEntity;

/**
 * Network manager. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
public interface NetworkManager extends Manager {

  public ResponsePacketEntity request(RequestPacket obj);
  public RequestPacketEntity convert(RzmRsltEntity entity, String cmd);
  public String createPortalUrl(String sid);
  
}
