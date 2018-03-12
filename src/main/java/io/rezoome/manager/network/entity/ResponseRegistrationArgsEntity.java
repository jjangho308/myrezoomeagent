package io.rezoome.manager.network.entity;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;

public class ResponseRegistrationArgsEntity extends AbstractEntity implements ResponseArgsEntity {

<<<<<<< HEAD
  @SerializedName("code")
  private String code;

  @SerializedName("msg")
  private String msg;

  @SerializedName("queueName")
  private String queueName;
=======
	private String	code;
	private String	msg;
	private String	queueName;
>>>>>>> branch 'development' of https://github.com/Team-REZOOME/agent.git

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
}
