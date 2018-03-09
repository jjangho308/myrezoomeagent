package io.rezoome.manager.pushcommand.entity;

import io.rezoome.core.entity.Action;

/**
 * Root interface of PushCommandAction invoker of {@link PushCommandEntity}.
 * <br />
 * 
 * @since 1.0.0
 *
 * @param <T>
 *            Entity class that charge of it. <br />
 */
public interface PushCommandAction<T extends PushCommandEntity>
		extends Action<T> {

}