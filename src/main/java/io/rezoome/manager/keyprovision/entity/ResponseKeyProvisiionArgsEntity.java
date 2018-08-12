package io.rezoome.manager.keyprovision.entity;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;
import io.rezoome.manager.network.entity.response.ResponseArgsEntity;

public class ResponseKeyProvisiionArgsEntity extends AbstractEntity implements ResponseArgsEntity {
  
  @SerializedName("value")
  public boolean value;
  
  
}
