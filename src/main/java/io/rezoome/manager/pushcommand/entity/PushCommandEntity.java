package io.rezoome.manager.pushcommand.entity;

import io.rezoome.core.entity.Entity;

public interface PushCommandEntity extends Entity {
  public void setMid(String mid);
  public void setSid(String sid);
  public void setCmd(String cmd);
}