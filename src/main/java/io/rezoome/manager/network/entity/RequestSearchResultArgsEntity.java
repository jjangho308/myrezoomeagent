package io.rezoome.manager.network.entity;

import io.rezoome.core.entity.AbstractEntity;

public class RequestSearchResultArgsEntity extends AbstractEntity implements RequestArgsEntity {

  private String orgCode;
  private String encryptedData;
  private String hashedData;
  private String encryptedKey;

  public String getOrgCode() {
    return orgCode;
  }

  public void setOrgCode(String orgCode) {
    this.orgCode = orgCode;
  }

  public String getEncryptedData() {
    return encryptedData;
  }

  public void setEncryptedData(String encryptedData) {
    this.encryptedData = encryptedData;
  }

  public String getHashedData() {
    return hashedData;
  }

  public void setHashedData(String hashedData) {
    this.hashedData = hashedData;
  }

  public String getEncryptedKey() {
    return encryptedKey;
  }

  public void setEncryptedKey(String encryptedKey) {
    this.encryptedKey = encryptedKey;
  }
}
