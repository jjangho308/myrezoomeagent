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
import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;
import io.rezoome.lib.json.JSON;
import io.rezoome.lib.json.util.ConstructorUtils;
import io.rezoome.lib.json.util.ReflectionUtils;
import io.rezoome.manager.job.entity.JobEntity;
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
		// JSON.registerSelfConverter(new Converter());
		JSON.registerDeserializer("cmd", "args", ManagerProvider.clsarrange().getEntityCodeMap(PushCommandEntity.class));
	}

	@SerializedName("mid")
	private final String			mid		= null;

	@SerializedName("token")
	private final String			token	= null;

	@SerializedName("cmd")
	private final String			cmd		= null;

	@SerializedName("args")
	private final PushCommandEntity	args	= null;

	public AMQMessageEntity() {
		super();
	}

	public PushCommandEntity getCommand() {
		return args;
	}
}
