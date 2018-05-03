package io.rezoome.external.inha.mapper;

import java.io.IOException;
import java.util.List;

import io.rezoome.constants.ErrorCodeConstants;
import io.rezoome.exception.ServiceException;
import io.rezoome.external.entity.AgencyKeyEntity;
import io.rezoome.external.entity.AgencyResultEntity;
import io.rezoome.external.inha.entity.InhaSubIdEntity;
import io.rezoome.external.mapper.AbstractExternalMapper;

public class InhaDaoMapper extends AbstractExternalMapper {

  @Override
  public List<AgencyResultEntity> getDataOfSubID(AgencyKeyEntity entity, AgencyResultEntity resEntity, String subId) throws ServiceException {
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
      throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNABLE_TO_GET_DB_DATA, e);
    }
    return dbResultEntityList;
  }

}
