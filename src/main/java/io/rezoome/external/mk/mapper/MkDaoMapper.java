package io.rezoome.external.mk.mapper;


import java.util.List;
import java.util.Map;

import io.rezoome.constants.ErrorCodeConstants;
import io.rezoome.exception.ServiceException;
import io.rezoome.external.common.entity.AgencyErrEntity;
import io.rezoome.external.common.entity.AgencyKeyEntity;
import io.rezoome.external.common.entity.AgencyResultEntity;
import io.rezoome.external.common.mapper.AbstractExternalMapper;
import io.rezoome.external.mk.entity.MkSubIdEntity;
import io.rezoome.lib.json.JSON;
import io.rezoome.manager.provider.ManagerProvider;
import io.rezoome.manager.vianetwork.entity.request.ViaRequestPacketEntity;
import io.rezoome.manager.vianetwork.entity.response.ViaResponsePacketEntity;

public class MkDaoMapper extends AbstractExternalMapper {

  // @Override
  // public List<AgencyResultEntity> getDataOfSubID(AgencyKeyEntity entity, AgencyResultEntity
  // resEntity,String subId) throws ServiceException {
  // List<AgencyResultEntity> dbResultEntityList = null;
  // try {
  // switch (subId) {
  // case MkSubIdEntity.SUBID_MK_RCCNF0001:
  // dbResultEntityList = daoMgr.getDao().getCertRecords(entity);
  // break;
  // default:
  // throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNDEFINED);
  // }
  // } catch (IOException e) {
  // throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNABLE_TO_GET_DB_DATA, e);
  // }
  // return dbResultEntityList;
  // }

  @Override
  public String getViaDataOfSubID(AgencyKeyEntity entity, String subId) throws ServiceException {

    String dbResultEntityList = null;

    try {
      switch (subId) {
        case MkSubIdEntity.SUBID_MK_RCCNF0001:
          ViaRequestPacketEntity req = new ViaRequestPacketEntity("http://rezoome.io:8080/result", JSON.toJson(entity));          
          dbResultEntityList = ManagerProvider.via().request(req);
          break;
        default:
          throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNDEFINED);
      }
    } catch (Exception e) {
      throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNABLE_TO_GET_DATA, e);
    }
    return dbResultEntityList;
  }

  @Override
  public AgencyResultEntity getDbDataOfSubID(AgencyKeyEntity entity, String subId) throws ServiceException {
    // TODO Auto-generated method stub
    return null;
  }


}
