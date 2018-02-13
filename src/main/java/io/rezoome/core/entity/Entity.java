package io.rezoome.core.entity;

import io.rezoome.core.entity.annotation.EntityType;
import io.rezoome.lib.json.Jsonable;

/**
 * Root interface of all entity class. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
public interface Entity extends Jsonable{
	EntityType getAnnotation();
}
