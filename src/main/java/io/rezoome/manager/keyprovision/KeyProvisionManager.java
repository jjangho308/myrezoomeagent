package io.rezoome.manager.keyprovision;

import java.security.KeyPair;
import java.security.KeyStore.Entry;

import javax.crypto.SecretKey;

import io.rezoome.manager.Manager;


public interface KeyProvisionManager extends Manager{
  
  public void initKeyStore() throws Exception;
  public Entry createCert(KeyPair keyPair, boolean isSaveKeyStore);
  
  public String getPubKeyStr(String certAlias);
  public String getPrivKeyStr(String certAlias);
  
}
