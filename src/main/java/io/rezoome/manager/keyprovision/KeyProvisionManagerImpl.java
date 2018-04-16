package io.rezoome.manager.keyprovision;

import java.security.KeyStore.PrivateKeyEntry;
import java.security.PublicKey;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.manager.AbstractManager;

public class KeyProvisionManagerImpl extends AbstractManager implements KeyProvisionManager {

	@Override
	public void initialize(InitialEvent event) {
		// TODO Auto-generated method stub

		setPrepared();
	}

	@Override
	public void initializeOnThread(InitialEvent event) {
		// TODO Auto-generated method stub

	}

  @Override
  public PublicKey getPublicKey() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PrivateKeyEntry getPrivateKey() {
    // TODO Auto-generated method stub
    return null;
  }

}
