package io.rezoome.external.mk.iorequest;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rezoome.constants.ErrorCodeConstants;
import io.rezoome.exception.ServiceException;
import io.rezoome.external.common.AbastractExternalIORequest;
import io.rezoome.external.common.entity.AgencyKeyEntity;
import io.rezoome.external.mk.entity.MkResponseEntity;
import io.rezoome.external.mk.entity.MkResponseErrArgsEntity;
import io.rezoome.external.mk.entity.MkResponseResultArgsEntity;
import io.rezoome.external.mk.entity.MkUserEntity;
import io.rezoome.lib.json.JSON;
import io.rezoome.manager.job.iorequest.IORequestJobEntity;
import io.rezoome.manager.network.entity.request.RequestPacket;
import io.rezoome.manager.network.entity.request.RequestPacketEntity;
import io.rezoome.manager.network.entity.response.ResponsePacketEntity;
import io.rezoome.manager.property.PropertyEnum;
import io.rezoome.manager.provider.ManagerProvider;
import io.rezoome.manager.vianetwork.entity.response.ViaResponsePacketEntity;

public class MkIORequest extends AbastractExternalIORequest {

  @Override
  public void iorequest(IORequestJobEntity entity) {


    // Via 데이터
    AgencyKeyEntity user = new MkUserEntity();
    // user.key = entity.getCi();
    user.key = "test";

    MkResponseEntity aResponse = new MkResponseEntity();
    MkResponseResultArgsEntity aResult = new MkResponseResultArgsEntity();
    MkResponseErrArgsEntity aError = new MkResponseErrArgsEntity();


    Map<String, String> resultStr =  getViaData(entity, user);
    Map<String, Object> results = new HashMap<String, Object>();
    
    for( String subId : resultStr.keySet()){      
      results.put(subId, JSON.fromJson(resultStr.get(subId), aResponse).getResult());
    }
    
    RequestPacketEntity requestEntity = new RequestPacketEntity();
    super.convertRequestPacket(entity, results, requestEntity);
    
    RequestPacket packet = new RequestPacket(ManagerProvider.property().getProperty(PropertyEnum.PORTAL_URL, false) + entity.getSid(), JSON.toJson(requestEntity));

    ResponsePacketEntity responseEntity = null;
    responseEntity = ManagerProvider.network().request(packet);
    
    
    
    // DB 직접 접근
    // get via data or get db data
   //getDirectDbData(entity, aResult);



  }



}
