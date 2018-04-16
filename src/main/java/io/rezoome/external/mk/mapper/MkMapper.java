package io.rezoome.external.mk.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rezoome.constants.Constants;
import io.rezoome.external.mk.entity.MkResultEntity;
import io.rezoome.manager.database.entity.DBRsltEntity;
import io.rezoome.manager.mapper.Mapper;
import io.rezoome.manager.mapper.MapperEntity;

public class MkMapper implements Mapper {

  @Override
  public MapperEntity convert(DBRsltEntity dbResultEntity) throws NullPointerException {
    // TODO Auto-generated method stub

    if (dbResultEntity == null) {
      throw new NullPointerException();
    }

    MkMapperEntity mapperEntity = new MkMapperEntity();
    mapperEntity.setName(((MkResultEntity) dbResultEntity).getName() == null ? null : ((MkResultEntity) dbResultEntity).getName());
    mapperEntity.setDate(((MkResultEntity) dbResultEntity).getDate() == null ? null : ((MkResultEntity) dbResultEntity).getDate());
    mapperEntity.setGrade(((MkResultEntity) dbResultEntity).getGrade() == null ? null : ((MkResultEntity) dbResultEntity).getGrade());
    mapperEntity.setUserid(((MkResultEntity) dbResultEntity).getUserid() == null ? null : ((MkResultEntity) dbResultEntity).getUserid());
    mapperEntity.setPoint0(((MkResultEntity) dbResultEntity).getPoint0() == null ? null : ((MkResultEntity) dbResultEntity).getPoint0());
    mapperEntity.setPoint1(((MkResultEntity) dbResultEntity).getPoint1() == null ? null : ((MkResultEntity) dbResultEntity).getPoint1());
    mapperEntity.setPoint2(((MkResultEntity) dbResultEntity).getPoint2() == null ? null : ((MkResultEntity) dbResultEntity).getPoint2());
    mapperEntity.setPoint3(((MkResultEntity) dbResultEntity).getPoint3() == null ? null : ((MkResultEntity) dbResultEntity).getPoint3());
    return mapperEntity;
  }

  @Override
  public Map<String, Object> convert(List<DBRsltEntity> dbResultEntityList) throws NullPointerException {
    // TODO Auto-generated method stub

    if (dbResultEntityList == null) {
      throw new NullPointerException();
    }

    Map<String, Object> mapperEntityMap = new HashMap<String, Object>();
    List<MapperEntity> mapperEntityList = new ArrayList<MapperEntity>();
    for (DBRsltEntity dbEntity : dbResultEntityList) {
      MkMapperEntity mapperEntity = new MkMapperEntity();
      mapperEntity.setName(((MkResultEntity) dbEntity).getName() == null ? null : ((MkResultEntity) dbEntity).getName());
      mapperEntity.setDate(((MkResultEntity) dbEntity).getDate() == null ? null : ((MkResultEntity) dbEntity).getDate());
      mapperEntity.setGrade(((MkResultEntity) dbEntity).getGrade() == null ? null : ((MkResultEntity) dbEntity).getGrade());
      mapperEntity.setUserid(((MkResultEntity) dbEntity).getUserid() == null ? null : ((MkResultEntity) dbEntity).getUserid());
      mapperEntity.setPoint0(((MkResultEntity) dbEntity).getPoint0() == null ? null : ((MkResultEntity) dbEntity).getPoint0());
      mapperEntity.setPoint1(((MkResultEntity) dbEntity).getPoint1() == null ? null : ((MkResultEntity) dbEntity).getPoint1());
      mapperEntity.setPoint2(((MkResultEntity) dbEntity).getPoint2() == null ? null : ((MkResultEntity) dbEntity).getPoint2());
      mapperEntity.setPoint3(((MkResultEntity) dbEntity).getPoint3() == null ? null : ((MkResultEntity) dbEntity).getPoint3());
      mapperEntityList.add(mapperEntity);
    }
    mapperEntityMap.put(Constants.PARAM_LIST, mapperEntityList);
    return mapperEntityMap;
  }

}
