package io.rezoome.lib.json.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import io.rezoome.lib.json.Jsonable;

/**
 * Bulk utility class for Constructor. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
public class ConstructorUtils {

	/**
	 * Invoke new instacne of inhertiance class of Entity. <br />
	 * 
	 * @since 1.0.0
	 * @author TACKSU
	 * 
	 * @param cls
	 * 
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static <T extends Jsonable> T newInstance(Class<T> cls) throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		T entity = null;
		Constructor<T> defaultConstructor = cls.getConstructor();
		entity = defaultConstructor.newInstance();

		return entity;
	}

}