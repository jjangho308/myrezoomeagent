package io.rezoome.manager.event;

import io.rezoome.manager.Manager;
import io.rezoome.manager.event.listener.EventListener;

/**
 * Interface of EventRegisterManager. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
public interface EventRegisterManager extends Manager {

	public void addEventListener(EventListener listener);

	public void removeEventListener(EventListener eventListener);
}