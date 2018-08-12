package io.rezoome.manager.auth.entity;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;
import io.rezoome.manager.network.entity.request.RequestArgsEntity;

public class RequestAuthenticationArgsEntity extends AbstractEntity implements RequestArgsEntity {

  @SerializedName("orgId")
  private String orgId;

  @SerializedName("passcode")
  private String orgPasscode;

  @SerializedName("name")
  private String orgName;

  

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  public String getOrgPasscode() {
    return orgPasscode;
  }

  public void setOrgPasscode(String orgPasscode) {
    this.orgPasscode = orgPasscode;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

}
