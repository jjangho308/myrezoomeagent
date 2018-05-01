package io.rezoome.manager.vianetwork.entity.request;


import java.util.HashMap;
import java.util.Map;
import io.rezoome.core.entity.AbstractEntity;
import io.rezoome.manager.networkv2.ContentType;

public class ViaRequestPacketEntity extends AbstractEntity {
  private final Map<String, String> header = new HashMap<>();
  private final String data;

  public ViaRequestPacketEntity(String data) {    
    this.data = data;
  }

  public Map<String, String> getHeader() {
    return header;
  }

  public String getData() {
    return data;
  }

  {
    this.header.put("Content-Type", ContentType.APPLICATION_JSON.getValue());
  }
}