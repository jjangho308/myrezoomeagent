package io.rezoome.external.mk.entity;

import io.rezoome.manager.database.entity.DBRsltEntity;

public class MkResultEntity implements DBRsltEntity {

  private String name;
  private String phone;
  private String userid;
  private String grade;
  private String point0;
  private String point1;
  private String point2;
  private String point3;
  private String date;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  public String getPoint0() {
    return point0;
  }

  public void setPoint0(String point0) {
    this.point0 = point0;
  }

  public String getPoint1() {
    return point1;
  }

  public void setPoint1(String point1) {
    this.point1 = point1;
  }

  public String getPoint2() {
    return point2;
  }

  public void setPoint2(String point2) {
    this.point2 = point2;
  }

  public String getPoint3() {
    return point3;
  }

  public void setPoint3(String point3) {
    this.point3 = point3;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }



}
