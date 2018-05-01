package io.rezoome.manager.network.entity.response;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;
import io.rezoome.lib.json.JSON;
import io.rezoome.manager.provider.ManagerProvider;

/**
 * Network response entity. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
public class ResponsePacketEntity extends AbstractEntity {

  static {
    JSON.registerDeserializer("cmd", "result", ManagerProvider.clsarrange().getEntityCodeMap(ResponseArgsEntity.class));
  }
//
//  @SerializedName("mid")
//  private String mid;
//
  @SerializedName("cmd")
  private String cmd;

  @SerializedName("code")
  private String code;

  @SerializedName("result")
  private ResponseArgsEntity result;

//  public String getMid() {
//    return mid;
//  }
//
//  public void setMid(String mid) {
//    this.mid = mid;
//  }
//
  public String getCmd() {
    return cmd;
  }

  public void setCmd(String cmd) {
    this.cmd = cmd;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public ResponseArgsEntity getResult() {
    return result;
  }

  public void setResult(ResponseArgsEntity result) {
    this.result = result;
  }

  public ResponsePacketEntity() {
    super();
  }

//  @Override
//  public String toString() {
//    return "ResponsePacketEntity [mid=" + mid + ", cmd=" + cmd + ", code=" + code + ", result=" + result + "]";
//  }
  
  @Override
  public String toString() {
    return "ResponsePacketEntity [ cmd=" + cmd + ", code=" + code + ", result=" + result + "]";
  }
}
