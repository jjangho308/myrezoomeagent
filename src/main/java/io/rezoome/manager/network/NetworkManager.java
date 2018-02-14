package io.rezoome.manager.network;

import io.rezoome.manager.Manager;

/**
 * Network manager. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
public interface NetworkManager extends Manager {
	
	void request(RequestEntity entity);
}
