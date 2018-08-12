package io.rezoome.external.kmu.entity;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import io.rezoome.external.common.entity.AgencyResultEntity;
import io.rezoome.external.common.entity.university.InfoEntity;
import io.rezoome.external.common.entity.university.RegisterEntity;
import io.rezoome.external.common.entity.university.ScoreEntity;
import io.rezoome.external.common.entity.university.ScoreStatisticEntity;

public class KmuResponseResultArgsEntity extends AgencyResultEntity {
  
  @SerializedName("univInfo")
  public InfoEntity univInfo;
  
  @SerializedName("registList")
  public List<AgencyResultEntity> registList;
  
  @SerializedName("scoreList")
  public List<AgencyResultEntity> scoreList;
  
  @SerializedName("scoreStatisticList")
  public List<AgencyResultEntity> scoreStatisticList;

  public InfoEntity getUnivInfo() {
    return univInfo;
  }

  public List<AgencyResultEntity> getRegistList() {
    return registList;
  }

  public void setRegistList(List<AgencyResultEntity> registList) {
    this.registList = registList;
  }

  public List<AgencyResultEntity> getScoreList() {
    return scoreList;
  }

  public void setScoreList(List<AgencyResultEntity> scoreList) {
    this.scoreList = scoreList;
  }

  public List<AgencyResultEntity> getScoreStatisticList() {
    return scoreStatisticList;
  }

  public void setScoreStatisticList(List<AgencyResultEntity> scoreStatisticList) {
    this.scoreStatisticList = scoreStatisticList;
  }

  public void setUnivInfo(InfoEntity univInfo) {
    this.univInfo = univInfo;
  }

  
  
}
