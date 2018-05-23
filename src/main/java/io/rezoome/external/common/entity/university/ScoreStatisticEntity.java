package io.rezoome.external.common.entity.university;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;
import io.rezoome.external.common.entity.AgencyResultEntity;

public class ScoreStatisticEntity extends AgencyResultEntity{
  
  // 학년도
  @SerializedName("year")
  public String year;

  // 학기
  @SerializedName("semester")
  public String semester;

  // 소속학과
  @SerializedName("department")
  public String department  ;

  // 이수학년: 1자리 숫자
  @SerializedName("completion_std_class")
  public String completion_std_class;

  //신청학점: 2자리숫자
  @SerializedName("score_enrolled")
  public String score_enrolled;  
  
  //취득학점: 2자리 숫자
  @SerializedName("scored_acquired")
  public String scored_acquired;
  
  //평균평점: 10자리 부동소수점(정수부1자리)
  @SerializedName("average_score")
  public String average_score;
  
  //석차: 3자리 숫자
  @SerializedName("ranking")
  public String ranking; 
  
  //인원: 3자리 숫자
  @SerializedName("people_count")
  public String people_count;


}
