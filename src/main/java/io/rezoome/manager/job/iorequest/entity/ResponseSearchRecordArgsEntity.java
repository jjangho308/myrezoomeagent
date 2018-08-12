package io.rezoome.manager.job.iorequest.entity;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;
import io.rezoome.manager.network.entity.response.ResponseArgsEntity;

public class ResponseSearchRecordArgsEntity extends AbstractEntity implements ResponseArgsEntity {

  @SerializedName("value")
  public boolean value;
  
}
