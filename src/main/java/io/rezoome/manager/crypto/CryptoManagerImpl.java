package io.rezoome.manager.crypto;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import io.rezoome.constants.Constants;
import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.core.annotation.ManagerType;
import io.rezoome.manager.AbstractManager;

@ManagerType(Constants.MANAGER_TYPE_CRYPTO)
public class CryptoManagerImpl extends AbstractManager implements CryptoManager {

  private static class Singleton {
    private static final CryptoManager instance = new CryptoManagerImpl();
  }

  public static CryptoManager getInstance() {
    return Singleton.instance;
  }


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
  public boolean isPrepared() {
    // TODO Auto-generated method stub
    return false;
  }


  @Override
  public String hash(String entity) {
    // TODO Auto-generated method stub
    String hash = "";
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      md.update(entity.toString().getBytes());
      byte byteData[] = md.digest();
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < byteData.length; i++) {
        sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
      }
      hash = sb.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return hash;
  }

  @Override
  public Map<String, String> generateRSA() {
    // TODO Auto-generated method stub
//    Map<String, String> returnMap = new HashMap<String, String>();
//
//    try {
//      // generate key pair
//      KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//      keyPairGenerator.initialize(2048);
//
//      KeyPair keyPair = keyPairGenerator.genKeyPair();
//      Key publicKey = keyPair.getPublic();
//      Key privateKey = keyPair.getPrivate();
//
//      // base64 encode keys
//      String encodedPublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
//      String encodedPrivateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());
//
//      returnMap.put("PUBLIC_KEY", encodedPublicKey);
//      returnMap.put("PRIVATE_KEY", encodedPrivateKey);
//
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    return returnMap;
    return null;
  }

  @Override
  public String generateAES() {
    // TODO Auto-generated method stub
    SecretKey secretKey = null;
    SecureRandom rand = new SecureRandom();
    KeyGenerator generator = null;

    try {
      generator = KeyGenerator.getInstance("AES");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }

    generator.init(128, rand);
    secretKey = generator.generateKey();

    return Base64.getEncoder().encodeToString(secretKey.getEncoded());
  }

  @Override
  public String generateIV() {
    // TODO Auto-generated method stub
    SecureRandom rand = new SecureRandom();
    byte[] iv = new byte[16];
    rand.nextBytes(iv);

    IvParameterSpec ivParams = new IvParameterSpec(iv);

    String randomIVbase64 = Base64.getEncoder().encodeToString(ivParams.getIV());
    return randomIVbase64;
  }

  @Override
  public String encryptRSA(String data, String publicKey) {
    // TODO Auto-generated method stub
    String encryptedString = "";

    try {
      KeyFactory fac = KeyFactory.getInstance("RSA");
      Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
      byte[] pubArr = Base64.getDecoder().decode(publicKey);
      X509EncodedKeySpec x509Spec = new X509EncodedKeySpec(pubArr);
      PublicKey pubkey = fac.generatePublic(x509Spec);

      cipher.init(Cipher.ENCRYPT_MODE, pubkey);
      byte[] arrCipherData = cipher.doFinal(data.getBytes());
      encryptedString = Base64.getEncoder().encodeToString(arrCipherData);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return encryptedString;
  }

  @Override
  public String decryptRSA(String encData, String privateKey) {
    // TODO Auto-generated method stub
    String decryptedString = "";

    try {
      byte[] decodedEncryptionData = Base64.getDecoder().decode(encData);

      KeyFactory fac = KeyFactory.getInstance("RSA");
      Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
      byte[] priArr = Base64.getDecoder().decode(privateKey);
      PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(priArr);
      PrivateKey priKey = fac.generatePrivate(keySpec);

      cipher.init(Cipher.DECRYPT_MODE, priKey);
      byte[] arrCipherData = cipher.doFinal(decodedEncryptionData);
      decryptedString = new String(arrCipherData);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return decryptedString;
  }

  @Override
  public String encryptAES(String data, String aesKey, String iv) {
    // TODO Auto-generated method stub
    byte[] keyData = Base64.getDecoder().decode(aesKey);
    byte[] ivData = Base64.getDecoder().decode(iv);

    SecretKey secureKey = new SecretKeySpec(keyData, "AES");
    String encData = null;

    try {
      Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
      c.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(ivData));
      byte[] encrypted = c.doFinal(data.getBytes("UTF-8"));
      encData = Base64.getEncoder().encodeToString(encrypted);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return encData;
  }

  @Override
  public String decryptAES(String encData, String aesKey, String iv) {
    // TODO Auto-generated method stub
    byte[] keyData = Base64.getDecoder().decode(aesKey);
    byte[] ivData = Base64.getDecoder().decode(iv);

    SecretKey secureKey = new SecretKeySpec(keyData, "AES");
    String decyptString = null;

    try {
      Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
      c.init(Cipher.DECRYPT_MODE, secureKey, new IvParameterSpec(ivData));

      byte[] byteStr = Base64.getDecoder().decode(encData);
      decyptString = new String(c.doFinal(byteStr), "UTF-8");
    } catch (Exception e) {
      e.printStackTrace();
    }

    return decyptString;
  }

}
