package io.rezoome.manager.job.iorequest;

import io.rezoome.exception.ServiceException;
import io.rezoome.manager.job.entity.AbstractJob;
import io.rezoome.manager.provider.ManagerProvider;

public class IORequestJobAction extends AbstractJob<IORequestJobEntity> {


  public IORequestJobAction() {
    super();
  }

  @Override
  protected void processInternal(IORequestJobEntity entity) throws ServiceException {
    try {
      ManagerProvider.job().getAgentIORequest().iorequest(entity);
    } catch (Exception e) {
      throw new ServiceException(e.getMessage(), e);
    }
  }
 
}
