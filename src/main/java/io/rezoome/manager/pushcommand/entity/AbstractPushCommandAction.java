package io.rezoome.manager.pushcommand.entity;

import io.rezoome.core.entity.AbstractAction;

/**
 * Abstract class of {@link PushCommandAction}. <br />
 * @since 1.0.0
 * @author TACKSU
 *
 * @param <T>
 */
public abstract class AbstractPushCommandAction<T extends PushCommandEntity> extends AbstractAction<T>
		implements PushCommandAction<T> {
  //  YSY - 18.02.19
  protected abstract void invokePushCommandAction(T pushCommandEntity);
  

}