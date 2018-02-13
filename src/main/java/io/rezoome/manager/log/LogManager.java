package io.rezoome.manager.log;

import io.rezoome.manager.Manager;

/**
 * Interface of LogManager. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
public interface LogManager extends Manager {
	void sendLogToServer(Runnable callback);
}
