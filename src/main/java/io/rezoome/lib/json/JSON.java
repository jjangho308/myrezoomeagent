package io.rezoome.lib.json;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;

import io.rezoome.core.entity.Entity;
import io.rezoome.lib.json.util.ConstructorUtils;
import io.rezoome.lib.json.util.ReflectionUtils;

/**
 * Root utility class for JSON De/Serialization. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 */
public final class JSON {

	/**
	 * Register custom converter for caller class. <br />
	 * Automatically specify caller class. <br />
	 * 
	 * @since 1.0.0
	 * @author TACKSU
	 * @param converter
	 */
	public static <T extends Jsonable> void registerSelfConverter(
			Object converter) {
		try {
			builder.registerTypeHierarchyAdapter(
					ClassLoader.getSystemClassLoader()
							.loadClass(Thread.currentThread().getStackTrace()[2]
									.getClassName()),
					converter);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static <T extends Entity> void registerDeserializer(
			final String codeKey, final String instanceKey,
			final Map<String, Class<? extends T>> mapper) {
		try {
			builder.registerTypeAdapter(
					ClassLoader.getSystemClassLoader()
							.loadClass(Thread.currentThread().getStackTrace()[2]
									.getClassName()),
					new JsonDeserializer<Entity>() {

						@SuppressWarnings("unchecked")
						@Override
						public Entity deserialize(JsonElement json,
								Type typeOfT,
								JsonDeserializationContext context)
								throws JsonParseException {
							Entity rootEntity = null;
							try {
								rootEntity = (Entity) ConstructorUtils
										.newInstance((Class<Object>) typeOfT);

								for (Field field : ReflectionUtils
										.getAllFields(typeOfT)) {

									String key = ReflectionUtils
											.getSerializedKey(field);

									if (!Modifier.isStatic(field.getModifiers())
											&& key != null) {
										if (key.equals(instanceKey)) {
											String codeName = ((JsonObject) json)
													.get(codeKey).getAsString();
											if (codeName == null) {
												throw new JsonParseException(
														"Command key is missing");
											}
											Class<? extends T> entityCls = mapper
													.get(codeName);

											T memberEntity = context
													.deserialize(
															((JsonObject) json)
																	.get(instanceKey),
															entityCls);

											ReflectionUtils.setField(rootEntity,
													field, memberEntity);
											continue;
										}

										ReflectionUtils.setField(rootEntity,
												field,
												context.deserialize(
														((JsonObject) json)
																.get(key),
														field.getType()));
									}
								}
							} catch (NoSuchMethodException | SecurityException
									| InstantiationException
									| IllegalAccessException
									| IllegalArgumentException
									| InvocationTargetException e) {
								e.printStackTrace();
							}
							return rootEntity;
						}
					});
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Static gson instance. <br />
	 * 
	 * @since 1.0.0
	 * @author TACKSU
	 */
	private static final GsonBuilder builder;

	/**
	 * Static initialier. <br />
	 * Prepare gsonBuilder. <br />
	 * 
	 * @since 1.0.0
	 * @author TACKSU
	 */
	static {
		builder = new GsonBuilder();
	}

	public static <T extends Jsonable> T fromJson(InputStream is,
			Class<T> cls) {
		return builder.create()
				.fromJson(new JsonReader(new InputStreamReader(is)), cls);
	}

	public static <T extends Jsonable> void fromJson(InputStream is, T entity) {
		entity = builder.create().fromJson(
				new JsonReader(new InputStreamReader(is)), entity.getClass());
	}

	public static <T extends Jsonable> T fromJson(String jsonString,
			Class<T> cls) {
		T entity = builder.create().fromJson(jsonString, cls);
		return entity;
	}

	/**
	 * Hide constructor. <br />
	 */
	private JSON() {

	}

	@Deprecated
	public static JSON pretty() {
		return null;
	}

	public static <T extends Jsonable> T fromJson(String jsonString, T entity) {
		entity = (T) fromJson(jsonString, entity.getClass());
		return entity;
	}

	/**
	 * Jsonable to JSON string presentation. <br />
	 * 
	 * @since 1.0.0
	 * @author TACKSU
	 * 
	 * @param jsonable
	 * @return
	 */
	public static String toJson(Jsonable jsonable) {
		return builder.create().toJson(jsonable);
	}

	/**
	 * Not implemented yet. <br />
	 * 
	 * @param jsonable
	 * @param mask
	 * @return
	 */
	@Deprecated
	public static String toJson(Jsonable jsonable, boolean mask) {
		// return builder.create().toJson(jsonable);
		return null;
	}
}