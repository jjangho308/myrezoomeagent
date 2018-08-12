package io.rezoome.external.cau.entity.cert;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;
import io.rezoome.external.common.entity.AgencyResultEntity;

public class ScoreEntity extends AgencyResultEntity{
  
  // 학년도
  @SerializedName("year")
  public String year;

  // 학기
  @SerializedName("semester")
  public String semester;

  // 과목코드
  @SerializedName("lecture_no")
  public String lecture_no  ;

  // 과목명:
  @SerializedName("lecture_name")
  public String  lecture_name   ;

  // 학점
  @SerializedName("score_result")
  public String score_result;

  // 취득등급
  @SerializedName("grade_result")
  public String grade_result;

  // 성적 구분
  @SerializedName("grade_division")
  public String grade_division;
  
  // 증명서 번호
  @SerializedName("issue_num")
  public String issue_num;

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public String getSemester() {
    return semester;
  }

  public void setSemester(String semester) {
    this.semester = semester;
  }

  public String getLecture_no() {
    return lecture_no;
  }

  public void setLecture_no(String lecture_no) {
    this.lecture_no = lecture_no;
  }

  public String getLecture_name() {
    return lecture_name;
  }

  public void setLecture_name(String lecture_name) {
    this.lecture_name = lecture_name;
  }

  public String getScore_result() {
    return score_result;
  }

  public void setScore_result(String score_result) {
    this.score_result = score_result;
  }

  public String getGrade_result() {
    return grade_result;
  }

  public void setGrade_result(String grade_result) {
    this.grade_result = grade_result;
  }

  public String getGrade_division() {
    return grade_division;
  }

  public void setGrade_division(String grade_division) {
    this.grade_division = grade_division;
  }

  public String getIssue_num() {
    return issue_num;
  }

  public void setIssue_num(String issue_num) {
    this.issue_num = issue_num;
  }
  
  
}
