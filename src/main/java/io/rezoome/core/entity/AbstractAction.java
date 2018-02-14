package io.rezoome.core.entity;

/**
 * Root abstraction class of {@link Action}. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 * @param <T>
 */
public abstract class AbstractAction<T extends Entity> implements Action<T> {

	protected abstract ActionResult process(T entity);
}