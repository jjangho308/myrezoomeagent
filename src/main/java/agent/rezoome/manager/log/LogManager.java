package agent.rezoome.manager.log;

import agent.rezoome.manager.Manager;

/**
 * Interface of LogManager. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
public interface LogManager extends Manager {
	void sendLogToServer(Runnable callback);

	Logger createLogger(Class<?> object);
}