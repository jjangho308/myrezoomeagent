package io.rezoome.external.mk.iorequest;


import io.rezoome.external.AbastractExternalIORequest;
import io.rezoome.external.mk.entity.MkResponsePacketEntity;
import io.rezoome.manager.job.iorequest.IORequestJobEntity;
import io.rezoome.manager.vianetwork.entity.response.ViaResponsePacketEntity;

public class MkIORequest extends AbastractExternalIORequest {

  @Override
  public void getData(IORequestJobEntity entity) {
    
    for(int i=0; i<100; i++){
      System.out.println("MKMKMKMKMKMKMKMKMKMKMKMKMKMKMKMKMKMKMKMKMKMKMKMKMKMKMKMKMKMK");
      ViaResponsePacketEntity result = new MkResponsePacketEntity();
      
      super.getViaData(entity, result);
    }
      

    
  }


}
