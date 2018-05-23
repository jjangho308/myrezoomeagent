package io.rezoome.external.opic.mapper;

import java.io.IOException;
import java.util.List;
import io.rezoome.constants.ErrorCodeConstants;
import io.rezoome.exception.ServiceException;
import io.rezoome.external.common.entity.AgencyErrEntity;
import io.rezoome.external.common.entity.AgencyKeyEntity;
import io.rezoome.external.common.entity.AgencyResultEntity;
import io.rezoome.external.common.mapper.AbstractExternalMapper;
import io.rezoome.external.opic.entity.OpicSubIdEntity;
import io.rezoome.manager.vianetwork.entity.response.ViaResponsePacketEntity;

public class OpicDaoMapper extends AbstractExternalMapper {


  @Override
  public String getViaDataOfSubID(AgencyKeyEntity entity, String subId) throws ServiceException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public AgencyResultEntity getDbDataOfSubID(AgencyKeyEntity entity, String subId) throws ServiceException {
    // TODO Auto-generated method stub
    return null;
  }


}
