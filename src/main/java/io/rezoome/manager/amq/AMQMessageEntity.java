package io.rezoome.manager.amq;


import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;
import io.rezoome.lib.json.JSON;
import io.rezoome.manager.provider.ManagerProvider;
import io.rezoome.manager.pushcommand.entity.PushCommandEntity;

/**
 * Push message entity. <br />
 * 
 * @author Saver
 *
 */
public final class AMQMessageEntity extends AbstractEntity {

	public String getMid() {
    return mid;
  }

  public String getSid() {
    return sid;
  }

  public String getToken() {
    return token;
  }

  public String getCmd() {
    return cmd;
  }

  static {
		// JSON.registerSelfConverter(new Converter());
		JSON.registerDeserializer("cmd", "args", ManagerProvider.clsarrange().getEntityCodeMap(PushCommandEntity.class));
	}

	@SerializedName("mid")
	private final String			mid		= null;

	@SerializedName("sid")
  private final String      sid   = null;
	
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

  @Override
  public String toString() {
    return "AMQMessageEntity [mid=" + mid + ", sid=" + sid + ", token=" + token + ", cmd=" + cmd + ", args=" + args + "]";
  }
	
	
}
