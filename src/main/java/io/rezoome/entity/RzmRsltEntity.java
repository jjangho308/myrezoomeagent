package io.rezoome.entity;

public final class RzmRsltEntity {
  
  String keyEnc;
  String dataEnc;
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
  String dataHash;
  
  
}
