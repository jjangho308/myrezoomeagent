package io.rezoome.external.opic.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rezoome.constants.Constants;
import io.rezoome.external.common.entity.AgencyUserEntity;
import io.rezoome.external.opic.entity.OpicResponseResultArgsEntity;
import io.rezoome.manager.mapper.Mapper;
import io.rezoome.manager.mapper.MapperEntity;

public class OpicMapper implements Mapper {

  @Override
  public MapperEntity convert(Object dbResultEntity) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Map<String, Object> convert(List<Object> dbResultEntityList) {
    // TODO Auto-generated method stub
    return null;
  }
/*
  @Override
  public MapperEntity convert(Object dbResultEntity) throws NullPointerException {
    // TODO Auto-generated method stub

    if (dbResultEntity == null) {
      throw new NullPointerException();
    }

    OpicMapperEntity mapperEntity = new OpicMapperEntity();
    mapperEntity.setName(((OpicResponseResultArgsEntity) dbResultEntity).getName() == null ? null : ((OpicResponseResultArgsEntity) dbResultEntity).getName());
    mapperEntity.setDate(((OpicResponseResultArgsEntity) dbResultEntity).getTestDay() == null ? null : ((OpicResponseResultArgsEntity) dbResultEntity).getTestDay());
    mapperEntity.setGrade(((OpicResponseResultArgsEntity) dbResultEntity).getRating() == null ? null : ((OpicResponseResultArgsEntity) dbResultEntity).getRating());
    mapperEntity.setTestid(((OpicResponseResultArgsEntity) dbResultEntity).getTestId() == null ? null : ((OpicResponseResultArgsEntity) dbResultEntity).getTestId());
    mapperEntity.setLang(((OpicResponseResultArgsEntity) dbResultEntity).getLanguage() == null ? null : ((OpicResponseResultArgsEntity) dbResultEntity).getLanguage());
    mapperEntity.setPhone(((OpicResponseResultArgsEntity) dbResultEntity).getPhone() == null ? null : ((OpicResponseResultArgsEntity) dbResultEntity).getPhone());
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
      mapperEntity.setName(((OpicResponseResultArgsEntity) dbEntity).getName() == null ? null : ((OpicResponseResultArgsEntity) dbEntity).getName());
      mapperEntity.setDate(((OpicResponseResultArgsEntity) dbEntity).getTestDay() == null ? null : ((OpicResponseResultArgsEntity) dbEntity).getTestDay());
      mapperEntity.setGrade(((OpicResponseResultArgsEntity) dbEntity).getRating() == null ? null : ((OpicResponseResultArgsEntity) dbEntity).getRating());
      mapperEntity.setTestid(((OpicResponseResultArgsEntity) dbEntity).getTestId() == null ? null : ((OpicResponseResultArgsEntity) dbEntity).getTestId());
      mapperEntity.setLang(((OpicResponseResultArgsEntity) dbEntity).getLanguage() == null ? null : ((OpicResponseResultArgsEntity) dbEntity).getLanguage());
      mapperEntity.setPhone(((OpicResponseResultArgsEntity) dbEntity).getPhone() == null ? null : ((OpicResponseResultArgsEntity) dbEntity).getPhone());
      mapperEntityList.add(mapperEntity);
    }
    mapperEntityMap.put(Constants.PARAM_LIST, mapperEntityList);
    return mapperEntityMap;
  }
*/
}
