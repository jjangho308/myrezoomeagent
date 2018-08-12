package io.rezoome.lib.crypto;

import java.security.KeyPair;

import javax.crypto.SecretKey;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.manager.AbstractManager;

public class CryptoManagerV1 extends AbstractManager implements CryptoManager {

	public static CryptoManager getInstance() {
		return Singleton.instance;
	}

	/**
	 * Hide constructor for singleton pattern. <br />
	 * 
	 */
	private CryptoManagerV1() {
	}

	private static class Singleton {
		private static CryptoManagerV1 instance = new CryptoManagerV1();
	}

	@Override
	public KeyPair generateAssymmetricKey() {
		KeyPair result = null;

		return result;
	}

	@Override
	public SecretKey generateSymmetricKey() {
		SecretKey result = null;

		return result;
	}

	@Override
	public void initialize(InitialEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initializeOnThread(InitialEvent event) {
		// TODO Auto-generated method stub

	}

}
