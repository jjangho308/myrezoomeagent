package io.rezoome.manager.amq;

import java.net.URL;

import io.rezoome.core.entity.AbstractEntity;

/**
 * Meta information to register Push service. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
public final class AMQConfigEntity extends AbstractEntity {
	private final String	queueName;
	private final String	serverHost;
	private final String	userName;
	private final String	userPassword;

	public AMQConfigEntity(String queueName, String serverHost, String userName, String userPassword) {
		super();
		this.queueName = queueName;
		this.serverHost = serverHost;
		this.userName = userName;
		this.userPassword = userPassword;
	}

	public String getQueueName() {
		return queueName;
	}

	public String getServerHost() {
		return serverHost;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public URL toURL() {
		// TODO Assemble parameters to push server url.
		return null;
	}
}
