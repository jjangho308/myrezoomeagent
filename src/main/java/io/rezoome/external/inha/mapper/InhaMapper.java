package io.rezoome.external.inha.mapper;

import java.util.ArrayList;
import java.util.List;

import io.rezoome.external.inha.entity.InhaResultEntity;
import io.rezoome.manager.database.entity.DBRsltEntity;
import io.rezoome.manager.mapper.Mapper;
import io.rezoome.manager.mapper.MapperEntity;

public class InhaMapper implements Mapper {

  @Override
  public MapperEntity convert(DBRsltEntity entity) throws NullPointerException {
    // TODO Auto-generated method stub

    if (entity == null) {
      throw new NullPointerException();
    }

    InhaMapperEntity mapperEntity = new InhaMapperEntity();
    mapperEntity.setName(((InhaResultEntity) entity).getName() == null ? "" : ((InhaResultEntity) entity).getName());
    mapperEntity.setDate(((InhaResultEntity) entity).getStartDate() + " ~ " + ((InhaResultEntity) entity).getEndDate());
    mapperEntity.setGrade(((InhaResultEntity) entity).getGrade() == null ? "" : ((InhaResultEntity) entity).getGrade());
    mapperEntity.setId(((InhaResultEntity) entity).getId() == null ? "" : ((InhaResultEntity) entity).getId());
    mapperEntity.setStatus(((InhaResultEntity) entity).getStatus() == null ? "" : ((InhaResultEntity) entity).getStatus());
    mapperEntity.setEntranceDate(((InhaResultEntity) entity).getStartDate() == null ? "" : ((InhaResultEntity) entity).getStartDate());
    mapperEntity.setGraduDate(((InhaResultEntity) entity).getEndDate() == null ? "" : ((InhaResultEntity) entity).getEndDate());
    return mapperEntity;
  }

  @Override
  public List<MapperEntity> convert(List<DBRsltEntity> dbResultEntityList) throws NullPointerException {
    // TODO Auto-generated method stub

    if (dbResultEntityList == null) {
      throw new NullPointerException();
    }

    List<MapperEntity> mapperEntityList = new ArrayList<MapperEntity>();
    InhaMapperEntity mapperEntity = null;
    for (DBRsltEntity dbEntity : dbResultEntityList) {
      mapperEntity = new InhaMapperEntity();
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
    return mapperEntityList;
  }
}
