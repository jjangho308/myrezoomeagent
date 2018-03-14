package io.rezoome.manager.database.entity.agency;

import io.rezoome.manager.database.entity.DBRsltEntity;

public class OpicResultEntity implements DBRsltEntity {
  private String name;
  private String examNo;
  private String grade;
  private String examDate;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getExamNo() {
    return examNo;
  }

  public void setExamNo(String examNo) {
    this.examNo = examNo;
  }

  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  public String getExamDate() {
    return examDate;
  }

  public void setExamDate(String examDate) {
    this.examDate = examDate;
  }

  @Override
  public String toString() {
    return "OpicResultEntity [id=" + name + ", examNo=" + examNo + "]";
  }

}
