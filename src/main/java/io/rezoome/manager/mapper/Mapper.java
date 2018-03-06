package io.rezoome.manager.mapper;

import java.util.List;

import io.rezoome.entity.RzmRsltEntity;
import io.rezoome.manager.database.entity.DBRsltEntity;

public interface Mapper {  
  public RzmRsltEntity convert(DBRsltEntity entity);
  public List<RzmRsltEntity> convert(List<DBRsltEntity> entity);
}
