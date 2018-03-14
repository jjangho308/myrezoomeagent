package io.rezoome.entity;

import io.rezoome.manager.network.entity.RequestArgsEntity;

public final class RzmRsltEntity implements RequestArgsEntity{

  String keyEnc;
  String dataEnc;
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
  
}
