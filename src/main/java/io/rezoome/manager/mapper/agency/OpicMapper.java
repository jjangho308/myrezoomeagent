package io.rezoome.manager.mapper.agency;

import java.util.List;

import io.rezoome.entity.RzmRsltEntity;
import io.rezoome.manager.database.entity.DBRsltEntity;
import io.rezoome.manager.database.entity.agency.OpicResultEntity;
import io.rezoome.manager.mapper.Mapper;
import io.rezoome.manager.mapper.MapperEntity;

public class OpicMapper implements Mapper {

  @Override
  public MapperEntity convert(DBRsltEntity entity) {
    // TODO Auto-generated method stub

    if (entity == null) {
      return null;
      
      
    }

    MapperEntity mapperEntity = new OpicMapperEntity();

    mapperEntity.setName(((OpicResultEntity)entity).getName());
    mapperEntity.setGrade(((OpicResultEntity)entity).getRating());
    mapperEntity.setDate(((OpicResultEntity)entity).getTestDay());
    return mapperEntity;
  }

  @Override
  public List<MapperEntity> convert(List<DBRsltEntity> entity) {
    // TODO Auto-generated method stub
    return null;
  }

}
