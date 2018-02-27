package io.rezoome.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for manager class. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ManagerType {
	
	/**
	 * Code name of specific manager. <br />
	 * 
	 * @since 1.0.0
	 * @return
	 */
	String value();
	
	/**
	 * Priority to be initialized. <br />
	 * -1	: Do not care. (Initialized randomly)
	 * 0	: N/A <br />
	 * 1	: First priority <br />
	 * N	: Smaller is more prior. <br /> 
	 * 
	 * @since 1.0.0
	 * 
	 * @return
	 */
	int initPriority() default -1;
}