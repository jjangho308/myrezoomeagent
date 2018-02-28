package io.rezoome.lib.json.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Clone {
	public static <T> T clone(T src) {
		if(src == null)
			throw new NullPointerException("Source object is null.");
		
		Class<?> cls = src.getClass();
		if(cls.isPrimitive())
			return src;
		
		
		T result = null;
		try {
			result = (T) src.getClass().getConstructor().newInstance();
			Field[] fields = src.getClass().getDeclaredFields();

			for (Field field : fields) {
				field.setAccessible(true);
				Object copiedValue = null;
				Object origValue = field.get(src);
				copiedValue = clone(origValue);
				
				field.set(result, copiedValue);
			}
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}
