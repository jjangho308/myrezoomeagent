package io.rezoome.manager.network.entity.request;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;

public class RequestSearchRecordsEntity extends AbstractEntity implements RequestArgsEntity {

  @SerializedName("data")
  String data;

  @SerializedName("hash")
  String hash;

  @SerializedName("subid")
  String subid;

  @SerializedName("stored")
  String stored;

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  public String getSubid() {
    return subid;
  }

  public void setSubid(String subid) {
    this.subid = subid;
  }

  public String getStored() {
    return stored;
  }

  public void setStored(String stored) {
    this.stored = stored;
  }

}
