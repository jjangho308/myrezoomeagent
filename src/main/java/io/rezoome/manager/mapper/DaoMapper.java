package io.rezoome.manager.mapper;

import java.util.List;
import java.util.Map;

import io.rezoome.exception.ServiceException;
import io.rezoome.external.entity.AgencyKeyEntity;
import io.rezoome.external.entity.AgencyResultEntity;
import io.rezoome.manager.database.entity.UserEntity;

public interface DaoMapper {

  Map<String, Object> getUserData(UserEntity entity) throws ServiceException;
  Map<String, Object> getCertData(AgencyKeyEntity entity, AgencyResultEntity resEntity, List<String> subIds) throws ServiceException;
  Map<String, Object> getCertDataWithRequireKey(UserEntity entity, List<String> subIds) throws ServiceException;

  List<AgencyResultEntity> getDataOfSubID(AgencyKeyEntity entity,  AgencyResultEntity resEntity, String subId) throws ServiceException;
  
}
