package io.rezoome.manager.keyprovision.entity;

import io.rezoome.core.entity.AbstractEntity;
import io.rezoome.manager.network.entity.response.ResponseArgsEntity;

public class ResponseKeyProvisiionArgsEntity extends AbstractEntity implements ResponseArgsEntity {

  private String code;
  private String msg;

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
