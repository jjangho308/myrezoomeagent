package io.rezoome.external.mk.mapper;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rezoome.constants.ErrorCodeConstants;
import io.rezoome.exception.ServiceException;
import io.rezoome.external.common.entity.AgencyKeyEntity;
import io.rezoome.external.common.entity.AgencyResultEntity;
import io.rezoome.external.common.mapper.AbstractExternalMapper;
import io.rezoome.external.mk.entity.MkSubIdEntity;
import io.rezoome.lib.json.JSON;
import io.rezoome.lib.json.Jsonable;
import io.rezoome.manager.provider.ManagerProvider;
import io.rezoome.manager.vianetwork.entity.request.ViaRequestPacketEntity;

public class MkDaoMapper extends AbstractExternalMapper {

//  @Override
//  public List<AgencyResultEntity> getDataOfSubID(AgencyKeyEntity entity, AgencyResultEntity resEntity,String subId) throws ServiceException {
//    List<AgencyResultEntity> dbResultEntityList = null;
//    try {
//      switch (subId) {
//        case MkSubIdEntity.SUBID_MK_RCCNF0001:
//          dbResultEntityList = daoMgr.getDao().getCertRecords(entity);
//          break;
//        default:
//          throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNDEFINED);
//      }
//    } catch (IOException e) {
//      throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNABLE_TO_GET_DB_DATA, e);
//    }
//    return dbResultEntityList;
//  }
  
  @Override
  public List<AgencyResultEntity> getDataOfSubID(AgencyKeyEntity entity, AgencyResultEntity resEntity,String subId) throws ServiceException {

    List<AgencyResultEntity> dbResultEntityList = null;
    
    System.out.println("MK GetViaData");
    ViaRequestPacketEntity req = new ViaRequestPacketEntity("http://rezoome.io:8080/result", JSON.toJson((Jsonable) entity));
    
    //ViaResponsePacketEntity res = new MkResponsePacketEntity();
    dbResultEntityList = ManagerProvider.via().request(req, resEntity);
    
    try {
      switch (subId) {
        case MkSubIdEntity.SUBID_MK_RCCNF0001:
          dbResultEntityList = daoMgr.getDao().getCertRecords(entity);
          break;
        default:
          throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNDEFINED);
      }
    } catch (IOException e) {
      throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNABLE_TO_GET_DB_DATA, e);
    }
    return dbResultEntityList;
  }
}
