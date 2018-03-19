package io.rezoome.entity;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;
import io.rezoome.core.entity.annotation.EntityType;
import io.rezoome.manager.network.entity.request.RequestArgsEntity;

public final class RzmRsltEntity extends AbstractEntity implements RequestArgsEntity {

  @SerializedName("keyEnc")
  String keyEnc;

  @SerializedName("dataEnc")
  String dataEnc;

  @SerializedName("dataHash")
  String dataHash;

  public String getKeyEnc() {
    return keyEnc;
  }

  public void setKeyEnc(String keyEnc) {
    this.keyEnc = keyEnc;
  }

  public String getDataEnc() {
    return dataEnc;
  }

  public void setDataEnc(String dataEnc) {
    this.dataEnc = dataEnc;
  }

  public String getDataHash() {
    return dataHash;
  }

  public void setDataHash(String dataHash) {
    this.dataHash = dataHash;
  }

  @Override
  public EntityType getAnnotation() {
    // TODO Auto-generated method stub
    return null;
  }

}
