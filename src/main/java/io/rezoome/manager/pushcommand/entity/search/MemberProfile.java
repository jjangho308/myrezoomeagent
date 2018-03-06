package io.rezoome.manager.pushcommand.entity.search;

import io.rezoome.core.entity.AbstractEntity;
import io.rezoome.lib.json.Jsonable;

public class MemberProfile extends AbstractEntity implements Jsonable{
  String username;
  String birth;
  String gender;
  String phone;
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getBirth() {
    return birth;
  }
  public void setBirth(String birth) {
    this.birth = birth;
  }
  public String getGender() {
    return gender;
  }
  public void setGender(String gender) {
    this.gender = gender;
  }
  public String getPhone() {
    return phone;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }
  
}