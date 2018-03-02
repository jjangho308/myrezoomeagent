package io.rezoome.lib.json;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;

import io.rezoome.lib.json.annotations.JSON;
import io.rezoome.lib.json.util.ReflectionUtils;

/**
 * Interface for entity that convertible from/to JSON presentation. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
public interface Jsonable {

	/**
	 * Basis JSON de/serialzer for Rezoome module of JAVA platform. <br />
	 * 
	 * @since 1.0.0
	 * @author Saver
	 */
	class Converter implements JsonSerializer<Jsonable>, JsonDeserializer<Jsonable> {

		/**
		 * Singleton Helper. <br />
		 * 
		 * @since 1.0.0
		 * @author Saver
		 *
		 */
		private static class Singleton {
			private static final Converter instance;
			static {
				instance = new Converter();
			}
		}

		private static final String TYPE = "$type";

		private static final String VALUE = "$value";

		public static Converter getInstance() {
			return Singleton.instance;
		}

		/*
		 * Hide constructor. <br />
		 * 
		 */
		private Converter() {
		}

		@Override
		public Jsonable deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			Jsonable resultEntity = null;
			try {
				resultEntity = (Jsonable) ((Class<?>) typeOfT).newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				throw new JsonParseException("Cannot institate " + typeOfT.getTypeName(), e);
			}
			List<Field> fields = Arrays.asList(typeOfT.getClass().getDeclaredFields());

			//
			Map<String, Field> keyMap = new HashMap<String, Field>();
			for (Field field : fields) {
				SerializedName serializedName = field.getDeclaredAnnotation(SerializedName.class);
				if (serializedName != null) {
					String keyName = serializedName.value();
					if (keyMap.containsKey(keyName)) {
						throw new JsonParseException("Serialized name of " + field.getName() + " field is duplicated.");
					}
					keyMap.put(keyName, field);
					// FIXME 여기 고쳐야됨
//					for (String altKey : serializedName.alternate()) {
//						if (keyMap.containsKey(altKey)) {
//							continue;
//						}
//						keyMap.put(keyName, field);
//					}
				}
			}

			Class<?> cls = (Class<?>) typeOfT;

			Jsonable result = null;
			try {
				result = (Jsonable) cls.getConstructor().newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				throw new JsonParseException("Cannot institate " + typeOfT.getTypeName(), e);
			}

			JsonObject object = (JsonObject) json;
			for (Entry<String, JsonElement> entry : object.entrySet()) {
				Field target = keyMap.get(entry.getKey());
				target.setAccessible(true);
				try {
					target.set(object, context.deserialize(entry.getValue(), target.getClass()));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new JsonParseException("Field error", e);
				}
			}

			resultEntity = context.deserialize(json, typeOfT);

			return resultEntity;
		}

		@Override
		public JsonElement serialize(Jsonable src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject element = new JsonObject();

			try {
				// Type distinguisher to JSON Object.
				String jsonType = null;
				Field[] fields = ReflectionUtils.getAllFields((Class<?>)typeOfSrc);
				for (Field field : fields) {

					// JSON field tag.
					String fieldJsonKey = field.isAnnotationPresent(SerializedName.class)
							? field.getDeclaredAnnotation(SerializedName.class).value() : field.getName();

					// Field actual value.
					field.setAccessible(true);
					Object fieldValue = field.get(src);

					//
					if (field.getType().isInterface() && field.getType().asSubclass(Jsonable.class) != null) {
						JsonObject interfaceObject = (JsonObject) context.serialize(field.get(src));
						JSON annot = fieldValue.getClass().getDeclaredAnnotation(JSON.class);
						interfaceObject.addProperty("$type", annot.value());
						element.add(field.getName(), interfaceObject);
					}

					//
					else if (field.getType().isArray()) {
						Object[] array = (Object[]) fieldValue;
						Class<?> firstCls = array[0].getClass();
						boolean singleType = true;
						for (Object item : array) {
							singleType &= firstCls.equals(item.getClass());
							if (!singleType) {
								break;
							}
						}

						if (singleType) {
							JsonObject jsonObject = new JsonObject();
							jsonObject.addProperty(TYPE, firstCls.getAnnotation(JSON.class).value());
							jsonObject.add(VALUE, context.serialize(array));
						} else {

						}
					}

					//
					else if (field.getType().asSubclass(Collection.class) != null) {
						Type valueType = ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
						if (valueType.getClass().isInterface() && valueType instanceof Jsonable) {
							JsonObject listObject = new JsonObject();
							JSON annot = src.getClass().getAnnotation(JSON.class);
							if (annot == null) {
								throw new JsonSyntaxException("No JSON annotation declared.");
							}
							listObject.addProperty(TYPE, annot.value());
							listObject.add(VALUE, context.serialize(field.get(src)));
						} else {
							element.add(fieldJsonKey, context.serialize(field.get(src)));
						}
					}

					//
					else if (field.getType().asSubclass(Map.class) != null) {
						Type valueType = ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[1];
						if (valueType.getClass().isInterface() && valueType instanceof Jsonable) {
							JsonObject mapObject = new JsonObject();
							Map<?, ?> mapValue = ((Map<?, ?>) fieldValue);
							for (Object key : mapValue.keySet()) {
								JsonObject elementJsonObject = (JsonObject) context.serialize(mapValue.get(key));
								elementJsonObject.addProperty(TYPE, jsonType);
								mapObject.add(key.toString(), elementJsonObject);
							}
						}
					}

					else {
						element.add(fieldJsonKey, context.serialize(fieldValue, field.getType()));
					}
				}
			} catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
				throw new JsonSyntaxException(e);
			}

			return element;
		}

	}
}
