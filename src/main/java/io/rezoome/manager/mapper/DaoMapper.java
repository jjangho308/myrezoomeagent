package io.rezoome.manager.mapper;

import java.util.List;
import java.util.Map;

import io.rezoome.exception.ServiceException;
import io.rezoome.manager.database.entity.DBEntity;
import io.rezoome.manager.database.entity.DBRsltEntity;

public interface DaoMapper {

  Map<String, Object> getUserData(DBEntity entity) throws ServiceException;

  List<List<DBRsltEntity>> getCertData(DBEntity entity, List<String> subIds) throws ServiceException;
}
