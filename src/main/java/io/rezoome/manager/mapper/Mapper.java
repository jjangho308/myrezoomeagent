package io.rezoome.manager.mapper;

import java.util.List;
import java.util.Map;

import io.rezoome.external.common.entity.AgencyUserEntity;

public interface Mapper {

  public MapperEntity convert(Object dbResultEntity);
  public Map<String, Object> convert(List<Object> dbResultEntityList);

  // public List<MapperEntity> convert(List<DBRsltEntity> dbResultEntityList);

}
