package io.rezoome.external.cau.entity;

import io.rezoome.external.common.entity.AgencyResultEntity;

public class CauResultEntity extends AgencyResultEntity {

  private String name;
  private String id;
  private String status;
  private String startDate;
  private String endDate;
  private String sub;
  private String grade;
  private String professor;
  private String entranceDate;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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
