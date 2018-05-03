package io.rezoome.external.common.mapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rezoome.constants.Constants;
import io.rezoome.constants.ErrorCodeConstants;
import io.rezoome.exception.ServiceException;
import io.rezoome.external.common.entity.AgencyKeyEntity;
import io.rezoome.external.common.entity.AgencyResultEntity;
import io.rezoome.manager.database.dao.DaoManagerImpl;
import io.rezoome.manager.database.entity.UserEntity;
import io.rezoome.manager.mapper.DaoMapper;
import io.rezoome.manager.provider.ManagerProvider;

public abstract class AbstractExternalMapper implements DaoMapper{
  protected DaoManagerImpl daoMgr = null;

  protected enum STATUS {
    USER_EXIST, USER_NOT_EXIST, REQUIRE_KEY
  }

  public AbstractExternalMapper() {
    daoMgr = ManagerProvider.database().getDaoManager();
  }

  @Override
  public Map<String, Object> getUserData(UserEntity entity) throws ServiceException {
    // TODO Auto-generated method stub
    Map<String, Object> dbResult = new HashMap<String, Object>();
    try {
      List<AgencyKeyEntity> dbResultEntityList = daoMgr.getDao().getUserRecords(entity);
      if (dbResultEntityList.size() == 1) {
       
            // TODO 좀더 이쁜 코드로
            
            AgencyKeyEntity agencyKeyEntity = dbResultEntityList.get(0);
            dbResult.put(Constants.PARAM_STATUS, STATUS.USER_EXIST);
            dbResult.put(Constants.PARAM_ENTITY, agencyKeyEntity);
          
        
      } else if (dbResultEntityList.size() > 1) {        
        dbResult.put(Constants.PARAM_STATUS, STATUS.REQUIRE_KEY);
      }else {
        dbResult.put(Constants.PARAM_STATUS, STATUS.USER_NOT_EXIST);
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNABLE_TO_GET_DB_DATA, e);
    }
    return dbResult;
  }

  @Override
  public Map<String, Object> getCertData(AgencyKeyEntity entity, AgencyResultEntity resEntity, List<String> subIds) throws ServiceException {
    // TODO Auto-generated method stub
    Map<String, Object> dbResultEntityListMap = new HashMap<String, Object>();
    List<AgencyResultEntity> dbResultEntityList = null;

    
    try {
      if (subIds == null || subIds.size() == 0) {
        throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNDEFINED);
      }

      for (String subId : subIds) {
        dbResultEntityList = getDataOfSubID(entity, resEntity, subId);
        dbResultEntityListMap.put(subId, dbResultEntityList);
      }

    } catch (Exception e) {
      // TODO Auto-generated catch block
      throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNABLE_TO_GET_DB_DATA, e);
    }

    return dbResultEntityListMap;
  }

  @Override
  public Map<String, Object> getCertDataWithRequireKey(UserEntity entity, List<String> subIds) throws ServiceException {
    // TODO Auto-generated method stub
    return null;
  }

  
}