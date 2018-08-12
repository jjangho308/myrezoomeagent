package io.rezoome.external.inha.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rezoome.constants.Constants;
import io.rezoome.external.common.entity.AgencyUserEntity;
import io.rezoome.external.inha.entity.InhaResponseResultArgsEntity;
import io.rezoome.external.inha.entity.InhaResultEntity;
import io.rezoome.manager.mapper.Mapper;
import io.rezoome.manager.mapper.MapperEntity;

public class InhaMapper implements Mapper {

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
//
//  @Override
//  public MapperEntity convert(Object dbResultEntity) throws NullPointerException {
//    // TODO Auto-generated method stub
//
//    if (dbResultEntity == null) {
//      throw new NullPointerException();
//    }
//    InhaMapperEntity mapperEntity = new InhaMapperEntity();
//    mapperEntity.setName(((InhaResponseResultArgsEntity) dbResultEntity).getName() == null ? null : ((InhaResponseResultArgsEntity) dbResultEntity).getName());
//    mapperEntity.setDate(((InhaResponseResultArgsEntity) dbResultEntity).getStartDate() + " ~ " + ((InhaResponseResultArgsEntity) dbResultEntity).getEndDate());
//    mapperEntity.setGrade(((InhaResponseResultArgsEntity) dbResultEntity).getGrade() == null ? null : ((InhaResponseResultArgsEntity) dbResultEntity).getGrade());
//    mapperEntity.setId(((InhaResponseResultArgsEntity) dbResultEntity).getId() == null ? null : ((InhaResponseResultArgsEntity) dbResultEntity).getId());
//    mapperEntity.setSub(((InhaResponseResultArgsEntity) dbResultEntity).getSub() == null ? null : ((InhaResponseResultArgsEntity) dbResultEntity).getSub());
//    mapperEntity.setStatus(((InhaResponseResultArgsEntity) dbResultEntity).getStatus() == null ? null : ((InhaResponseResultArgsEntity) dbResultEntity).getStatus());
//    mapperEntity.setEntranceDate(((InhaResponseResultArgsEntity) dbResultEntity).getStartDate() == null ? null : ((InhaResponseResultArgsEntity) dbResultEntity).getStartDate());
//    mapperEntity.setGraduDate(((InhaResponseResultArgsEntity) dbResultEntity).getEndDate() == null ? null : ((InhaResponseResultArgsEntity) dbResultEntity).getEndDate());
//    return mapperEntity;
//  }
//
//  @Override
//  public Map<String, Object> convert(List<Object> dbResultEntityList) throws NullPointerException {
//    // TODO Auto-generated method stub
//
//    if (dbResultEntityList == null) {
//      throw new NullPointerException();
//    }
//
//    Map<String, Object> mapperEntityMap = new HashMap<String, Object>();
//    List<MapperEntity> mapperEntityList = new ArrayList<MapperEntity>();
//    for (Object dbEntity : dbResultEntityList) {
//      InhaMapperEntity mapperEntity = new InhaMapperEntity();
//      mapperEntity.setName(((InhaResponseResultArgsEntity) dbEntity).getName() == null ? null : ((InhaResponseResultArgsEntity) dbEntity).getName());
//      mapperEntity.setDate(((InhaResponseResultArgsEntity) dbEntity).getStartDate() + " ~ " + ((InhaResponseResultArgsEntity) dbEntity).getEndDate());
//      mapperEntity.setGrade(((InhaResponseResultArgsEntity) dbEntity).getGrade() == null ? null : ((InhaResponseResultArgsEntity) dbEntity).getGrade());
//      mapperEntity.setId(((InhaResponseResultArgsEntity) dbEntity).getId() == null ? null : ((InhaResponseResultArgsEntity) dbEntity).getId());
//      mapperEntity.setSub(((InhaResponseResultArgsEntity) dbEntity).getSub() == null ? null : ((InhaResponseResultArgsEntity) dbEntity).getSub());
//      mapperEntity.setStatus(((InhaResponseResultArgsEntity) dbEntity).getStatus() == null ? null : ((InhaResponseResultArgsEntity) dbEntity).getStatus());
//      mapperEntity.setEntranceDate(((InhaResponseResultArgsEntity) dbEntity).getStartDate() == null ? null : ((InhaResponseResultArgsEntity) dbEntity).getStartDate());
//      mapperEntity.setGraduDate(((InhaResponseResultArgsEntity) dbEntity).getEndDate() == null ? null : ((InhaResponseResultArgsEntity) dbEntity).getEndDate());
//      mapperEntityList.add(mapperEntity);
//    }
//    mapperEntityMap.put(Constants.PARAM_LIST, mapperEntityList);
//    return mapperEntityMap;
//  }
}
