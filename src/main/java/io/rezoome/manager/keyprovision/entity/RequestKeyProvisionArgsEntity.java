package io.rezoome.manager.keyprovision.entity;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;
import io.rezoome.manager.network.entity.request.RequestArgsEntity;

public class RequestKeyProvisionArgsEntity extends AbstractEntity implements RequestArgsEntity {

  @SerializedName("orgId")
  private String orgId;

  @SerializedName("pubkey")
  private String pubKey;

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  public String getPubKey() {
    return pubKey;
  }

  public void setPubKey(String pubKey) {
    this.pubKey = pubKey;
  }


}
