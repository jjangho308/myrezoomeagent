package io.rezoome.manager.network.entity.request;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;

public class RequestSearchRecordsEntity extends AbstractEntity implements RequestArgsEntity {

  @SerializedName("encData")
  String encData;

  @SerializedName("hashData")
  String hashData;

  @SerializedName("stored")
  String stored;

  public String getEncData() {
    return encData;
  }

  public void setEncData(String encData) {
    this.encData = encData;
  }

  public String getHashData() {
    return hashData;
  }

  public void setHashData(String hashData) {
    this.hashData = hashData;
  }

  public String getStored() {
    return stored;
  }

  public void setStored(String stored) {
    this.stored = stored;
  }

}
