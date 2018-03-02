package io.rezoome.manager.network.entity;

import io.rezoome.core.entity.AbstractEntity;

public class RequestRegistrationArgsEntity extends AbstractEntity implements RequestArgsEntity {

  private String orgCode;
  private String orgPasscode;
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
