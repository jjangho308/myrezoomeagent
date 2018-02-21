package io.rezoome.manager.push;

import io.rezoome.manager.Manager;

/**
 * Push message manager. <br />
 * @since 1.0.0
 * @author TACKSU
 *
 */
public interface AMQManager extends Manager {
	void registerPush(PushConfigEntity config);
	void unregisterPush(PushConfigEntity config);
	void registerPushHandler(PushMessageHandler handler);
}