package io.rezoome.manager.network.entity.request;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;
import io.rezoome.core.entity.annotation.EntityType;

public class RequestSearchArgsEntity extends AbstractEntity implements RequestArgsEntity {

  @SerializedName("code")
  String code;

  @SerializedName("orgcode")
  String orgCode;

  @SerializedName("key")
  String key;

  @SerializedName("iv")
  String iv;

  @SerializedName("records")
  List<RequestArgsEntity> records;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getOrgCode() {
    return orgCode;
  }

  public void setOrgCode(String orgCode) {
    this.orgCode = orgCode;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getIv() {
    return iv;
  }

  public void setIv(String iv) {
    this.iv = iv;
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
