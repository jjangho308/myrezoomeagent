package io.rezoome.manager.mapper.agency;

import java.util.List;

import io.rezoome.entity.RzmRsltEntity;
import io.rezoome.manager.database.entity.DBRsltEntity;
import io.rezoome.manager.mapper.Mapper;

public class OpicMapper implements Mapper {

  @Override
  public RzmRsltEntity convert(DBRsltEntity entity) {
    // TODO Auto-generated method stub

    if (entity == null) {
      return null;
    }

    RzmRsltEntity rsltEntity = new RzmRsltEntity();

    return rsltEntity;
  }

  @Override
  public List<RzmRsltEntity> convert(List<DBRsltEntity> entity) {
    // TODO Auto-generated method stub
    return null;
  }

}
