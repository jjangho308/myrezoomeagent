package io.rezoome.manager.network.entity;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;

/**
 * Network request packet entity. <br />
 * 
 * @since 1.0.0
 *
 */
public class RequestPacketEntity extends AbstractEntity {

<<<<<<< HEAD
  @SerializedName("mid")
  private String mid;

  @SerializedName("cmd")
  private String cmd;

  @SerializedName("args")
  private RequestArgsEntity args;
=======
	private String				mid;
	private String				cmd;
	private RequestArgsEntity	args;
>>>>>>> branch 'development' of https://github.com/Team-REZOOME/agent.git

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public RequestArgsEntity getArgs() {
		return args;
	}

	public void setArgs(RequestArgsEntity args) {
		this.args = args;
	}

}
