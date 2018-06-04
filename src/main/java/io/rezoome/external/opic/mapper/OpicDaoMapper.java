package io.rezoome.external.opic.mapper;

import java.util.List;

import io.rezoome.constants.ErrorCodeConstants;
import io.rezoome.exception.ServiceException;
import io.rezoome.external.common.entity.AgencyKeyEntity;
import io.rezoome.external.common.entity.AgencyResultEntity;
import io.rezoome.external.common.mapper.AbstractExternalMapper;
import io.rezoome.external.opic.entity.OpicResponseResultArgsEntity;
import io.rezoome.external.opic.entity.OpicSubIdEntity;
import io.rezoome.lib.json.JSON;
import io.rezoome.manager.provider.ManagerProvider;
import io.rezoome.manager.vianetwork.entity.request.ViaRequestPacketEntity;

public class OpicDaoMapper extends AbstractExternalMapper {


  @Override
  public String getViaDataOfSubID(AgencyKeyEntity entity, String subId) throws ServiceException {
    String dbResultEntityList = null;

    try {
      switch (subId) {
        case OpicSubIdEntity.SUBID_OPIC_RCLPT0005:
          ViaRequestPacketEntity req = new ViaRequestPacketEntity("", JSON.toJson(entity));          
          dbResultEntityList = ManagerProvider.via().request(req);
          System.out.println("dbResultEntityList : " + dbResultEntityList);

          
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
