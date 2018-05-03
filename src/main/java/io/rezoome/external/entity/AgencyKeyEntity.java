package io.rezoome.external.entity;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;

public class AgencyKeyEntity extends AbstractEntity implements AgencyUserEntity{
  
  @SerializedName("key")
  public String key;  

  @SerializedName("key2")
  public String key2;

  @SerializedName("key3")
  public String key3;  

  @SerializedName("key4")
  public String key4;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getKey2() {
    return key2;
  }

  public void setKey2(String key2) {
    this.key2 = key2;
  }

  public String getKey3() {
    return key3;
  }

  public void setKey3(String key3) {
    this.key3 = key3;
  }

  public String getKey4() {
    return key4;
  }

  public void setKey4(String key4) {
    this.key4 = key4;
  }

  
  
  
  
}
