package io.rezoome.manager.pushcommand.entity;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;

/**
 * Abstract class of {@link PushCommandEntity}. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
public class AbstractPushCommandEntity extends AbstractEntity implements PushCommandEntity {

  @SerializedName("mid")
  protected String      mid   = null;

  @SerializedName("sid")
  protected  String      sid   = null;
  
  @SerializedName("cmd")
  protected  String      cmd   = null;

  @Override
  public void setMid(String mid) {
    this.mid = mid;
  }

  @Override
  public void setSid(String sid) {
    this.sid = sid;
  }

  @Override
  public void setCmd(String cmd) {
    this.cmd = cmd;
  }
  
}
