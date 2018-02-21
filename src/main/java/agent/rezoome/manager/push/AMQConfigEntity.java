package agent.rezoome.manager.push;

import java.net.URL;

import agent.rezoome.core.entity.AbstractEntity;

/**
 * Meta information to register Push service. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
public final class AMQConfigEntity extends AbstractEntity {
	
	private final String queueMeta;
	
	private final String queueName;

	private final String sererProtocol;

	private final String serverHost;

	private final int serverPort;

	private final String userName;

	private final String userPassword;
	
	

	public AMQConfigEntity(String queueMeta,
							String queueName,
							String sererProtocol,
							String serverHost,
							int serverPort,
							String userName,
							String userPassword) {
		super();
		this.queueMeta = queueMeta;
		this.queueName = queueName;
		this.sererProtocol = sererProtocol;
		this.serverHost = serverHost;
		this.serverPort = serverPort;
		this.userName = userName;
		this.userPassword = userPassword;
	}

	public String getQueueMeta() {
		return queueMeta;
	}

	public String getQueueName() {
		return queueName;
	}

	public String getSererProtocol() {
		return sererProtocol;
	}
	
	public String getServerHost() {
		return serverHost;
	}

	public int getServerPort() {
		return serverPort;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserPassword() {
		return userPassword;
	}
	
	public URL toURL(){
		// TODO Assemble parameters to push server url.
		return null;
	}
}
