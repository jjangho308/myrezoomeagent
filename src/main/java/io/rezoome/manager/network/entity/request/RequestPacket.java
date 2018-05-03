package io.rezoome.manager.network.entity.request;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rezoome.constants.GlobalEntity;
import io.rezoome.core.entity.AbstractEntity;
import io.rezoome.manager.network.entity.NetworkPacket;
import io.rezoome.manager.networkv2.ContentType;

public class RequestPacket extends AbstractEntity implements NetworkPacket{
  private final Map<String, String> header = new HashMap<>();
  private final String url;
  private final String data;
  private final List<File> fileList = new ArrayList<>();

  public RequestPacket(String url, String data) {
    this.url = url;
    this.data = data;
  }

  public Map<String, String> getHeader() {
    return header;
  }

  public String getUrl() {
    return url;
  }

  public String getData() {
    return data;
  }

  public List<File> getFileList() {
    return fileList;
  }

  {
    this.header.put("Content-Type", ContentType.APPLICATION_JSON.getValue());
    this.header.put("Authorization", "Bearer " + GlobalEntity.TOKEN);
  }
}
