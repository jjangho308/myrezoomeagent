package io.rezoome.manager.pushcommand;

import java.util.Map;

import io.rezoome.core.entity.Action;
import io.rezoome.core.entity.Entity;

/**
 * Interface to Code-Entity-Action mapper. <br />
 * 
 * @author TACKSU
 * @since 180306
 *
 * @param <T> Entity root interface.
 * @param <U> Action.
 */
public interface EntityMapper<T extends Entity, U extends Action<? super T>> {

	<V extends T> U getAction(V entity);
	Class<? extends T> getEntity(String code);
}