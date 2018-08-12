package io.rezoome.external.opic.iorequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rezoome.external.common.AbastractExternalIORequest;
import io.rezoome.external.common.entity.AgencyKeyEntity;
import io.rezoome.external.common.entity.AgencyResultEntity;
import io.rezoome.external.mk.entity.MkResponseEntity;
import io.rezoome.external.mk.entity.MkResponseErrArgsEntity;
import io.rezoome.external.mk.entity.MkResponseResultArgsEntity;
import io.rezoome.external.mk.entity.MkUserEntity;
import io.rezoome.external.opic.entity.OpicResponseEntity;
import io.rezoome.lib.json.JSON;
import io.rezoome.manager.job.iorequest.IORequestJobEntity;
import io.rezoome.manager.network.entity.request.RequestPacket;
import io.rezoome.manager.network.entity.request.RequestPacketEntity;
import io.rezoome.manager.network.entity.response.ResponsePacket;
import io.rezoome.manager.property.PropertyEnum;
import io.rezoome.manager.provider.ManagerProvider;

public class OpicIORequest extends AbastractExternalIORequest {

  @Override
  public void iorequest(IORequestJobEntity entity) {


    // Via 데이터
    AgencyKeyEntity user = new MkUserEntity();
    user.key = entity.getCi();

    OpicResponseEntity aResponse = new OpicResponseEntity();

    Map<String, String> resultStr = getViaData(entity, user);
    Map<String, Object> resultsMap = new HashMap<String,Object>();


    for (String subId : resultStr.keySet()) {
      /*if(JSON.fromJson(resultStr.get(subId), aResponse).getResult().size() > 1){
        for(int i=0; i< JSON.fromJson(resultStr.get(subId), aResponse).getResult().size(); i++){          
          resultsMap.put(subId, JSON.fromJson(resultStr.get(subId), aResponse).getResult().get(i));
        }
      }*/
      
      resultsMap.put(subId, JSON.fromJson(resultStr.get(subId), aResponse).getResult());
    }
    RequestPacketEntity requestEntity = new RequestPacketEntity();
    super.convertRequestPacket(entity, resultsMap, requestEntity);

    RequestPacket packet = new RequestPacket(ManagerProvider.property().getProperty(PropertyEnum.PORTAL_URL, false) + "/" + entity.getSid(), JSON.toJson(requestEntity));

    ResponsePacket responseEntity = null;
    responseEntity = ManagerProvider.network().request(packet);
  }

}
