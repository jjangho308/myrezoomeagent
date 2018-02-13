package io.rezoome.core.entity;

import io.rezoome.core.entity.annotation.EntityType;

/**
 * Root abstract class of entity. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
public abstract class AbstractEntity implements Entity {
	private final EntityType ant;
	
	{
		ant = this.getClass().getDeclaredAnnotation(EntityType.class);
	}

	@Override
	public EntityType getAnnotation() {
		return ant;
	}
}
