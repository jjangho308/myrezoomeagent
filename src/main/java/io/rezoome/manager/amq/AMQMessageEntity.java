package io.rezoome.manager.amq;

import io.rezoome.core.entity.AbstractEntity;

/**
 * Push message entity. <br />
 * 
 * @author Saver
 *
 */
public final class AMQMessageEntity extends AbstractEntity {
	private final String message;

	public AMQMessageEntity(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
