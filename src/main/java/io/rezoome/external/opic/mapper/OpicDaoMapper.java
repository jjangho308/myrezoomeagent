package io.rezoome.external.opic.mapper;

import java.io.IOException;
import java.util.List;
import io.rezoome.constants.ErrorCodeConstants;
import io.rezoome.exception.ServiceException;
import io.rezoome.external.common.entity.AgencyKeyEntity;
import io.rezoome.external.common.entity.AgencyResultEntity;
import io.rezoome.external.common.mapper.AbstractExternalMapper;
import io.rezoome.external.opic.entity.OpicSubIdEntity;

public class OpicDaoMapper extends AbstractExternalMapper {

  @Override
  public List<AgencyResultEntity> getDataOfSubID(AgencyKeyEntity entity, AgencyResultEntity resEntity,String subId) throws ServiceException {
   List<AgencyResultEntity> dbResultEntityList = null;
    try {
      switch (subId) {
        case OpicSubIdEntity.SUBID_OPIC_RCLPT0005:
          dbResultEntityList = daoMgr.getDao().getCertRecords(entity);
          break;
        case OpicSubIdEntity.SUBID_OPIC_RCLPT0006:
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
