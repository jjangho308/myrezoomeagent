package io.rezoome.manager.keyprovision;

import java.security.KeyStore.PrivateKeyEntry;
import java.security.PublicKey;


public interface KeyProvisionManager {
  public PublicKey getPublicKey();
  public PrivateKeyEntry getPrivateKey();
}
