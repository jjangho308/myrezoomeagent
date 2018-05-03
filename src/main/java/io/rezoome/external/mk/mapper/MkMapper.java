package io.rezoome.external.mk.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rezoome.constants.Constants;
import io.rezoome.external.entity.AgencyUserEntity;
import io.rezoome.external.mk.entity.MkResponseResultArgsEntity;
import io.rezoome.manager.mapper.Mapper;
import io.rezoome.manager.mapper.MapperEntity;

public class MkMapper implements Mapper {

  @Override
  public MapperEntity convert(Object dbResultEntity) throws NullPointerException {
    // TODO Auto-generated method stub

    if (dbResultEntity == null) {
      throw new NullPointerException();
    }

    
    MkMapperEntity mapperEntity = new MkMapperEntity();    
    mapperEntity.setName(((MkResponseResultArgsEntity) dbResultEntity).getName() == null ? null : ((MkResponseResultArgsEntity) dbResultEntity).getName());
    mapperEntity.setDate(((MkResponseResultArgsEntity) dbResultEntity).getDate() == null ? null : ((MkResponseResultArgsEntity) dbResultEntity).getDate());
    mapperEntity.setGrade(((MkResponseResultArgsEntity) dbResultEntity).getGrade() == null ? null : ((MkResponseResultArgsEntity) dbResultEntity).getGrade());
    mapperEntity.setUserid(((MkResponseResultArgsEntity) dbResultEntity).getUserid() == null ? null : ((MkResponseResultArgsEntity) dbResultEntity).getUserid());
    mapperEntity.setPoint0(((MkResponseResultArgsEntity) dbResultEntity).getPoint0() == null ? null : ((MkResponseResultArgsEntity) dbResultEntity).getPoint0());
    mapperEntity.setPoint1(((MkResponseResultArgsEntity) dbResultEntity).getPoint1() == null ? null : ((MkResponseResultArgsEntity) dbResultEntity).getPoint1());
    mapperEntity.setPoint2(((MkResponseResultArgsEntity) dbResultEntity).getPoint2() == null ? null : ((MkResponseResultArgsEntity) dbResultEntity).getPoint2());
    mapperEntity.setPoint3(((MkResponseResultArgsEntity) dbResultEntity).getPoint3() == null ? null : ((MkResponseResultArgsEntity) dbResultEntity).getPoint3());
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
      MkMapperEntity mapperEntity = new MkMapperEntity();
      mapperEntity.setName(((MkResponseResultArgsEntity) dbEntity).getName() == null ? null : ((MkResponseResultArgsEntity) dbEntity).getName());
      mapperEntity.setDate(((MkResponseResultArgsEntity) dbEntity).getDate() == null ? null : ((MkResponseResultArgsEntity) dbEntity).getDate());
      mapperEntity.setGrade(((MkResponseResultArgsEntity) dbEntity).getGrade() == null ? null : ((MkResponseResultArgsEntity) dbEntity).getGrade());
      mapperEntity.setUserid(((MkResponseResultArgsEntity) dbEntity).getUserid() == null ? null : ((MkResponseResultArgsEntity) dbEntity).getUserid());
      mapperEntity.setPoint0(((MkResponseResultArgsEntity) dbEntity).getPoint0() == null ? null : ((MkResponseResultArgsEntity) dbEntity).getPoint0());
      mapperEntity.setPoint1(((MkResponseResultArgsEntity) dbEntity).getPoint1() == null ? null : ((MkResponseResultArgsEntity) dbEntity).getPoint1());
      mapperEntity.setPoint2(((MkResponseResultArgsEntity) dbEntity).getPoint2() == null ? null : ((MkResponseResultArgsEntity) dbEntity).getPoint2());
      mapperEntity.setPoint3(((MkResponseResultArgsEntity) dbEntity).getPoint3() == null ? null : ((MkResponseResultArgsEntity) dbEntity).getPoint3());
      mapperEntityList.add(mapperEntity);
    }
    mapperEntityMap.put(Constants.PARAM_LIST, mapperEntityList);
    return mapperEntityMap;
  }

}
