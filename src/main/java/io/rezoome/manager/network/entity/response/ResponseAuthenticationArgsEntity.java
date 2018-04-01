package io.rezoome.manager.network.entity.response;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;

public class ResponseAuthenticationArgsEntity extends AbstractEntity implements ResponseArgsEntity {

  @SerializedName("code")
  private String code;

  @SerializedName("msg")
  private String msg;

  @SerializedName("token")
  private String token;

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

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
