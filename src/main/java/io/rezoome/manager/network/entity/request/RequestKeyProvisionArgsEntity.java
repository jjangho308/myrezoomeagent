package io.rezoome.manager.network.entity.request;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;

public class RequestKeyProvisionArgsEntity extends AbstractEntity implements RequestArgsEntity {

  @SerializedName("orgcode")
  private String orgCode;

  @SerializedName("pubkey")
  private String pubKey;

  public void setOrgCode(String orgCode) {
    this.orgCode = orgCode;
  }

  public String getOrgCode() {
    return orgCode;
  }

  public String getPubKey() {
    return pubKey;
  }

  public void setPubKey(String pubKey) {
    this.pubKey = pubKey;
  }

}
