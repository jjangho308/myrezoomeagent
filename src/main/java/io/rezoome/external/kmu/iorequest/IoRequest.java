package io.rezoome.external.kmu.iorequest;

import io.rezoome.external.common.AbastractExternalIORequest;
import io.rezoome.manager.job.iorequest.IORequestJobEntity;

public class IoRequest extends AbastractExternalIORequest {

  @Override
  public void iorequest(IORequestJobEntity entity) {
    // TODO Auto-generated method stub
    super.getDirectDbData(entity);    
  }

}
