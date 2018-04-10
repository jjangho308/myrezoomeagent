package io.rezoome.manager.job.iorequest;

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
import io.rezoome.lib.json.JSON;
import io.rezoome.manager.database.convert.DBConverter;
import io.rezoome.manager.database.entity.DBEntity;
import io.rezoome.manager.database.entity.DBRsltEntity;
import io.rezoome.manager.job.JobRsltEntity;
import io.rezoome.manager.job.entity.AbstractJob;
import io.rezoome.manager.mapper.DaoMapper;
import io.rezoome.manager.mapper.Mapper;
import io.rezoome.manager.network.entity.RequestPacket;
import io.rezoome.manager.network.entity.request.RequestArgsEntity;
import io.rezoome.manager.network.entity.request.RequestPacketEntity;
import io.rezoome.manager.network.entity.request.RequestSearchArgsEntity;
import io.rezoome.manager.network.entity.request.RequestSearchRecordsEntity;
import io.rezoome.manager.network.entity.response.ResponsePacketEntity;
import io.rezoome.manager.property.PropertyEnum;
import io.rezoome.manager.provider.ManagerProvider;
import io.rezoome.manager.pushcommand.entity.search.HashRecordEntity;

public class IORequestJobAction extends AbstractJob<IORequestJobEntity> {

  private final Logger LOG = LoggerFactory.getLogger(Constants.AGENT_LOG);
  private DaoMapper daoMapper;

  public enum STATUS {
    USER_EXIST, USER_NOT_EXIST, REQUIRE_KEY
  }

  private STATUS status;

  public IORequestJobAction() {
    super();
  }

