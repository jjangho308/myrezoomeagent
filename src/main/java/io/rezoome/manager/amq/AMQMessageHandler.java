package io.rezoome.manager.amq;

/**
 * Push message handler interface. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 */
public interface AMQMessageHandler {

	boolean handleMessage(AMQMessageEntity msg);
}
