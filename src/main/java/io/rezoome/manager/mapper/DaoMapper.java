package io.rezoome.manager.mapper;

import java.util.List;
import java.util.Map;

import io.rezoome.exception.ServiceException;
import io.rezoome.manager.database.entity.DBEntity;

public interface DaoMapper {

  Map<String, Object> getUserData(DBEntity entity) throws ServiceException;

  Map<String, Object> getCertData(DBEntity entity, List<String> subIds) throws ServiceException;

  Map<String, Object> getCertDataWithRequireKey(DBEntity entity, List<String> subIds) throws ServiceException;
}