  @Override
  protected JobRsltEntity processInternal(IORequestJobEntity entity) throws ServiceException {
    try {

      ResponsePacketEntity responseEntity = null;

      // database
      // List<List<DBRsltEntity>> dbResultEntityList = getDBData(entity);
      Map<String, Object> dbResultEntityListMap = getDBData(entity);
      LOG.debug("dbResultEntityList {}", dbResultEntityListMap);

      // convert
      RequestPacketEntity requestEntity = new RequestPacketEntity();
      convertRequestPacket(entity, dbResultEntityListMap, requestEntity);

      // http
      RequestPacket packet = new RequestPacket(entity.getSid(), JSON.toJson(requestEntity));
      responseEntity = ManagerProvider.network().request(packet);

      if (responseEntity == null) {
        throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNABLE_TO_GET_CORRECT_RESPONSE_CODE);
      }
    } catch (Exception e) {
      throw new ServiceException(e.getMessage(), e);
    }
    return null;
  }

  private void convertRequestPacket(IORequestJobEntity entity, Map<String, Object> dbResultEntityListMap, RequestPacketEntity requestEntity) throws ServiceException {
    // TODO Auto-generated method stub
    try {
      RequestSearchArgsEntity searchRecordEntity = new RequestSearchArgsEntity();

      searchRecordEntity.setOrgCode("orgcode");
      searchRecordEntity.setKey("");
      searchRecordEntity.setIv("");

      if (status.equals(STATUS.USER_NOT_EXIST)) {
        requestEntity.setCode(Constants.RESULT_CODE_USER_NOT_EXIST);
      } else if (status.equals(STATUS.REQUIRE_KEY)) {
        requestEntity.setCode(Constants.RESULT_CODE_NEED_TO_REQUIRE_KEY);
      } else if (dbResultEntityListMap.size() == 0) {
        requestEntity.setCode(Constants.RESULT_CODE_DATA_IS_EMPTY);
      } else {
        String aesKey = ManagerProvider.crypto().generateAES();
        String iv = ManagerProvider.crypto().generateIV();
        String encKey = ManagerProvider.crypto().encryptRSA(aesKey, entity.getPkey());
        String encIv = ManagerProvider.crypto().encryptRSA(iv, entity.getPkey());

        Mapper mapper = ManagerProvider.mapper().getMapper();
        List<RequestArgsEntity> records = new ArrayList<RequestArgsEntity>();
        RequestSearchRecordsEntity record = null;

        // TODO gson -> JSON
        Gson gson = new Gson();
        for (String subId : dbResultEntityListMap.keySet()) {
          @SuppressWarnings("unchecked")
          String dbEntityString = gson.toJson(mapper.convert((List<DBRsltEntity>) dbResultEntityListMap.get(subId)));
          record = new RequestSearchRecordsEntity();
          String encData = dbEntityString;
          String hashData = ManagerProvider.crypto().hash(dbEntityString);
          record.setData(encData);
          record.setHash(hashData);
          record.setSubid(subId);
          record.setStored(isStoredHashData(entity, hashData));
          records.add(record);
        }

        searchRecordEntity.setKey(encKey);
        searchRecordEntity.setIv(encIv);
        searchRecordEntity.setRecords(records);
        requestEntity.setCode(Constants.RESULT_CODE_SUCCESS);
      }
      requestEntity.setArgs(searchRecordEntity);
      requestEntity.setCmd(entity.getCmd());
      requestEntity.setMid(entity.getMid());
    } catch (Exception e) {
      throw new ServiceException(ErrorCodeConstants.ERROR_CODE_FAIL_TO_CONVERT_DATA, e);
    }
  }

  private Map<String, Object> getDBData(IORequestJobEntity entity) throws ServiceException {

    try {
      String daoMapperClass = ManagerProvider.property().getProperty(PropertyEnum.DAO_MAPPER_CLASS_NAME);
      ClassLoader loader = ClassLoader.getSystemClassLoader();
      Class<?> mapperCls = loader.loadClass(daoMapperClass);
      daoMapper = (DaoMapper) mapperCls.newInstance();
    } catch (Exception e) {
      throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNDEFINED, e);
    }

    DBConverter converter = ManagerProvider.database().getConvertManager().getConverter();

    Map<String, Object> dbResultEntityListMap = new HashMap<String, Object>();
    List<String> requires = entity.getRequire();
    List<String> subIds = entity.getSubIds();
    List<HashRecordEntity> records = entity.getRecords();

    DBEntity dbEntity = converter.convert(entity);
    Map<String, Object> userResultMap = new HashMap<String, Object>();

    // 1. 사용자 확인
    // 2. 기관정보 데이터 확인
    try {
      // CI, 이름, 생년월일, 성별 등으로 다시 확인
      userResultMap = daoMapper.getUserData(dbEntity);
      status = STATUS.valueOf(userResultMap.get(Constants.PARAM_STATUS).toString());
      dbEntity = (DBEntity) userResultMap.get(Constants.PARAM_ENTITY);

      if (status.equals(STATUS.USER_EXIST)) {
        if (subIds != null && records == null) {
          // 시나리오1. first call
          LOG.debug("시나리오1. first call");
          dbResultEntityListMap = daoMapper.getCertData(dbEntity, subIds);
        } else if (subIds == null && records != null) {
          // 시나리오2. second call
          LOG.debug("시나리오2. second call");
          subIds = new ArrayList<String>();
          for (HashRecordEntity record : records) {
            subIds.add(record.getSubID());
          }
          dbResultEntityListMap = daoMapper.getCertData(dbEntity, subIds);
        } else if (subIds != null && records != null) {
          // 시나리오3. update call
          LOG.debug("시나리오3. update call");
        }
      } else {
        dbResultEntityListMap = null;
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNABLE_TO_GET_DB_DATA, e);
    }

    return dbResultEntityListMap;
  }

  private String isStoredHashData(IORequestJobEntity entity, String hashData) throws ServiceException {
    // TODO entity.getHashList 로 유도
    List<HashRecordEntity> hashList = new ArrayList<HashRecordEntity>();
    for (HashRecordEntity record : hashList) {
      if (record.getHashed() != null && record.getHashed().equals(hashData)) {
        return "Y";
      }
    }
    return "N";
  }
}
