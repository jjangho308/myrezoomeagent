package io.rezoome.manager.pushcommand.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.rezoome.manager.pushcommand.entity.PushCommandAction;
import io.rezoome.manager.pushcommand.entity.PushCommandEntity;

/**
 * Annotation for {@link PushCommandAction}. <br />
 * 
 * @author Saver
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PushCommand {
	Class<? extends PushCommandEntity> value() default PushCommandEntity.class;
}