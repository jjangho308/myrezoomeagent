package io.rezoome.lib.json.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.Entity;

/**
 * Utility class for reflection function. <br />
 * 
 * @sin
 * @author Saver
 *
 */
public class ReflectionUtils {
	public static Field[] getAllFields(Class<?> cls) {
		List<Field> fields = new ArrayList<>();

		while (cls != null) {
			Collections.<Field>addAll(fields, cls.getDeclaredFields());
			cls = cls.getSuperclass();
		}

		Field[] result = new Field[fields.size()];
		fields.toArray(result);
		return result;
	}

	public static <T> Field[] getAllFields(T obj) {
		if (obj instanceof Class) {
			return getAllFields((Class<?>) obj);
		}
		return getAllFields(obj.getClass());
	}

	public static String getSerializedKey(Field field) {
		SerializedName sName = field.getAnnotation(SerializedName.class);
		return sName != null ? sName.value() : null;
	}

	public static void setField(Entity entity, Field field, Object value) throws IllegalArgumentException, IllegalAccessException {
		field.setAccessible(true);
		field.set(entity, value);
	}
}
