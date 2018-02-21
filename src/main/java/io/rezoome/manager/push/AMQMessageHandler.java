package io.rezoome.manager.push;

/**
 * Push message handler interface. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 */
public interface PushMessageHandler {
	boolean handleMessage(PushMessageEntity msg);
}
