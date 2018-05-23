package io.rezoome.external.opic.iorequest;

import io.rezoome.external.common.AbastractExternalIORequest;
import io.rezoome.external.common.entity.AgencyResultEntity;
import io.rezoome.external.opic.entity.OpicResponseResultArgsEntity;
import io.rezoome.manager.job.iorequest.IORequestJobEntity;

public class OpicIORequest extends AbastractExternalIORequest{

  @Override
  public void iorequest(IORequestJobEntity entity) {
    
   
    getDirectDbData(entity);
  
  }

}
