package io.rezoome.manager.mapper;

import java.util.List;

import io.rezoome.manager.database.entity.DBRsltEntity;

public interface Mapper {
  public MapperEntity convert(DBRsltEntity entity);

  public List<MapperEntity> convert(List<DBRsltEntity> dbResultEntityList);
}
