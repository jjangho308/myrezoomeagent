package io.rezoome.manager.database.entity.agency;

import io.rezoome.manager.database.entity.DBRsltEntity;

public class OpicResultEntity implements DBRsltEntity {
  private String name;
  private String cname;
  private String testDay;
  private String rating;
  private String birth;
  private String testId;
  private String testRoom;
  private String testName;
  private String language;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCname() {
    return cname;
  }

  public void setCname(String cname) {
    this.cname = cname;
  }

  public String getTestDay() {
    return testDay;
  }

  public void setTestDay(String testDay) {
    this.testDay = testDay;
  }

  public String getRating() {
    return rating;
  }

  public void setRating(String rating) {
    this.rating = rating;
  }

  public String getBirth() {
    return birth;
  }

  public void setBirth(String birth) {
    this.birth = birth;
  }

  public String getTestId() {
    return testId;
  }

  public void setTestId(String testId) {
    this.testId = testId;
  }

  public String getTestRoom() {
    return testRoom;
  }

  public void setTestRoom(String testRoom) {
    this.testRoom = testRoom;
  }

  public String getTestName() {
    return testName;
  }

  public void setTestName(String testName) {
    this.testName = testName;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  @Override
  public String toString() {
    return "OpicResultEntity [name=" + name + ", cname=" + cname + ", testDay=" + testDay + ", rating=" + rating + ", birth=" + birth + ", testId=" + testId + ", testRoom=" + testRoom + ", testName="
        + testName + ", language=" + language + "]";
  }


}
