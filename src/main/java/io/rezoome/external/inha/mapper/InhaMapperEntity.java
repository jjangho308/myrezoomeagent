package io.rezoome.external.inha.mapper;

import io.rezoome.manager.mapper.MapperEntity;

public class InhaMapperEntity extends MapperEntity {

  private String id;
  private String status;
  private String entranceDate;
  private String graduDate;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getEntranceDate() {
    return entranceDate;
  }

  public void setEntranceDate(String entranceDate) {
    this.entranceDate = entranceDate;
  }

  public String getGraduDate() {
    return graduDate;
  }

  public void setGraduDate(String graduDate) {
    this.graduDate = graduDate;
  }

}
