package io.rezoome.lib.crypto;

import java.security.KeyPair;

import javax.crypto.SecretKey;

import io.rezoome.manager.Manager;

/**
 * CryptoManager interface. <br />
 * 
 * @since 1.0.0
 * @author Saver
 *
 */
public interface CryptoManager extends Manager {
	public KeyPair generateAssymmetricKey();
	
	public SecretKey generateSymmetricKey();
}
