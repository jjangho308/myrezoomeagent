package io.rezoome.external.opic.entity;

import io.rezoome.external.common.entity.AgencyKeyEntity;
import io.rezoome.external.common.entity.AgencyUserEntity;

public class OpicUserResultEntity extends AgencyKeyEntity {

  private String id;
  private String name;
  private String ci;
  private String phone;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    super.key = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCi() {
    return ci;
  }

  public void setCi(String ci) {
    this.ci = ci;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

}
