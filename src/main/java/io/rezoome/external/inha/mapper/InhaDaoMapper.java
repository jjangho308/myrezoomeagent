package io.rezoome.external.inha.mapper;

import java.io.IOException;
import java.util.List;

import io.rezoome.constants.ErrorCodeConstants;
import io.rezoome.exception.ServiceException;
import io.rezoome.external.common.entity.AgencyErrEntity;
import io.rezoome.external.common.entity.AgencyKeyEntity;
import io.rezoome.external.common.entity.AgencyResultEntity;
import io.rezoome.external.common.mapper.AbstractExternalMapper;
import io.rezoome.external.inha.entity.InhaSubIdEntity;
import io.rezoome.manager.vianetwork.entity.response.ViaResponsePacketEntity;

public class InhaDaoMapper extends AbstractExternalMapper {

  @Override
  public List<AgencyResultEntity> getDbDataOfSubID(AgencyKeyEntity entity, AgencyResultEntity resEntity, String subId) throws ServiceException {
    List<AgencyResultEntity> dbResultEntityList = null;
    try {
      switch (subId) {
        case InhaSubIdEntity.SUBID_INHA_RCOGC0008:
          dbResultEntityList = daoMgr.getDao().getJolupRecord(entity);
          break;
        case InhaSubIdEntity.SUBID_INHA_RCOGC0009:
          dbResultEntityList = daoMgr.getDao().getSungjukRecord(entity);
          break;
        default:
          throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNDEFINED);
      }
    } catch (IOException e) {
      throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNABLE_TO_GET_DATA, e);
    }
    return dbResultEntityList;
  }


  @Override
  public List<AgencyResultEntity> getViaDataOfSubID(AgencyKeyEntity entity, ViaResponsePacketEntity agencyRes, AgencyResultEntity aResult, AgencyErrEntity agencyErr, String subId)
      throws ServiceException {
    // TODO Auto-generated method stub
    return null;
  }

}
