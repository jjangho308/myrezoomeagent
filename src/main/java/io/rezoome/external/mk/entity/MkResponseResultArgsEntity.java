package io.rezoome.external.mk.entity;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;
import io.rezoome.external.common.entity.AgencyResultEntity;

public class MkResponseResultArgsEntity  extends AgencyResultEntity {

  @SerializedName("name")
  private String name;
  
  @SerializedName("phone")
  private String phone;
  
  @SerializedName("userid")
  private String userid;
  
  @SerializedName("grade")
  private String grade;
  
  @SerializedName("point0")
  private String point0;
  
  @SerializedName("point1")
  private String point1;
  
  @SerializedName("point2")
  private String point2;
  
  @SerializedName("point3")
  private String point3;
  
  @SerializedName("date")
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
