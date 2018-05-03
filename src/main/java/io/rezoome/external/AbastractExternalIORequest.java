package io.rezoome.external;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import io.rezoome.constants.Constants;
import io.rezoome.constants.ErrorCodeConstants;
import io.rezoome.exception.ServiceException;
import io.rezoome.external.entity.AgencyKeyEntity;
import io.rezoome.external.entity.AgencyResultEntity;
import io.rezoome.external.entity.AgencyUserEntity;
import io.rezoome.external.mk.entity.MkRequestPacketEntity;
import io.rezoome.external.mk.entity.MkResponsePacketEntity;
import io.rezoome.lib.json.JSON;
import io.rezoome.lib.json.Jsonable;
import io.rezoome.manager.database.convert.DBConverter;
import io.rezoome.manager.database.entity.UserEntity;
import io.rezoome.manager.job.iorequest.IORequestJobEntity;
import io.rezoome.manager.job.iorequest.entity.RequestSearchArgsEntity;
import io.rezoome.manager.job.iorequest.entity.RequestSearchRecordsEntity;
import io.rezoome.manager.mapper.DaoMapper;
import io.rezoome.manager.mapper.Mapper;
import io.rezoome.manager.network.entity.request.RequestArgsEntity;
import io.rezoome.manager.network.entity.request.RequestPacket;
import io.rezoome.manager.network.entity.request.RequestPacketEntity;
import io.rezoome.manager.network.entity.response.ResponsePacketEntity;
import io.rezoome.manager.property.PropertyEnum;
import io.rezoome.manager.provider.ManagerProvider;
import io.rezoome.manager.pushcommand.entity.search.HashRecordEntity;
import io.rezoome.manager.vianetwork.entity.request.ViaRequestPacketEntity;
import io.rezoome.manager.vianetwork.entity.response.ViaResponsePacketEntity;

public abstract class AbastractExternalIORequest implements ExternalIORequest{

  protected final Logger LOG = LoggerFactory.getLogger(Constants.AGENT_LOG);

  private enum STATUS {
    USER_EXIST, USER_NOT_EXIST, REQUIRE_KEY
  }
  private STATUS status;
  
  private DaoMapper daoMapper;
  
  /**
   * @author YOO
   * @param entity
   * @throws ServiceException
   */
    
