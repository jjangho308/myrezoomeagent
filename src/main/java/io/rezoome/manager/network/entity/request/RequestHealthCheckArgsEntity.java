package io.rezoome.manager.network.entity.request;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;

public class RequestHealthCheckArgsEntity extends AbstractEntity implements RequestArgsEntity {

  @SerializedName("orgCode")
  private String orgCode;

  public void setOrgCode(String orgCode) {
    this.orgCode = orgCode;
  }

  public String getOrgCode() {
    return orgCode;
  }
}
