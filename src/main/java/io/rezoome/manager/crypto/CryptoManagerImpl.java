package io.rezoome.manager.crypto;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

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
  public KeyPair generateRSA() {
    // TODO Auto-generated method stub
    KeyPair keyPair = null;
    try {
      // generate key pair
      KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
      keyPairGenerator.initialize(2048, new SecureRandom());
      keyPair = keyPairGenerator.genKeyPair();

    } catch (Exception e) {
      e.printStackTrace();
    }
    return keyPair;
  }

  @SuppressWarnings("static-access")
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

    generator.init(256, rand);
    secretKey = generator.generateKey();
    return new Base64(false).encodeBase64String(secretKey.getEncoded());
  }

  @Override
  public String generateIV() {
    // TODO Auto-generated method stub
    SecureRandom rand = new SecureRandom();
    byte[] iv = new byte[16];
    rand.nextBytes(iv);

    IvParameterSpec ivParams = new IvParameterSpec(iv);

    @SuppressWarnings("static-access")
    String randomIVbase64 = new Base64(false).encodeBase64String(ivParams.getIV());
    return randomIVbase64;
  }

  @SuppressWarnings("static-access")
  @Override
  public String encryptRSA(String data, String publicKey) {
    // TODO Auto-generated method stub
    String encryptedString = "";
    try {
      KeyFactory fac = KeyFactory.getInstance("RSA");
      Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
      byte[] pubArr = new Base64(false).decode(publicKey);
      X509EncodedKeySpec x509Spec = new X509EncodedKeySpec(pubArr);
      PublicKey pubkey = fac.generatePublic(x509Spec);
      cipher.init(Cipher.ENCRYPT_MODE, pubkey);
      byte[] arrCipherData = cipher.doFinal(data.getBytes());
      encryptedString = new Base64(false).encodeBase64String(arrCipherData);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return encryptedString;
  }

  @SuppressWarnings("static-access")
  @Override
  public String encryptRSA(String data, String N, String E) {
    // TODO Auto-generated method stub
    String encryptedString = "";
    try {
      String encodeE = E;
      String encodeN = N;
      BigInteger bigE = new BigInteger(new Base64(true).decode(encodeE));
      byte[] decodedN = new Base64(true).decode(encodeN.getBytes());
      ByteBuffer buffer = ByteBuffer.allocate(decodedN.length + 1);
      buffer.put((byte) 0x00).put(decodedN);
      BigInteger bigN = new BigInteger(buffer.array());

      // Convert e, n to Client PublicKey
      RSAPublicKeySpec publicSpec = new RSAPublicKeySpec(bigN, bigE);
      KeyFactory factory = KeyFactory.getInstance("RSA");
      RSAPublicKey clientPublic = (RSAPublicKey) factory.generatePublic(publicSpec);
      Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
      cipher.init(Cipher.ENCRYPT_MODE, clientPublic);
      byte[] arrCipherData = cipher.doFinal(data.getBytes());
      encryptedString = new Base64(false).encodeBase64String(arrCipherData);
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
      byte[] decodedEncryptionData = new Base64(false).decode(encData);

      KeyFactory fac = KeyFactory.getInstance("RSA");
      Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
      byte[] priArr = new Base64(false).decode(privateKey);
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
  public String decryptRSA(String encData, String N, String D) {
    // TODO Auto-generated method stub
    String decryptedString = "";
    try {
      byte[] decodedEncryptionData = new Base64(false).decode(encData);

      String encodedN = N;
      String encodedD = D;
      byte[] decodedN = new Base64(true).decode(encodedN.getBytes());
      byte[] decodedD = new Base64(true).decode(encodedD);

      ByteBuffer buffer = ByteBuffer.allocate(decodedN.length + 1);
      buffer.put((byte) 0x00).put(decodedN);
      BigInteger bigN = new BigInteger(buffer.array());

      buffer = ByteBuffer.allocate(decodedD.length + 1);
      buffer.clear();
      buffer.put((byte) 0x00).put(decodedD);
      BigInteger privateExponent = new BigInteger(buffer.array());

      RSAPrivateKeySpec privateSpec = new RSAPrivateKeySpec(bigN, privateExponent);
      KeyFactory factory = KeyFactory.getInstance("RSA");
      RSAPrivateKey clientPrivate = (RSAPrivateKey) factory.generatePrivate(privateSpec);
      Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
      cipher.init(Cipher.DECRYPT_MODE, clientPrivate);
      byte[] arrCipherData = cipher.doFinal(decodedEncryptionData);
      decryptedString = new String(arrCipherData);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return decryptedString;
  }

  @SuppressWarnings("static-access")
  @Override
  public String encryptAES(String data, String aesKey, String iv) {
    // TODO Auto-generated method stub
    byte[] keyData = new Base64(false).decode(aesKey);
    byte[] ivData = new Base64(false).decode(iv);

    SecretKey secureKey = new SecretKeySpec(keyData, "AES");
    String encData = null;

    try {
      Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
      c.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(ivData));
      byte[] encrypted = c.doFinal(data.getBytes("UTF-8"));
      encData = new Base64(false).encodeBase64String(encrypted);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return encData;
  }

  @Override
  public String decryptAES(String encData, String aesKey, String iv) {
    // TODO Auto-generated method stub
    byte[] keyData = new Base64(false).decode(aesKey);
    byte[] ivData = new Base64(false).decode(iv);

    SecretKey secureKey = new SecretKeySpec(keyData, "AES");
    String decyptString = null;

    try {
      Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
      c.init(Cipher.DECRYPT_MODE, secureKey, new IvParameterSpec(ivData));

      byte[] byteStr = new Base64(false).decode(encData);
      decyptString = new String(c.doFinal(byteStr), "UTF-8");
    } catch (Exception e) {
      e.printStackTrace();
    }

    return decyptString;
  }


}
