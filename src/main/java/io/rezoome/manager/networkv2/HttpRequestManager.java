package io.rezoome.manager.networkv2;

import io.rezoome.core.annotation.ManagerType;
import io.rezoome.manager.Manager;
import io.rezoome.manager.network.entity.RequestPacketEntity;
import io.rezoome.manager.network.entity.ResponsePacketEntity;

/**
 * Send/Receive HTTP Transfering. <br />
 * 
 * @since 180309
 * @author TACKSU
 *
 */
@ManagerType(value = "HttpRequest")
public interface HttpRequestManager extends Manager {
	RequestPacketEntity obtainRequestPacket();

	ResponsePacketEntity sendRequest(RequestPacketEntity packet);
}
