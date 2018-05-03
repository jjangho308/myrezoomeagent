package io.rezoome.external.mk.iorequest;


import io.rezoome.external.common.AbastractExternalIORequest;
import io.rezoome.external.common.entity.AgencyKeyEntity;
import io.rezoome.external.mk.entity.MkUserEntity;
import io.rezoome.external.mk.entity.MkUserResultEntity;
import io.rezoome.external.mk.entity.MkResponseErrArgsEntity;
import io.rezoome.external.mk.entity.MkResponsePacketEntity;
import io.rezoome.external.mk.entity.MkResponseResultArgsEntity;
import io.rezoome.lib.json.JSON;
import io.rezoome.manager.database.entity.UserEntity;
import io.rezoome.manager.job.iorequest.IORequestJobEntity;
import io.rezoome.manager.network.NetworkManagerImpl;
import io.rezoome.manager.provider.ManagerProvider;
import io.rezoome.manager.vianetwork.entity.request.ViaRequestPacketEntity;
import io.rezoome.manager.vianetwork.entity.response.ViaResponsePacketEntity;

public class MkIORequest extends AbastractExternalIORequest {

  @Override
  public void iorequest(IORequestJobEntity entity) {
      
    
    // Via 데이터
    AgencyKeyEntity user = new MkUserEntity();
    //user.key = entity.getCi();
    user.key = "test";
    
    MkResponsePacketEntity aResponse = new MkResponsePacketEntity();
    MkResponseResultArgsEntity aResult = new MkResponseResultArgsEntity();
    MkResponseErrArgsEntity aErrror = new MkResponseErrArgsEntity();
    
    getViaData(entity, user, aResult);    
    
    
    
    
    // DB 직접 접근
    // get via data or get db data
    // getDirectDbData(entity);
    
    
    
      
  }
  
  


}
