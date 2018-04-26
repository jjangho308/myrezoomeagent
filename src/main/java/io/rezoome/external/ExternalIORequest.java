package io.rezoome.external;


import io.rezoome.exception.ServiceException;
import io.rezoome.manager.job.iorequest.IORequestJobEntity;

public interface ExternalIORequest {
  public void getData(IORequestJobEntity entity) throws ServiceException;
  
}
