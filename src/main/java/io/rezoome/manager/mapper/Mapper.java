package io.rezoome.manager.mapper;

import java.util.List;
import java.util.Map;

import io.rezoome.manager.database.entity.DBRsltEntity;

public interface Mapper {

  public MapperEntity convert(DBRsltEntity dbResultEntity);

  public Map<String, Object> convert(List<DBRsltEntity> dbResultEntityList);

  // public List<MapperEntity> convert(List<DBRsltEntity> dbResultEntityList);

}
