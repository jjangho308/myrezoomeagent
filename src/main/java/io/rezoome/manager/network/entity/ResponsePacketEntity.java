package io.rezoome.manager.network.entity;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;

/**
 * Network response entity. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
public class ResponsePacketEntity extends AbstractEntity {

<<<<<<< HEAD
  @SerializedName("mid")
  private String mid;

  @SerializedName("cmd")
  private String cmd;

  @SerializedName("code")
  private String code;

  @SerializedName("result")
  private ResponseArgsEntity result;
=======
	private String				mid;
	private String				cmd;
	private String				code;
	private ResponseArgsEntity	result;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ResponseArgsEntity getResult() {
		return result;
	}

	public void setResult(ResponseArgsEntity result) {
		this.result = result;
	}

}
