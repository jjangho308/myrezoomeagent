package io.rezoome.external.common.mapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rezoome.constants.Constants;
import io.rezoome.constants.ErrorCodeConstants;
import io.rezoome.exception.ServiceException;
import io.rezoome.external.common.entity.AgencyErrEntity;
import io.rezoome.external.common.entity.AgencyKeyEntity;
import io.rezoome.external.common.entity.AgencyResultEntity;
import io.rezoome.manager.database.dao.DaoManagerImpl;
import io.rezoome.manager.database.entity.UserEntity;
import io.rezoome.manager.provider.ManagerProvider;
import io.rezoome.manager.vianetwork.entity.response.ViaResponsePacketEntity;
import net.minidev.json.JSONObject;

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
     
      System.out.println("1111");
      if (dbResultEntityList.size() == 1) {       
            // TODO 좀더 이쁜 코드로
          System.out.println("2222");
            AgencyKeyEntity agencyKeyEntity = dbResultEntityList.get(0);
            dbResult.put(Constants.PARAM_STATUS, STATUS.USER_EXIST);
            dbResult.put(Constants.PARAM_ENTITY, agencyKeyEntity);          
        
      } else if (dbResultEntityList.size() > 1) {
        System.out.println("3333");
        dbResult.put(Constants.PARAM_STATUS, STATUS.REQUIRE_KEY);
      }else {
        System.out.println("4444");
        dbResult.put(Constants.PARAM_STATUS, STATUS.USER_NOT_EXIST);
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNABLE_TO_GET_DATA, e);
    }
    return dbResult;
  }

  @Override
  public Map<String, Object> getCertDataDB(AgencyKeyEntity entity, List<String> subIds) throws ServiceException {
    // TODO Auto-generated method stub
    Map<String, Object> dbResultEntityListMap = new HashMap<String, Object>();
    AgencyResultEntity resultData;

    
    try {
      if (subIds == null || subIds.size() == 0) {
        throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNDEFINED);
      }

      for (String subId : subIds) {
        resultData = getDbDataOfSubID(entity, subId);        
        dbResultEntityListMap.put(subId, resultData);
      }

    } catch (Exception e) {
      // TODO Auto-generated catch block
      throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNABLE_TO_GET_DATA, e);
    }

    return dbResultEntityListMap;
  }
  
  @Override
//  public Map<String, Object> getCertDataVia(AgencyKeyEntity entity , ViaResponsePacketEntity agencyRes,   AgencyResultEntity aResult, AgencyErrEntity agencyErr, List<String> subIds) throws ServiceException{
    public Map<String, String> getCertDataVia(AgencyKeyEntity entity ,List<String> subIds) throws ServiceException{
    //Map<String, Object> dbResultEntityListMap = new HashMap<String, Object>();
    Map<String, String> resultData = new HashMap<String, String>();
    
    try {
      if (subIds == null || subIds.size() == 0) {
        throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNDEFINED);
      }

      for (String subId : subIds) {        
        resultData.put(subId, getViaDataOfSubID(entity, subId));        
      }

    } catch (Exception e) {
      // TODO Auto-generated catch block
      throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNABLE_TO_GET_DATA, e);
    }

    return resultData;
    
  }
  

  @Override
  public Map<String, Object> getCertDataWithRequireKey(UserEntity entity, List<String> subIds) throws ServiceException {
    // TODO Auto-generated method stub
    return null;
  }

  
}