package io.rezoome.manager.health.entity;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;
import io.rezoome.manager.network.entity.request.RequestArgsEntity;

public class RequestHealthCheckArgsEntity extends AbstractEntity implements RequestArgsEntity {

  @SerializedName("orgcode")
  private String orgCode;

  public void setOrgCode(String orgCode) {
    this.orgCode = orgCode;
  }

  public String getOrgCode() {
    return orgCode;
  }
}
