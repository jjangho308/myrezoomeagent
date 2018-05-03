package io.rezoome.external.inha.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rezoome.constants.Constants;
import io.rezoome.external.entity.AgencyUserEntity;
import io.rezoome.external.inha.entity.InhaResultEntity;
import io.rezoome.manager.mapper.Mapper;
import io.rezoome.manager.mapper.MapperEntity;

public class InhaMapper implements Mapper {

  @Override
  public MapperEntity convert(Object dbResultEntity) throws NullPointerException {
    // TODO Auto-generated method stub

    if (dbResultEntity == null) {
      throw new NullPointerException();
    }

    InhaMapperEntity mapperEntity = new InhaMapperEntity();
    mapperEntity.setName(((InhaResultEntity) dbResultEntity).getName() == null ? null : ((InhaResultEntity) dbResultEntity).getName());
    mapperEntity.setDate(((InhaResultEntity) dbResultEntity).getStartDate() + " ~ " + ((InhaResultEntity) dbResultEntity).getEndDate());
    mapperEntity.setGrade(((InhaResultEntity) dbResultEntity).getGrade() == null ? null : ((InhaResultEntity) dbResultEntity).getGrade());
    mapperEntity.setId(((InhaResultEntity) dbResultEntity).getId() == null ? null : ((InhaResultEntity) dbResultEntity).getId());
    mapperEntity.setSub(((InhaResultEntity) dbResultEntity).getSub() == null ? null : ((InhaResultEntity) dbResultEntity).getSub());
    mapperEntity.setStatus(((InhaResultEntity) dbResultEntity).getStatus() == null ? null : ((InhaResultEntity) dbResultEntity).getStatus());
    mapperEntity.setEntranceDate(((InhaResultEntity) dbResultEntity).getStartDate() == null ? null : ((InhaResultEntity) dbResultEntity).getStartDate());
    mapperEntity.setGraduDate(((InhaResultEntity) dbResultEntity).getEndDate() == null ? null : ((InhaResultEntity) dbResultEntity).getEndDate());
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
      InhaMapperEntity mapperEntity = new InhaMapperEntity();
      mapperEntity.setName(((InhaResultEntity) dbEntity).getName() == null ? null : ((InhaResultEntity) dbEntity).getName());
      mapperEntity.setDate(((InhaResultEntity) dbEntity).getStartDate() + " ~ " + ((InhaResultEntity) dbEntity).getEndDate());
      mapperEntity.setGrade(((InhaResultEntity) dbEntity).getGrade() == null ? null : ((InhaResultEntity) dbEntity).getGrade());
      mapperEntity.setId(((InhaResultEntity) dbEntity).getId() == null ? null : ((InhaResultEntity) dbEntity).getId());
      mapperEntity.setSub(((InhaResultEntity) dbEntity).getSub() == null ? null : ((InhaResultEntity) dbEntity).getSub());
      mapperEntity.setStatus(((InhaResultEntity) dbEntity).getStatus() == null ? null : ((InhaResultEntity) dbEntity).getStatus());
      mapperEntity.setEntranceDate(((InhaResultEntity) dbEntity).getStartDate() == null ? null : ((InhaResultEntity) dbEntity).getStartDate());
      mapperEntity.setGraduDate(((InhaResultEntity) dbEntity).getEndDate() == null ? null : ((InhaResultEntity) dbEntity).getEndDate());
      mapperEntityList.add(mapperEntity);
    }
    mapperEntityMap.put(Constants.PARAM_LIST, mapperEntityList);
    return mapperEntityMap;
  }
}
