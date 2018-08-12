package io.rezoome.manager;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.exception.ServiceException;

/**
 * Root interface for each manager. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
public interface Manager {

  void initialize(InitialEvent event) throws ServiceException;

  void initializeOnThread(InitialEvent event);

  boolean isPrepared();
}
