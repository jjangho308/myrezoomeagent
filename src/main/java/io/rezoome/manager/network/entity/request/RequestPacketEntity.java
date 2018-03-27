package io.rezoome.manager.network.entity.request;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;

/**
 * Network request packet entity. <br />
 * 
 * @since 1.0.0
 *
 */
public class RequestPacketEntity extends AbstractEntity {


  @SerializedName("mid")
  private String mid;

  @SerializedName("cmd")
  private String cmd;

  @SerializedName("code")
  private String code;

  @SerializedName("args")
  private RequestArgsEntity args;

  public String getMid() {
    return mid;
  }

  public void setMid(String mid) {
    this.mid = mid;
  }

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


  public RequestArgsEntity getArgs() {
    return args;
  }

  public void setArgs(RequestArgsEntity args) {
    this.args = args;
  }

  @Override
  public String toString() {
    return "RequestPacketEntity [mid=" + mid + ", cmd=" + cmd + ", code=" + code + ", args=" + args + "]";
  }

}
