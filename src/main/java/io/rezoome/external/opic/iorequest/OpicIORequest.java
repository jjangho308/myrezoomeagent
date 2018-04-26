package io.rezoome.external.opic.iorequest;

import io.rezoome.external.AbastractExternalIORequest;
import io.rezoome.manager.job.iorequest.IORequestJobEntity;

public class OpicIORequest extends AbastractExternalIORequest{

  @Override
  public void getData(IORequestJobEntity entity) {
    
    super.getDirectDbData(entity);
  
  }

}
