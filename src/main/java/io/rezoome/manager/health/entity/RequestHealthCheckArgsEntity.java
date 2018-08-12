package io.rezoome.manager.health.entity;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;
import io.rezoome.manager.network.entity.request.RequestArgsEntity;

public class RequestHealthCheckArgsEntity extends AbstractEntity implements RequestArgsEntity {

  @SerializedName("orgId")
  private String orgId;

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  
}
