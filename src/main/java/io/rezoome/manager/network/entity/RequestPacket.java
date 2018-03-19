package io.rezoome.manager.network.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rezoome.core.entity.AbstractEntity;
import io.rezoome.manager.networkv2.ContentType;

public class RequestPacket extends AbstractEntity {
  private final Map<String, String> header = new HashMap<>();
  private final String sid;
  private final String data;
  private final List<File> fileList = new ArrayList<>();

  public RequestPacket(String sid, String data) {
    this.sid = sid;
    this.data = data;
  }

  public Map<String, String> getHeader() {
    return header;
  }

  public String getSid() {
    return sid;
  }

  public String getData() {
    return data;
  }

  public List<File> getFileList() {
    return fileList;
  }

  {
    this.header.put("Content-Type", ContentType.APPLICATION_JSON.getValue());
    this.header.put("Authorization", "Bearer ");
  }
}
