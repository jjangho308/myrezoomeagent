package io.rezoome.manager.network.entity.request;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;

public class RequestSearchRecordsEntity extends AbstractEntity implements RequestArgsEntity {

  @SerializedName("data")
  private String data;

  @SerializedName("hash")
  private String hash;

  @SerializedName("txid")
  private String txid;

  @SerializedName("subid")
  private String subid;

  @SerializedName("stored")
  private String stored;

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

  public String getTxid() {
    return txid;
  }

  public void setTxid(String txid) {
    this.txid = txid;
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
