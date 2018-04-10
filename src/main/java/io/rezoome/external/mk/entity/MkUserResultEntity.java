package io.rezoome.external.mk.entity;

import io.rezoome.manager.database.entity.DBRsltEntity;

public class MkUserResultEntity implements DBRsltEntity {

  private String userid;
  private String name;
  private String birth;
  private String phone;
  private String email;
  private String gender;


  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBirth() {
    return birth;
  }

  public void setBirth(String birth) {
    this.birth = birth;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

}
