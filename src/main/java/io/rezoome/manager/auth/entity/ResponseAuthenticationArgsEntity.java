package io.rezoome.manager.auth.entity;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;
import io.rezoome.manager.network.entity.response.ResponseArgsEntity;

public class ResponseAuthenticationArgsEntity extends AbstractEntity implements ResponseArgsEntity {

  @SerializedName("code")
  private String code;

  @SerializedName("msg")
  private String msg;

  @SerializedName("token")
  private String token;

  @SerializedName("keystored")
  private String keyStored;

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

  public String getKeyStored() {
    return keyStored;
  }

  public void setKeyStored(String keyStored) {
    this.keyStored = keyStored;
  }
}
