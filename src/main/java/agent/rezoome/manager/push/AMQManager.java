package agent.rezoome.manager.push;

import agent.rezoome.manager.Manager;

/**
 * Push message manager. <br />
 * @since 1.0.0
 * @author TACKSU
 *
 */
public interface AMQManager extends Manager {
	void registerPush(AMQConfigEntity config);
	void unregisterPush();
	void registerPushHandler(AMQMessageHandler handler);
}