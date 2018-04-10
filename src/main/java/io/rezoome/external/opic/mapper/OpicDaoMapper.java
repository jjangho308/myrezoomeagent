package io.rezoome.external.opic.mapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rezoome.constants.Constants;
import io.rezoome.constants.ErrorCodeConstants;
import io.rezoome.exception.ServiceException;
import io.rezoome.external.opic.entity.OpicUserResultEntity;
import io.rezoome.lib.json.JSON;
import io.rezoome.manager.database.dao.DaoManagerImpl;
import io.rezoome.manager.database.entity.DBEntity;
import io.rezoome.manager.database.entity.DBRsltEntity;
import io.rezoome.manager.mapper.DaoMapper;
import io.rezoome.manager.provider.ManagerProvider;

public class OpicDaoMapper implements DaoMapper {

  private DaoManagerImpl daoMgr = null;

  private enum STATUS {
    USER_EXIST, USER_NOT_EXIST, REQUIRE_KEY
  }

  public OpicDaoMapper() {
    daoMgr = ManagerProvider.database().getDaoManager();
  }

  @Override
  public Map<String, Object> getUserData(DBEntity entity) throws ServiceException {
    // TODO Auto-generated method stub
    Map<String, Object> dbResult = new HashMap<String, Object>();
    try {
      List<DBRsltEntity> dbResultEntityList = daoMgr.getDao().getUserRecords(entity);
      if (dbResultEntityList.size() >= 1) {
        for (DBRsltEntity userEntity : dbResultEntityList) {
          OpicUserResultEntity user = (OpicUserResultEntity) userEntity;
          // CI 또는 전화번호가 일치 하는 경우에만 사용자로 확정
          if ((user.getCi() != null && entity.getCi().equals(user.getCi()))
              || (user.getPhone() != null && entity.getPhone().equals(user.getPhone()))) {

            // TODO 좀더 이쁜 코드로
            DBEntity dbEntity = JSON.fromJson("{phone:" + user.getPhone() + "}", DBEntity.class);

            dbResult.put(Constants.PARAM_STATUS, STATUS.USER_EXIST);
            dbResult.put(Constants.PARAM_ENTITY, dbEntity);
            return dbResult;
          }
        }
        dbResult.put(Constants.PARAM_STATUS, STATUS.REQUIRE_KEY);
      } else {
        dbResult.put(Constants.PARAM_STATUS, STATUS.USER_NOT_EXIST);
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNABLE_TO_GET_DB_DATA, e);
    }
    return dbResult;
  }


  @Override
  public List<List<DBRsltEntity>> getCertData(DBEntity entity, List<String> subIds) throws ServiceException {
    // TODO Auto-generated method stub
    List<List<DBRsltEntity>> finalDbResultEntityList = new ArrayList<List<DBRsltEntity>>();
    List<DBRsltEntity> dbResultEntityList = null;

    try {
      if (subIds == null || subIds.size() == 0) {
        throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNDEFINED);
      }

      for (String subId : subIds) {
        dbResultEntityList = convert(entity, subId);
        finalDbResultEntityList.add(dbResultEntityList);
      }

    } catch (Exception e) {
      // TODO Auto-generated catch block
      throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNABLE_TO_GET_DB_DATA, e);
    }

    return finalDbResultEntityList;
  }

  private List<DBRsltEntity> convert(DBEntity entity, String subId) throws ServiceException {
    List<DBRsltEntity> dbResultEntityList = null;
    try {
      switch (subId) {
        case "en":
          dbResultEntityList = daoMgr.getDao().getCertRecords(entity);
          break;
        case "ch":
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
