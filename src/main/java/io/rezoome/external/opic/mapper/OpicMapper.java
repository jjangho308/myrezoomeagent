package io.rezoome.external.opic.mapper;

import java.util.ArrayList;
import java.util.List;

import io.rezoome.external.opic.entity.OpicResultEntity;
import io.rezoome.manager.database.entity.DBRsltEntity;
import io.rezoome.manager.mapper.Mapper;
import io.rezoome.manager.mapper.MapperEntity;

public class OpicMapper implements Mapper {

  @Override
  public List<MapperEntity> convert(List<DBRsltEntity> dbResultEntityList) {
    // TODO Auto-generated method stub


    if (dbResultEntityList == null) {
      throw new NullPointerException();
    }

    List<MapperEntity> mapperEntityList = new ArrayList<MapperEntity>();
    for (DBRsltEntity dbEntity : dbResultEntityList) {
      OpicMapperEntity mapperEntity = new OpicMapperEntity();
      mapperEntity.setName(((OpicResultEntity) dbEntity).getName() == null ? null : ((OpicResultEntity) dbEntity).getName());
      mapperEntity.setDate(((OpicResultEntity) dbEntity).getTestDay() == null ? null : ((OpicResultEntity) dbEntity).getTestDay());
      mapperEntity.setGrade(((OpicResultEntity) dbEntity).getRating() == null ? null : ((OpicResultEntity) dbEntity).getRating());
      mapperEntity.setTestid(((OpicResultEntity) dbEntity).getTestId() == null ? null : ((OpicResultEntity) dbEntity).getTestId());
      mapperEntity.setLang(((OpicResultEntity) dbEntity).getLanguage() == null ? null : ((OpicResultEntity) dbEntity).getLanguage());
      mapperEntity.setPhone(((OpicResultEntity) dbEntity).getPhone() == null ? null : ((OpicResultEntity) dbEntity).getPhone());
      mapperEntityList.add(mapperEntity);
    }

    return mapperEntityList;
  }

}
