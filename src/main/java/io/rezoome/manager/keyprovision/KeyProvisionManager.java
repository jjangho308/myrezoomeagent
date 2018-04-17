package io.rezoome.manager.keyprovision;

import javax.crypto.SecretKey;

import io.rezoome.manager.Manager;


public interface KeyProvisionManager extends Manager{
  
  public void initKeyStore() throws Exception;
  
  
}
