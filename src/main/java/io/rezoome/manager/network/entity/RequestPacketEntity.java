package io.rezoome.manager.network.entity;

import io.rezoome.core.entity.AbstractEntity;

/**
 * Network request packet entity. <br />
 * 
 * @since 1.0.0
 *
 */
public class RequestPacketEntity extends AbstractEntity {

  private String mid;
  private String cmd;
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

  public RequestArgsEntity getArgs() {
    return args;
  }

  public void setArgs(RequestArgsEntity args) {
    this.args = args;
  }

}
