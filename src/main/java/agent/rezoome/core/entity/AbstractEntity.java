package agent.rezoome.core.entity;

import agent.rezoome.core.entity.annotation.EntityType;
import io.rezoome.lib.json.JSON;

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
	
	/**
	 * Hide default constructor. <br />
	 */
	protected AbstractEntity(){
	}

	@Override
	public EntityType getAnnotation() {
		return ant;
	}

	@Override
	public String toString() {
		return JSON.toJson(this);
	}
}
