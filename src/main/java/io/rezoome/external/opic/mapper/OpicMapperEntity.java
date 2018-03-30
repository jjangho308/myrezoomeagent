package io.rezoome.external.opic.mapper;

import io.rezoome.manager.mapper.MapperEntity;

public class OpicMapperEntity extends MapperEntity {

  private String testid;
  private String phone;
  private String lang;

  public String getTestid() {
    return testid;
  }

  public void setTestid(String testid) {
    this.testid = testid;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getLang() {
    return lang;
  }

  public void setLang(String lang) {
    this.lang = lang;
  }

}
