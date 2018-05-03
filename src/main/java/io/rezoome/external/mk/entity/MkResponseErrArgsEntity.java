package io.rezoome.external.mk.entity;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;
import io.rezoome.external.entity.AgencyErrEntity;

public class MkResponseErrArgsEntity extends AgencyErrEntity{
  
  @SerializedName("code")
  String code;
  
  @SerializedName("msg")
  String msg;

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
