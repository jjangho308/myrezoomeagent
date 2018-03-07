package io.rezoome.manager.status;

import io.rezoome.manager.Manager;

public interface StatusManager extends Manager{
  public void keepAlive();
}
