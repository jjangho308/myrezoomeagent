package io.rezoome.external.mk.iorequest;


import io.rezoome.external.AbastractExternalIORequest;
import io.rezoome.manager.job.iorequest.IORequestJobEntity;

public class MkIORequest extends AbastractExternalIORequest {

  @Override
  public void getData(IORequestJobEntity entity) {
    super.getViaData(entity);
    
  }


}
