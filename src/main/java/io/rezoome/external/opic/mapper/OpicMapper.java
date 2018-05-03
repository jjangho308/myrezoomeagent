package io.rezoome.external.opic.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rezoome.constants.Constants;
import io.rezoome.external.entity.AgencyUserEntity;
import io.rezoome.external.opic.entity.OpicResultEntity;
import io.rezoome.manager.mapper.Mapper;
import io.rezoome.manager.mapper.MapperEntity;

public class OpicMapper implements Mapper {

  @Override
  public MapperEntity convert(Object dbResultEntity) throws NullPointerException {
    // TODO Auto-generated method stub

    if (dbResultEntity == null) {
      throw new NullPointerException();
    }

    OpicMapperEntity mapperEntity = new OpicMapperEntity();
    mapperEntity.setName(((OpicResultEntity) dbResultEntity).getName() == null ? null : ((OpicResultEntity) dbResultEntity).getName());
    mapperEntity.setDate(((OpicResultEntity) dbResultEntity).getTestDay() == null ? null : ((OpicResultEntity) dbResultEntity).getTestDay());
    mapperEntity.setGrade(((OpicResultEntity) dbResultEntity).getRating() == null ? null : ((OpicResultEntity) dbResultEntity).getRating());
    mapperEntity.setTestid(((OpicResultEntity) dbResultEntity).getTestId() == null ? null : ((OpicResultEntity) dbResultEntity).getTestId());
    mapperEntity.setLang(((OpicResultEntity) dbResultEntity).getLanguage() == null ? null : ((OpicResultEntity) dbResultEntity).getLanguage());
    mapperEntity.setPhone(((OpicResultEntity) dbResultEntity).getPhone() == null ? null : ((OpicResultEntity) dbResultEntity).getPhone());
    return mapperEntity;
  }

  @Override
  public Map<String, Object> convert(List<Object> dbResultEntityList) throws NullPointerException {
    // TODO Auto-generated method stub

    if (dbResultEntityList == null) {
      throw new NullPointerException();
    }

    Map<String, Object> mapperEntityMap = new HashMap<String, Object>();
    List<MapperEntity> mapperEntityList = new ArrayList<MapperEntity>();
    for (Object dbEntity : dbResultEntityList) {
      OpicMapperEntity mapperEntity = new OpicMapperEntity();
      mapperEntity.setName(((OpicResultEntity) dbEntity).getName() == null ? null : ((OpicResultEntity) dbEntity).getName());
      mapperEntity.setDate(((OpicResultEntity) dbEntity).getTestDay() == null ? null : ((OpicResultEntity) dbEntity).getTestDay());
      mapperEntity.setGrade(((OpicResultEntity) dbEntity).getRating() == null ? null : ((OpicResultEntity) dbEntity).getRating());
      mapperEntity.setTestid(((OpicResultEntity) dbEntity).getTestId() == null ? null : ((OpicResultEntity) dbEntity).getTestId());
      mapperEntity.setLang(((OpicResultEntity) dbEntity).getLanguage() == null ? null : ((OpicResultEntity) dbEntity).getLanguage());
      mapperEntity.setPhone(((OpicResultEntity) dbEntity).getPhone() == null ? null : ((OpicResultEntity) dbEntity).getPhone());
      mapperEntityList.add(mapperEntity);
    }
    mapperEntityMap.put(Constants.PARAM_LIST, mapperEntityList);
    return mapperEntityMap;
  }

}
