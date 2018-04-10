package io.rezoome.external.mk.mapper;

import java.util.ArrayList;
import java.util.List;

import io.rezoome.external.mk.entity.MkResultEntity;
import io.rezoome.manager.database.entity.DBRsltEntity;
import io.rezoome.manager.mapper.Mapper;
import io.rezoome.manager.mapper.MapperEntity;

public class MkMapper implements Mapper {

  @Override
  public MapperEntity convert(DBRsltEntity entity) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<MapperEntity> convert(List<DBRsltEntity> dbResultEntityList) {
    // TODO Auto-generated method stub
    if (dbResultEntityList == null) {
      throw new NullPointerException();
    }

    List<MapperEntity> mapperEntityList = new ArrayList<MapperEntity>();
    MkMapperEntity mapperEntity = null;
    for (DBRsltEntity dbEntity : dbResultEntityList) {
      mapperEntity = new MkMapperEntity();
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
    return mapperEntityList;
  }

}
