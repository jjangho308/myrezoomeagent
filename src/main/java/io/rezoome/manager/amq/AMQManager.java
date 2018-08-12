package io.rezoome.manager.amq;

import io.rezoome.manager.Manager;

/**
 * Push message manager. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
public interface AMQManager extends Manager {
	void registerPush(AMQConfigEntity config);

	void unregisterPush();

	void registerPushHandler();
}