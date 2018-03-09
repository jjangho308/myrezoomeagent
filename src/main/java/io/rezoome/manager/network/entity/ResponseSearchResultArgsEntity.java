package io.rezoome.manager.network.entity;

import io.rezoome.core.entity.AbstractEntity;

public class ResponseSearchResultArgsEntity extends AbstractEntity implements ResponseArgsEntity {

	private String	code;
	private String	msg;

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
}
