package io.rezoome.manager.networkv2;

import java.net.URL;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.manager.AbstractManager;
import io.rezoome.manager.network.entity.RequestPacketEntity;
import io.rezoome.manager.network.entity.ResponsePacketEntity;

/**
 * Implementation of {@link HttpRequestManager}. <br />
 * 
 * @since 180309
 * @author TACKSU
 *
 */
public class HttpRequestManagerImpl extends AbstractManager implements HttpRequestManager {

	private URL agentChannenUrl;

	@Override
	public void initialize(InitialEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initializeOnThread(InitialEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public RequestPacketEntity obtainRequestPacket() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponsePacketEntity sendRequest(RequestPacketEntity packet) {
		// TODO Auto-generated method stub
		return null;
	}

}
