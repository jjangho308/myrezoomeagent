package io.rezoome.external.kyungki.entity.cert;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;
import io.rezoome.external.common.entity.AgencyResultEntity;

public class RegisterEntity extends AgencyResultEntity{
  
  // 학번 
  @SerializedName("std_no")
  public String std_no;
  
  // 이름
  @SerializedName("name")
  public String name;
 
  // 생년월일
  @SerializedName("birth")
  public String birth;  
    
  // 소속 대학/대학원
  @SerializedName("univ_course")
  public String univ_course;  

  //소속대학(계열)
  @SerializedName("univ_affiliation")
  public String univ_affiliation;
  
  //대학분류 : 학부/학과
  @SerializedName("univ_group")
  public String univ_group;
  
  // 1전공
  @SerializedName("major_first")
  public String major_first;
  
  // 2전공
  @SerializedName("major_second")
  public String major_second;  
  
  //2전공
  @SerializedName("grade")
  public String grade;  
 
  
  // 과정구분 : 학사/석사/박사
  @SerializedName("course")
  public String course;  
  
  // 시작일자 ( 입학/과정 등)
  @SerializedName("admission_date")
  public String admission_date;
  
  // 학적 변동 일자 ( 졸업/휴학 등)
  @SerializedName("change_date")
  public String change_date;
  
  // 학적 상태 : 0:재학, 1:졸업, 3:휴학, 4:퇴학
  @SerializedName("status")
  public String status;
  
  // 번호
  @SerializedName("issue_num")
  public String issue_num;

  public String getStd_no() {
    return std_no;
  }

  public void setStd_no(String std_no) {
    this.std_no = std_no;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBirth() {
    return birth;
  }

  public void setBirth(String birth) {
    this.birth = birth;
  }

  public String getUniv_course() {
    return univ_course;
  }

  public void setUniv_course(String univ_course) {
    this.univ_course = univ_course;
  }

  public String getUniv_affiliation() {
    return univ_affiliation;
  }

  public void setUniv_affiliation(String univ_affiliation) {
    this.univ_affiliation = univ_affiliation;
  }

  public String getUniv_group() {
    return univ_group;
  }

  public void setUniv_group(String univ_group) {
    this.univ_group = univ_group;
  }

  public String getCourse() {
    return course;
  }

  public void setCourse(String course) {
    this.course = course;
  }

  public String getAdmission_date() {
    return admission_date;
  }

  public void setAdmission_date(String admission_date) {
    this.admission_date = admission_date;
  }

  public String getChange_date() {
    return change_date;
  }

  public void setChange_date(String change_date) {
    this.change_date = change_date;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getIssue_num() {
    return issue_num;
  }

  public void setIssue_num(String issue_num) {
    this.issue_num = issue_num;
  }
  
  
  
  
  
}
