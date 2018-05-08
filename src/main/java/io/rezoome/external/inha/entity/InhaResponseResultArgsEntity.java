package io.rezoome.external.inha.entity;

import com.google.gson.annotations.SerializedName;

import io.rezoome.external.common.entity.AgencyResultEntity;

public class InhaResponseResultArgsEntity extends AgencyResultEntity {

  @SerializedName("id")
  private String id;
  
  @SerializedName("name")
  private String name;
  
  @SerializedName("status")
  private String status;
  
  @SerializedName("startDate")
  private String startDate;
  
  @SerializedName("endDate")
  private String endDate;
  
  @SerializedName("sub")
  private String sub;
  
  @SerializedName("grade")
  private String grade;
  
  @SerializedName("professor")
  private String professor;
  
  @SerializedName("entranceDate")
  private String entranceDate;

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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public String getSub() {
    return sub;
  }

  public void setSub(String sub) {
    this.sub = sub;
  }

  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  public String getProfessor() {
    return professor;
  }

  public void setProfessor(String professor) {
    this.professor = professor;
  }

  public String getEntranceDate() {
    return entranceDate;
  }

  public void setEntranceDate(String entranceDate) {
    this.entranceDate = entranceDate;
  }
  
  
}
