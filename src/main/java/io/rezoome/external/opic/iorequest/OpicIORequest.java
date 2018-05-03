package io.rezoome.external.opic.iorequest;

import io.rezoome.external.AbastractExternalIORequest;
import io.rezoome.external.entity.AgencyResultEntity;
import io.rezoome.external.opic.entity.OpicResultEntity;
import io.rezoome.manager.job.iorequest.IORequestJobEntity;

public class OpicIORequest extends AbastractExternalIORequest{

  @Override
  public void iorequest(IORequestJobEntity entity) {
    
    AgencyResultEntity aResult = new OpicResultEntity();
    getDirectDbData(entity, aResult);
  
  }

}
