package io.rezoome.external.opic.mapper;

import java.util.ArrayList;
import java.util.List;

import io.rezoome.external.opic.entity.OpicResultEntity;
import io.rezoome.manager.database.entity.DBRsltEntity;
import io.rezoome.manager.mapper.Mapper;
import io.rezoome.manager.mapper.MapperEntity;

public class OpicMapper implements Mapper {

  @Override
  public MapperEntity convert(DBRsltEntity entity) throws NullPointerException {

    if (entity == null) {
      throw new NullPointerException();
    }

    OpicMapperEntity mapperEntity = new OpicMapperEntity();

    mapperEntity.setName(((OpicResultEntity) entity).getName() == null ? "" : ((OpicResultEntity) entity).getName());
    mapperEntity.setGrade(((OpicResultEntity) entity).getRating() == null ? "" : ((OpicResultEntity) entity).getRating());
    mapperEntity.setDate(((OpicResultEntity) entity).getTestDay() == null ? "" : ((OpicResultEntity) entity).getTestDay());
    mapperEntity.setPhone(((OpicResultEntity) entity).getPhone() == null ? "" : ((OpicResultEntity) entity).getPhone());
    mapperEntity.setLang(((OpicResultEntity) entity).getLanguage() == null ? "" : ((OpicResultEntity) entity).getLanguage());
    mapperEntity.setTestid(((OpicResultEntity) entity).getTestId() == null ? "" : ((OpicResultEntity) entity).getTestId());

    return mapperEntity;
  }

  @Override
  public List<MapperEntity> convert(List<DBRsltEntity> dbResultEntityList) {
    // TODO Auto-generated method stub
    List<MapperEntity> mapperEntityList = new ArrayList<MapperEntity>();
    OpicMapperEntity mapperEntity = null;
    for (DBRsltEntity dbEntity : dbResultEntityList) {
      mapperEntity = new OpicMapperEntity();
      mapperEntity.setName(((OpicResultEntity) dbEntity).getName() == null ? "" : ((OpicResultEntity) dbEntity).getName());
      mapperEntity.setDate(((OpicResultEntity) dbEntity).getTestDay() == null ? "" : ((OpicResultEntity) dbEntity).getTestDay());
      mapperEntity.setGrade(((OpicResultEntity) dbEntity).getRating() == null ? "" : ((OpicResultEntity) dbEntity).getRating());
      mapperEntity.setTestid(((OpicResultEntity) dbEntity).getTestId() == null ? "" : ((OpicResultEntity) dbEntity).getTestId());
      mapperEntity.setLang(((OpicResultEntity) dbEntity).getLanguage() == null ? "" : ((OpicResultEntity) dbEntity).getLanguage());
      mapperEntity.setPhone(((OpicResultEntity) dbEntity).getPhone() == null ? "" : ((OpicResultEntity) dbEntity).getPhone());
      mapperEntityList.add(mapperEntity);
    }

    return mapperEntityList;
  }

}
