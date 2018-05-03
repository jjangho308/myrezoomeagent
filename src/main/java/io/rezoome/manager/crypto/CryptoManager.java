package io.rezoome.manager.crypto;

import java.security.KeyPair;
import java.util.Map;

import io.rezoome.manager.Manager;

public interface CryptoManager extends Manager {
  public String hash(String data);

  public String generateAES();

  public String generateIV();

  public KeyPair genRSAKeyPair();

  public String encryptRSA(String data, String publicKey);

  public String decryptRSA(String encData, String privateKey);

  public String encryptAES(String data, String aesKey, String iv);

  public String decryptAES(String encData, String aesKey, String iv);
  
  public String encodeBase64(byte [] bArr);
}
