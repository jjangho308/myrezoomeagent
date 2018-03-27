package io.rezoome.entity;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;
import io.rezoome.core.entity.annotation.EntityType;
import io.rezoome.manager.network.entity.request.RequestArgsEntity;

public final class RzmRsltEntity extends AbstractEntity implements RequestArgsEntity {

  @SerializedName("orgCode")
  String orgCode;

  @SerializedName("encKey")
  String encKey;

  @SerializedName("encIv")
  String encIv;

  @SerializedName("records")
  List<RequestArgsEntity> records;

  public String getOrgCode() {
    return orgCode;
  }

  public void setOrgCode(String orgCode) {
    this.orgCode = orgCode;
  }

  public String getEncKey() {
    return encKey;
  }

  public void setEncKey(String encKey) {
    this.encKey = encKey;
  }

  public String getEncIv() {
    return encIv;
  }

  public void setEncIv(String encIv) {
    this.encIv = encIv;
  }

  public List<RequestArgsEntity> getRecords() {
    return records;
  }

  public void setRecords(List<RequestArgsEntity> records) {
    this.records = records;
  }

  @Override
  public EntityType getAnnotation() {
    // TODO Auto-generated method stub
    return null;
  }

}
