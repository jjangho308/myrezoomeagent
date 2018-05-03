package io.rezoome.manager.amq;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;

public class AMQMessageCryptoEntity extends AbstractEntity {

  @SerializedName("key")
  private final String key = null;

  @SerializedName("iv")
  private final String iv = null;

  @SerializedName("msg")
  private final String msg = null;

  public String getKey() {
    return key;
  }

  public String getIv() {
    return iv;
  }

  public String getMsg() {
    return msg;
  }

  @Override
  public String toString() {
    return "AMQMessageCryptoEntity [key=" + key + ", iv=" + iv + ", msg=" + msg + "]";
  }

}
