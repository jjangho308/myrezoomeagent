package io.rezoome.manager.push;

import io.rezoome.manager.Manager;

/**
 * Push message manager. <br />
 * @since 1.0.0
 * @author TACKSU
 *
 */
public interface AMQManager extends Manager {
	void registerPush(AMQConfigEntity config);
<<<<<<< HEAD
	void unregisterPush();
=======
	void unregisterPush(AMQConfigEntity config);
>>>>>>> branch 'development' of https://github.com/Team-REZOOME/agent.git
	void registerPushHandler(AMQMessageHandler handler);
}