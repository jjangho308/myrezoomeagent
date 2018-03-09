package io.rezoome.manager.network.entity;

import io.rezoome.core.entity.AbstractEntity;

public class ResponseRegistrationArgsEntity extends AbstractEntity implements ResponseArgsEntity {

	private String	code;
	private String	msg;
	private String	queueName;

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
