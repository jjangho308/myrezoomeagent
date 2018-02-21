package io.rezoome.manager.push;

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
}