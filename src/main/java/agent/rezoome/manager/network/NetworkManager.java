package agent.rezoome.manager.network;

import agent.rezoome.manager.Manager;
import agent.rezoome.manager.network.entity.RequestPacketEntity;
import agent.rezoome.manager.network.entity.ResponsePacketEntity;

/**
 * Network manager. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
public interface NetworkManager extends Manager {
	
	ResponsePacketEntity request(RequestPacketEntity entity);
}
