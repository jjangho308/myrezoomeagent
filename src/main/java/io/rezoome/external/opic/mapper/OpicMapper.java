package io.rezoome.external.opic.mapper;

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

    // MapperEntity mapperEntity = new OpicMapperEntity();
    OpicMapperEntity mapperEntity = new OpicMapperEntity();
    mapperEntity.setName(((OpicResultEntity) entity).getName() == null ? "" : ((OpicResultEntity) entity).getName());
    mapperEntity.setGrade(((OpicResultEntity) entity).getRating() == null ? "" : ((OpicResultEntity) entity).getRating());
    mapperEntity.setDate(((OpicResultEntity) entity).getTestDay() == null ? "" : ((OpicResultEntity) entity).getTestDay());
    mapperEntity.setPhone(((OpicResultEntity) entity).getPhone() == null ? "" : ((OpicResultEntity) entity).getPhone());
    mapperEntity.setLang(((OpicResultEntity) entity).getLanguage() == null ? "" : ((OpicResultEntity) entity).getLanguage());
    mapperEntity.setTestid(((OpicResultEntity) entity).getTestId() == null ? "" : ((OpicResultEntity) entity).getTestId());

    return mapperEntity;
  }

}
