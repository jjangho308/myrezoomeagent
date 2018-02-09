package io.rezoome.vo;

public class OrgVOImpl implements OrgVO {
  String name;
  String phnNumber;
  String sex;
  String birth;
  String orgKey;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhnNumber() {
    return phnNumber;
  }

  public void setPhnNumber(String phnNumber) {
    this.phnNumber = phnNumber;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getBirth() {
    return birth;
  }

  public void setBirth(String birth) {
    this.birth = birth;
  }

  public String getOrgKey() {
    return orgKey;
  }

  public void setOrgKey(String orgKey) {
    this.orgKey = orgKey;
  }

  @Override
  public String toString() {
    return "OrgVOImpl [name=" + name + ", phnNumber=" + phnNumber + ", sex=" + sex + ", birth=" + birth + ", orgKey=" + orgKey + "]";
  }


}
