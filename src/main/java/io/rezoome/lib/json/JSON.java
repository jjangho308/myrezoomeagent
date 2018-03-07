package io.rezoome.lib.json;

import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import com.google.gson.stream.JsonReader;

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
	public static <T extends Jsonable, U extends JsonSerializer<T> & JsonDeserializer<T>>
		void registerSelfConverter(U converter) {
		
		try {
			builder.registerTypeHierarchyAdapter(ClassLoader.getSystemClassLoader().loadClass(Thread.currentThread().getStackTrace()[2].getClassName()), converter);
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

	public static <T extends Jsonable> T fromJson(InputStream is, Class<T> cls) {
		return builder.create().fromJson(new JsonReader(new InputStreamReader(is)), cls);
	}

	public static <T extends Jsonable> void fromJson(InputStream is, T entity) {
		entity = builder.create().fromJson(new JsonReader(new InputStreamReader(is)), entity.getClass());
	}

	public static <T extends Jsonable> T fromJson(String jsonString, Class<T> cls) {
		T entity = builder.create().fromJson(jsonString, cls);
		return entity;
	}
	
	/**
	 * Hide constructor. <br />
	 */
	private JSON(){
		
	}
	
	@Deprecated
	public static JSON pretty(){
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
	public static String toJson(Jsonable jsonable){
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
//		return builder.create().toJson(jsonable);
		return null;
	}
}