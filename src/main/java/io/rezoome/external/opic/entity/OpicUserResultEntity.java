package io.rezoome.external.opic.entity;

import io.rezoome.manager.database.entity.DBRsltEntity;

public class OpicUserResultEntity implements DBRsltEntity {

  private String id;
  private String name;
  private String ci;
  private String phone;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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
