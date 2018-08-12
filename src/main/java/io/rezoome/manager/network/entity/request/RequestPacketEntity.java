package io.rezoome.manager.network.entity.request;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;

/**
 * Network request packet entity. <br />
 * 
 * @since 1.0.0
 *
 */
public class RequestPacketEntity extends AbstractEntity{

  @SerializedName("sid")
  private String sid;

  @SerializedName("mid")
  private String mid;

  @SerializedName("cmd")
  private String cmd;

  @SerializedName("args")
  private RequestArgsEntity args;

  
  public String getSid() {
    return sid;
  }

  public void setSid(String sid) {
    this.sid = sid;
  }
  
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

  public RequestArgsEntity getArgs() {
    return args;
  }

  public void setArgs(RequestArgsEntity args) {
    this.args = args;
  }

  @Override
  public String toString() {
    return "RequestPacketEntity [sid=" + sid + ", mid=" + mid + ", cmd=" + cmd + ", args=" + args + "]";
  }

}
