package io.rezoome.manager.database.entity.agency;

import io.rezoome.manager.database.entity.DBRsltEntity;

public class InhaUnivEntity implements DBRsltEntity {
	private String	user;
	private String	host;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public String toString() {
		return "InhaUnivEntity [user=" + user + ", host=" + host + "]";
	}

}