  protected void getViaData(IORequestJobEntity entity, AgencyKeyEntity user,  AgencyResultEntity resEntity){
    try{ 
      List<String> subIds = checkCommand(entity);
      ManagerProvider.mapper().getDaoMapper().getCertData(user, resEntity, subIds);
      Map<String, Object> dbResultEntityListMap = new HashMap<String, Object>();
      
      // convert
      RequestPacketEntity requestEntity = new RequestPacketEntity();
      convertRequestPacket(entity, dbResultEntityListMap, requestEntity);
      
      // http
      RequestPacket packet = new RequestPacket(ManagerProvider.property().getProperty(PropertyEnum.PORTAL_URL , false) + entity.getSid(), JSON.toJson(requestEntity));
      ResponsePacketEntity responseEntity = null;
      responseEntity = ManagerProvider.network().request(packet);
  
      if (responseEntity == null) {
        throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNABLE_TO_GET_CORRECT_RESPONSE_CODE);
      }
    
  } catch (Exception e) {
    // TODO Auto-generated catch block
    throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNABLE_TO_GET_DB_DATA, e);
  }
  }
  
  
  protected void getDirectDbData(IORequestJobEntity entity,  AgencyResultEntity resEntity) throws ServiceException {
    
    daoMapper = (DaoMapper) ManagerProvider.mapper().getDaoMapper();
    DBConverter converter = ManagerProvider.database().getConvertManager().getConverter();

    Map<String, Object> dbResultEntityListMap = new HashMap<String, Object>();
    List<String> requires = entity.getRequire();
    List<HashRecordEntity> records = entity.getRecords();
    List<String> subIds;


    UserEntity dbEntity = converter.convert(entity);
    Map<String, Object> userResultMap = new HashMap<String, Object>();
    AgencyKeyEntity agencyKeyEntity;
    // 1. 사용자 확인
    // 2. 기관정보 데이터 확인
    try {
      // CI, 이름, 생년월일, 성별 등으로 다시 확인
      userResultMap = daoMapper.getUserData(dbEntity);
      status = STATUS.valueOf(userResultMap.get(Constants.PARAM_STATUS).toString());
      agencyKeyEntity = (AgencyKeyEntity) userResultMap.get(Constants.PARAM_ENTITY);

      if (requires != null) {
        // dbEntity = JSON.fromJson("{req_key1:" + requires.get(0) + ", req_key2:" + requires.get(1)
        // != null ? requires.get(1) : "" + "}", DBEntity.class);
        // dbResultEntityListMap = daoMapper.getCertDataWithRequireKey(dbEntity, subIds);
      }

      subIds = checkCommand(entity);
      if (status.equals(STATUS.USER_EXIST)) {        
//        if (subIds != null) {
//          dbResultEntityListMap = daoMapper.getCertData(agencyKeyEntity,resEntity, subIds);
//        } else if (subIds == null && records != null) {
//          subIds = new ArrayList<String>();
//          for (HashRecordEntity record : records) {
//            subIds.add(record.getSubID());
//          }
//          dbResultEntityListMap = daoMapper.getCertData(agencyKeyEntity,resEntity, subIds);
//        }
        dbResultEntityListMap = daoMapper.getCertData(agencyKeyEntity,resEntity, subIds);
      } else {
        dbResultEntityListMap = null;
      }
      
      // convert
      RequestPacketEntity requestEntity = new RequestPacketEntity();
      convertRequestPacket(entity, dbResultEntityListMap, requestEntity);
      
      // http
      RequestPacket packet = new RequestPacket(ManagerProvider.property().getProperty(PropertyEnum.PORTAL_URL , false) + entity.getSid(), JSON.toJson(requestEntity));
      ResponsePacketEntity responseEntity = null;
      responseEntity = ManagerProvider.network().request(packet);

      if (responseEntity == null) {
        throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNABLE_TO_GET_CORRECT_RESPONSE_CODE);
      }
      
    } catch (Exception e) {
      // TODO Auto-generated catch block
      throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNABLE_TO_GET_DB_DATA, e);
    }

  }  
  
  private List<String> checkCommand(IORequestJobEntity entity){
    List<String> subIds = entity.getSubIds();
    List<HashRecordEntity> records = entity.getRecords();

    
    if (subIds != null) {
      return subIds;
    } else if (subIds == null && records != null) {
      subIds = new ArrayList<String>();
      for (HashRecordEntity record : records) {
        subIds.add(record.getSubID());
      }
      return subIds;
    }
    
    return subIds;
    
  }
  
  
  @SuppressWarnings("unchecked")
  private void convertRequestPacket(IORequestJobEntity entity, Map<String, Object> dbResultEntityListMap, RequestPacketEntity requestEntity) throws ServiceException {
    // TODO Auto-generated method stub
    try {
      RequestSearchArgsEntity searchRecordEntity = new RequestSearchArgsEntity();

      searchRecordEntity.setOrgCode(ManagerProvider.property().getProperty(PropertyEnum.ORG_CODE, false));
      searchRecordEntity.setKey("");
      searchRecordEntity.setIv("");

      if (status.equals(STATUS.USER_NOT_EXIST)) {
        searchRecordEntity.setCode(Constants.RESULT_CODE_USER_NOT_EXIST);
      } else if (status.equals(STATUS.REQUIRE_KEY)) {
        searchRecordEntity.setCode(Constants.RESULT_CODE_NEED_TO_REQUIRE_KEY);
      } else if (dbResultEntityListMap.size() == 0) {
        searchRecordEntity.setCode(Constants.RESULT_CODE_DATA_IS_EMPTY);
      } else {
        String aesKey = ManagerProvider.crypto().generateAES();
        String iv = ManagerProvider.crypto().generateIV();
        // String encKey = ManagerProvider.crypto().encryptRSA(aesKey, entity.getPkey());
        // String encIv = ManagerProvider.crypto().encryptRSA(iv, entity.getPkey());
        String encKey = "ENCRYPTED_AESKEY";
        String encIv = "ENCRYPTED_IV";

        Mapper mapper = ManagerProvider.mapper().getMapper();
        List<RequestArgsEntity> records = new ArrayList<RequestArgsEntity>();
        RequestSearchRecordsEntity record = null;

        // TODO gson -> JSON
        Gson gson = new Gson();
        for (String subId : dbResultEntityListMap.keySet()) {
          String dbEntityString = "";
          String dbResultClassType = dbResultEntityListMap.get(subId).getClass().toString();
          if (dbResultClassType.contains("List")) {
            List<Object> dbResultEntityList = (List<Object>) dbResultEntityListMap.get(subId);
            for (Object resultEntity : dbResultEntityList) {
              dbEntityString = gson.toJson(mapper.convert(resultEntity));
              record = new RequestSearchRecordsEntity();
              String encData = dbEntityString;
              String hashData = ManagerProvider.crypto().hash(dbEntityString);
              record.setData(encData);
              record.setHash(hashData);
              record.setSubid(subId);
              HashRecordEntity hashRecordEntity = getMatchingHashData(entity, hashData);
              if (hashRecordEntity != null) {
                record.setTxid(hashRecordEntity.getTxid());
                record.setStored("Y");
              } else {
                record.setTxid("");
                record.setStored("N");
              }
              records.add(record);
            }
          } else if (dbResultClassType.contains("Map")) {
            Map<String, Object> dbResultEntityMap = new HashMap<String, Object>();
            dbResultEntityMap = (Map<String, Object>) dbResultEntityListMap.get(subId);
            List<AgencyUserEntity> dbResultEntityList = (List<AgencyUserEntity>) dbResultEntityMap.get("all");
            dbEntityString = gson.toJson(mapper.convert(dbResultEntityList));
            record = new RequestSearchRecordsEntity();
            String encData = dbEntityString;
            String hashData = ManagerProvider.crypto().hash(dbEntityString);
            record.setData(encData);
            record.setHash(hashData);
            record.setSubid(subId);
            HashRecordEntity hashRecordEntity = getMatchingHashData(entity, hashData);
            if (hashRecordEntity != null) {
              record.setTxid(hashRecordEntity.getTxid());
              record.setStored("Y");
            } else {
              record.setTxid("");
              record.setStored("N");
            }
            records.add(record);
          }



          // if (((List<DBRsltEntity>) dbResultEntityListMap.get(subId)).size() > 1) {
          // List<DBRsltEntity> dbResultEntityList = (List<DBRsltEntity>)
          // dbResultEntityListMap.get(subId);
          // dbEntityString = gson.toJson(mapper.convert(dbResultEntityList));
          // } else {
          // DBRsltEntity dbResultEntity = ((List<DBRsltEntity>)
          // dbResultEntityListMap.get(subId)).get(0);
          // dbEntityString = gson.toJson(mapper.convert(dbResultEntity));
          // }
          //
          // record = new RequestSearchRecordsEntity();
          // String encData = dbEntityString;
          // String hashData = ManagerProvider.crypto().hash(dbEntityString);
          // record.setData(encData);
          // record.setHash(hashData);
          // record.setSubid(subId);
          // HashRecordEntity hashRecordEntity = getMatchingHashData(entity, hashData);
          // if (hashRecordEntity != null) {
          // record.setTxid(hashRecordEntity.getTxid());
          // record.setStored("Y");
          // } else {
          // record.setTxid("");
          // record.setStored("N");
          // }
          // records.add(record);
        }

        searchRecordEntity.setKey(encKey);
        searchRecordEntity.setIv(encIv);
        searchRecordEntity.setRecords(records);
        searchRecordEntity.setCode(Constants.RESULT_CODE_SUCCESS);
      }
      requestEntity.setSid(entity.getSid());
      requestEntity.setArgs(searchRecordEntity);
      requestEntity.setCmd(entity.getCmd());
      requestEntity.setMid(entity.getMid());
    } catch (Exception e) {
      throw new ServiceException(ErrorCodeConstants.ERROR_CODE_FAIL_TO_CONVERT_DATA, e);
    }
  }
  
  private HashRecordEntity getMatchingHashData(IORequestJobEntity entity, String hashData) throws ServiceException {
    // TODO entity.getHashList 로 유도

    if (entity.getRecords() == null) {
      return null;
    }

    List<HashRecordEntity> hashList = entity.getRecords();
    for (HashRecordEntity record : hashList) {
      if (record.getHashed() != null && record.getHashed().equals(hashData)) {
        return record;
      }
    }
    return null;
  }
}
