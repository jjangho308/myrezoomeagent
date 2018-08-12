package io.rezoome.manager.network.entity;

import java.net.URL;

import javax.net.ssl.SSLContext;

import io.rezoome.core.entity.AbstractEntity;

/**
 * Information to configure network property. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
public final class NetworkConfig extends AbstractEntity {
	private final URL			url;
	private final SSLContext	sslContext;

	public NetworkConfig(URL url, SSLContext sslContext) {
		super();
		this.url = url;
		this.sslContext = sslContext;
	}
}