package io.rezoome.manager.push;

import io.rezoome.manager.Manager;

/**
 * Push message manager. <br />
 * @since 1.0.0
 * @author TACKSU
 *
 */
public interface PushManager extends Manager {
	void registerPush(PushConfigEntity config) throws Exception;
	void unregisterPush(PushConfigEntity config) throws Exception;
	void registerPushHandler(PushMessageHandler handler);
}