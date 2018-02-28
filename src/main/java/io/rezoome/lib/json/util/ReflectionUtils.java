package io.rezoome.lib.json.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utility class for reflection function. <br />
 * 
 * @sin
 * @author Saver
 *
 */
public class ReflectionUtils {
	public static Field[] getAllFields(Class<?> cls){
		List<Field> fields = new ArrayList<>();
		
		while(cls != null){
			Collections.<Field>addAll(fields, cls.getDeclaredFields());
			cls = cls.getSuperclass();
		}
		
		Field[] result = new Field[fields.size()];
		fields.toArray(result);
		return result;
	}
	
	public static <T> Field[] getAllFields(T obj){
		return getAllFields(obj.getClass());
	}
}
