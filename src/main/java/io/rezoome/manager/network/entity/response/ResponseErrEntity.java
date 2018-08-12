package io.rezoome.manager.network.entity.response;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;

public class ResponseErrEntity  extends AbstractEntity{
  @SerializedName("code")
  int code;
  
  @SerializedName("msg")
  String msg;
  
}
