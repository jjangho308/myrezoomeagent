package io.rezoome.external.inha.iorequest;

import io.rezoome.external.common.AbastractExternalIORequest;
import io.rezoome.external.mk.entity.MkResponseResultArgsEntity;
import io.rezoome.manager.job.iorequest.IORequestJobEntity;

public class InhaIORequest extends AbastractExternalIORequest{

  @Override
  public void iorequest(IORequestJobEntity entity) {
    // TODO Auto-generated method stub
    
    
    
    getDirectDbData(entity);
  }

}
