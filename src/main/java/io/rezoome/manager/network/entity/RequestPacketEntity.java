package io.rezoome.manager.network.entity;

import io.rezoome.core.entity.AbstractEntity;

/**
 * Network request packet entity. <br />
 * @since 1.0.0
 *
 */
public class RequestPacketEntity extends AbstractEntity {
  private String protocol;

  public String getProtocol() {
    return protocol;
  }

  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }
  
  
}