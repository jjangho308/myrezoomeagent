package io.rezoome.manager.amq;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;
import io.rezoome.lib.json.JSON;
import io.rezoome.lib.json.util.ConstructorUtils;
import io.rezoome.lib.json.util.ReflectionUtils;
import io.rezoome.manager.provider.ManagerProvider;
import io.rezoome.manager.pushcommand.entity.PushCommandEntity;

/**
 * Push message entity. <br />
 * 
 * @author Saver
 *
 */
public final class AMQMessageEntity extends AbstractEntity {

	static {
		JSON.registerSelfConverter(new Converter());
	}

	private static class Converter implements JsonDeserializer<AMQMessageEntity> {

		@Override
		public AMQMessageEntity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			AMQMessageEntity entity = null;
			try {
				entity = ConstructorUtils.newInstance(AMQMessageEntity.class);
				for (Field field : ReflectionUtils.getAllFields(typeOfT)) {

					String key = ReflectionUtils.getSerializedKey(field);

					if (!Modifier.isStatic(field.getModifiers()) && key != null) {
						if (field.getType().equals(PushCommandEntity.class)) {
							String cmdName = ((JsonObject) json).get("cmd").getAsString();
							if (cmdName == null) {
								throw new JsonParseException("Command key is missing");
							}
							Class<? extends PushCommandEntity> cmdEntityCls = ManagerProvider.pushcommand()
									.getCommandEntity(cmdName);
							PushCommandEntity commandEntity = context.deserialize(((JsonObject) json).get("args"),
									cmdEntityCls);
							ReflectionUtils.setField(entity, field, commandEntity);
							continue;
						}

						ReflectionUtils.setField(entity, field,
								context.deserialize(((JsonObject) json).get(key), field.getType()));
					}
				}
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}

			return entity;
		}
	}

	@SerializedName("mid")
	private final String mid = null;

	@SerializedName("token")
	private final String token = null;

	@SerializedName("cmd")
	private final String cmd = null;

	@SerializedName("args")
	private final PushCommandEntity args = null;

	public AMQMessageEntity() {
		super();
	}

	public PushCommandEntity getCommand() {
		return args;
	}
}
