package io.rezoome.external.common.mapper;

import java.util.List;
import java.util.Map;

import io.rezoome.exception.ServiceException;
import io.rezoome.external.common.entity.AgencyErrEntity;
import io.rezoome.external.common.entity.AgencyKeyEntity;
import io.rezoome.external.common.entity.AgencyResultEntity;
import io.rezoome.manager.database.entity.UserEntity;
import io.rezoome.manager.vianetwork.entity.response.ViaResponsePacketEntity;

public interface DaoMapper {

  Map<String, Object> getUserData(UserEntity entity) throws ServiceException;
  Map<String, Object> getCertDataDB(AgencyKeyEntity entity, AgencyResultEntity resEntity, List<String> subIds) throws ServiceException;
  Map<String, Object> getCertDataVia(AgencyKeyEntity entity, ViaResponsePacketEntity agencyRes,   AgencyResultEntity aResult, AgencyErrEntity agencyErr, List<String> subIds) throws ServiceException;
  Map<String, Object> getCertDataWithRequireKey(UserEntity entity, List<String> subIds) throws ServiceException;
  List<AgencyResultEntity> getDbDataOfSubID(AgencyKeyEntity entity,  AgencyResultEntity resEntity, String subId) throws ServiceException;
  List<AgencyResultEntity> getViaDataOfSubID(AgencyKeyEntity entity,  ViaResponsePacketEntity agencyRes,   AgencyResultEntity aResult, AgencyErrEntity agencyErr , String subId) throws ServiceException;
  
}
