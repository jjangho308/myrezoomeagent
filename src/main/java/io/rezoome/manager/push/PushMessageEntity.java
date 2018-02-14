package io.rezoome.manager.push;

import io.rezoome.core.entity.AbstractEntity;

/**
 * Push message entity. <br />
 * 
 * @author Saver
 *
 */
public final class PushMessageEntity extends AbstractEntity {
	private final String message;

	public PushMessageEntity(String message) {
		super();
		this.message = message;
	}
}
