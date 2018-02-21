package agent.rezoome.manager.pushcommand.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for {@link PushCommandEntity} <br />
 * @since 1.0.0
 * @author Saver
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PushCommandEntity {
	String value();
	int priority() default -1;
}