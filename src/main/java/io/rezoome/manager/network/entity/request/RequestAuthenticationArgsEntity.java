package io.rezoome.manager.network.entity.request;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;

public class RequestAuthenticationArgsEntity extends AbstractEntity implements RequestArgsEntity {

  @SerializedName("orgcode")
  private String orgCode;

  @SerializedName("passcode")
  private String orgPasscode;

  @SerializedName("name")
  private String orgName;

  public String getOrgCode() {
    return orgCode;
  }

  public void setOrgCode(String orgCode) {
    this.orgCode = orgCode;
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
