package io.rezoome.manager.network;

import io.rezoome.entity.RzmRsltEntity;
import io.rezoome.manager.Manager;
import io.rezoome.manager.network.entity.RequestPacketEntity;
import io.rezoome.manager.network.entity.ResponsePacketEntity;
import io.rezoome.manager.network.http.HttpConnector;
import io.rezoome.manager.network.http.HttpsConnector;

/**
 * Network manager. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
public interface NetworkManager extends Manager {

  ResponsePacketEntity request(RequestPacketEntity entity, String protocol, String method);

  public RequestPacketEntity convert(RzmRsltEntity entity, String cmd);

  public HttpConnector getHttpConnecter();

  public HttpsConnector getHttpsConnecter();
}
