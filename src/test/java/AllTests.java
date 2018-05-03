
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import io.rezoome.core.ServiceInitializer;
import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.external.inha.mapper.InhaMapperEntity;
import io.rezoome.lib.json.JSON;
import io.rezoome.manager.amq.AMQMessageCryptoEntity;
import io.rezoome.manager.amq.AMQMessageEntity;
import io.rezoome.manager.amq.AMQMessageHandlerImpl;
import io.rezoome.manager.property.PrivateProperties;
import io.rezoome.manager.provider.ManagerProvider;
import junit.framework.TestSuite;

public class AllTests extends TestSuite {

	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	@Before
	public void initialize() {
		/*
		 * ManagerProvider.property().initialize(InitialEvent.RUNTIME);
		 * ManagerProvider.clsarrange().initialize(InitialEvent.RUNTIME);
		 * ManagerProvider.pushcommand().initialize(InitialEvent.RUNTIME);
		 */
	}

	@Test
	public void encryptForClient() {
		String plainText = "Hello World!";
		try {
			// Generate Client Public key.
			String clientE = "AQAB";
			String clientN = "d41zfuC7n8loCB4Vzhk_l-eUkJwg2gouUUBPpdHecZ2Ih_FdsYOSUOd7V15ks9puo-ZTPjSMe-crvMQE6dHaky1EUd3Kk3X8MG1qvtI7IQSGLucZXWaRWURbyHI-tZtpa6z9QOgGfcfd18ILxUfq976SWbq1csmQxoRsCKDCPg9rImQ7ZunzDPQP64DcmEIxNWVWkH8BiRQqmsGaFvzzLIttpqJ9cSwlFv0ns1EGTmcCmSTw6pa7q19C0f7Zs5oOhUsKj1ElytoJoZLRsObwc_GAuHPLWRsfyPoWsREfia3Z39Odde7RXfdBri8xzauCKA9S7EVq5k3Y1gVUqbzT7Q";
			BigInteger bigE = new BigInteger(Base64.getUrlDecoder().decode(clientE));
			byte[] decodedN = Base64.getUrlDecoder().decode(clientN.getBytes());
			ByteBuffer buffer = ByteBuffer.allocate(decodedN.length + 1);
			buffer.put((byte) 0x00).put(decodedN);
			BigInteger bigN = new BigInteger(buffer.array());

			// Convert e, n to Client PublicKey
			RSAPublicKeySpec publicSpec = new RSAPublicKeySpec(bigN, bigE);
			KeyFactory factory = KeyFactory.getInstance("RSA");
			RSAPublicKey clientPublic = (RSAPublicKey) factory.generatePublic(publicSpec);

			// Generate instance AES Key
			KeyGenerator aesGen = KeyGenerator.getInstance("AES");
			aesGen.init(256, SecureRandom.getInstance("SHA1PRNG"));
			SecretKey aesKey = aesGen.generateKey();
			// aesKey = new
			// SecretKeySpec("0123456789abcdef0123456789abcdef".getBytes(),
			// "AES");

			// Generate IV
			byte[] iv = new byte[16];
			SecureRandom.getInstance("SHA1PRNG").nextBytes(iv);

			// Encrypt data with AES Key
			Cipher aseCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			aseCipher.init(Cipher.ENCRYPT_MODE, aesKey, new IvParameterSpec(iv));
			ByteArrayInputStream bis = new ByteArrayInputStream(plainText.getBytes());
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buf = new byte[32];
			int length = 0;
			while ((length = bis.read(buf)) > 0) {
				bos.write(aseCipher.update(buf, 0, length));
			}
			bos.write(aseCipher.doFinal());
			bos.flush();
			bos.close();
			byte[] encryptedData = bos.toByteArray();

			// Encrypt AESKey with client public Key
			Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			rsaCipher.init(Cipher.ENCRYPT_MODE, clientPublic);
			rsaCipher.update(Base64.getEncoder().encodeToString(aesKey.getEncoded()).getBytes());
			// rsaCipher.update("0123456789abcdef".getBytes());
			byte[] encryptedKey = rsaCipher.doFinal();

			// Java Decrypt
			String encodedD = "aXT7FkaC-tYc0FxJe73F3OdIo681Q2CLrtx95ZWVFL-TeectcLLQ1FD8-fqn9gaOZkF72HleGsW2TRLUCrU0i3L4uwZb9Wu0A7vg12Z3Bg8JlkIAm-Un_YhRNiWgr23htjuoQiLp5vXw-KuQ2nswB02xpzkNaa3n6VVSPuIftcGLEml4SYN8PnsMxqYOzadO-tz-iRnOD8MUoGmUgyyxT9xD9ejjP5ItVbKgLGKqCZmMpVOUkOqHBF3oadKccfBCrcIoN5qlKA-uZW3Gd6G8az8zy8tvJu-1yRCA0GhjaKYD5DCFLF5OpWZcNbL55R4PByxsZBgl776Yj7jcegpfQQ";
			byte[] decodedD = Base64.getUrlDecoder().decode(encodedD);
			buffer.clear();
			buffer.put((byte) 0x00).put(decodedD);
			BigInteger privateExponent = new BigInteger(buffer.array());
			RSAPrivateKeySpec privateSpec = new RSAPrivateKeySpec(bigN, privateExponent);
			factory = KeyFactory.getInstance("RSA");
			RSAPrivateKey clientPrivate = (RSAPrivateKey) factory.generatePrivate(privateSpec);

			System.out.println("var publicKey  = '" + Base64.getEncoder().encodeToString(clientPublic.getEncoded()) + "';");
			System.out.println("var privateKey = '" + Base64.getEncoder().encodeToString(clientPrivate.getEncoded()) + "';");
			System.out.println("var aesKey = '" + Base64.getUrlEncoder().encodeToString(aesKey.getEncoded()) + "';");
			System.out.println("var key = '" + Base64.getUrlEncoder().encodeToString(encryptedKey) + "\';");
			System.out.println("var iv = '" + Base64.getUrlEncoder().encodeToString(iv) + "\';");
			System.out.println("var records = '" + Base64.getUrlEncoder().encodeToString(encryptedData) + "\';");

			// Decrypt Key
			rsaCipher.init(Cipher.DECRYPT_MODE, clientPrivate);
			rsaCipher.update(encryptedKey);
			byte[] decryptedKey = rsaCipher.doFinal();

			// Decrypt Data
			aesKey = new SecretKeySpec(Base64.getDecoder().decode(decryptedKey), "AES");
			aseCipher.init(Cipher.DECRYPT_MODE, aesKey, new IvParameterSpec(iv));
			bos.reset();
			bos.write(aseCipher.update(encryptedData));
			bos.write(aseCipher.doFinal());
			byte[] decryptedData = bos.toByteArray();

			System.out.println("Decrypted : " + new String(decryptedData));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchPaddingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidKeyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalBlockSizeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (BadPaddingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidAlgorithmParameterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void generateRSAKeyPair() {
		KeyPairGenerator kpg;
		try {
			kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(2048, new SecureRandom());
			KeyPair keyPair = kpg.generateKeyPair();

			System.out.println("Public : " + Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));

			System.out.println("Private : " + Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()));

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void encryptTest() {
		try {
			String aesKey = "giemncc3LqXT+o8MWucPqkGQPCmsBliHCwCOlxoHY6k=";
			byte[] parsedKey = Base64.getDecoder().decode(aesKey);
			String encodedPublic = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh+t28y1H3534QQYhIz2BZZ0YfGHopH3SDdtb9WkRHXA51ZYvo7O6b1Me00EEH4s6zK8G466Sn4i7R74kahxuTBkJK33zvsTfKke4iOhEu88sZOOKKhe7hGVKWKRRXQEsqYBE5/9iPRSx+eKOQIlfkUwiIpOLnVjilH8RlyCR4DYumf1LB/6GE5CoudfAQaMTujO5lznj/lWXxPTsRs5tv7ii32DqR91ueksMt23HlpTRHh8X1SoftvdwBNF263EgwIR+5rTQ38Nmkh+CqcqJuYAjLaGKuvNfXH+uUX+A3HsbKL8qfDTdWZKY0HLOHhs5dth73wF9uDauytiUNP1tMQIDAQAB";
			X509EncodedKeySpec xSpec = new X509EncodedKeySpec(Base64.getDecoder().decode(encodedPublic));
			RSAPublicKey publicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(xSpec);

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey, new SecureRandom());
			bos.write(cipher.doFinal(parsedKey));

			System.out.println(Base64.getEncoder().encodeToString(bos.toByteArray()));
		} catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void decryptTest() {
		String key = "Lnp8fOsqwW1pR3GBxcM/0UQJlJp4x8ZE3cVGAKEKa678GPNt/BrMoi5xUplVTYoX0LsPQjF465ubaZsbksrlllF5l3ao9noj8FODzEgtdWuCSFAI9xcdZQi7gU7arDAOhMgd7DgKy7iNETuUlI0Ft9qnwX6dFEp2LQbeoodezb819y97TslhoMCbVCPfPIPG/myco8H/CKrQ46goF1JYb/lrejYpr9UQYsR1JKYY4RsOjr/jyRp765NefjnEGS3ug0SJxC0npfFj7MoRkoLmUDnGjSsZDgn1TJHVm2bOfQZOMzgfXQgep1LwzL/Cj/wuWQkPS1bS6sjHT1KnJu4NVw==";
		String encodedPrivate = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCH63bzLUffnfhBBiEjPYFlnRh8YeikfdIN21v1aREdcDnVli+js7pvUx7TQQQfizrMrwbjrpKfiLtHviRqHG5MGQkrffO+xN8qR7iI6ES7zyxk44oqF7uEZUpYpFFdASypgETn/2I9FLH54o5AiV+RTCIik4udWOKUfxGXIJHgNi6Z/UsH/oYTkKi518BBoxO6M7mXOeP+VZfE9OxGzm2/uKLfYOpH3W56Swy3bceWlNEeHxfVKh+293AE0XbrcSDAhH7mtNDfw2aSH4Kpyom5gCMtoYq6819cf65Rf4Dcexsovyp8NN1ZkpjQcs4eGzl22HvfAX24Nq7K2JQ0/W0xAgMBAAECggEAVkpYJ0S9F6vLu0Rv71aR+vgEXpsuPpgermtwc1pz4jOolAAYWJ63IwwrSsVBH+Gdv+ryvcPMFHqTI5YEulU4dRmD59e70oZchBQqhMipMm7x6U9IHTcZ9pDz5VjUyu3+89GabYuGS+8CmvmJhFFymeUy2q6/eXK/zrTpbv/ffO8btPw6u+ZmGP1+VDYuYvX1eVyv0xb1w9R1VLKHvLJMrs+2c/HWHZUtLtd/ub6STmnupxa5bVcJQ6r/XnXQVil3FAw7Unt/LHT4bBXN6K32wBUQFyT63npjnT9TeXcnYEQGa3BU4M/wKPVgW4gDFnSWbuXwr5JA3BoSht1pcdvGAQKBgQDH7GxVPb5mL2/KVvZFr7ARjsic+Hc7aeCh7ffOIfZ9XulUYg1FOQrQdO4s7VZrPAI6cpX4fDNXK6bKDl+bD/0uILX+Td9pP/kG1ZDlyMOt06INfHswaCOlUefRxx2lKjYMRnB3xwaObZa18N3l4mQfUtUIZblyaeUi6ryOeVLhoQKBgQCuCzsbA4Ug3GJv7sx4K23SckkV6jtqGwYMey76OoNz0FbgQgYvyGIwOuZ9mF/tO++xRlHPyP3Ureq1UswdmzgDAYrJ0sgqgjNlpJWXEu1HcuKLYbXfipQ0MHCEFsqsG6JZV6x+6AXPh0vI4NjMs+TZVIOH0WVtZePvtIX4zeoBkQKBgQCjv0Nj9fRy4bugcOW5YhLF7rrlxqRx52BzgOOM1Eh9mzVlRgI0hmoTHxLNfX9gyX/Ru3SWV7P9W+V6bZdk/A2UsHof1kx8ekdPj7DkZDHRuBttDF4h4coAaDuFmJ7xXsdx+me30y3Njnj4gFZfKbRz63ZNMHRZ/94LYVQbJcf6wQKBgF5n7yanESaTBL5MnRKuFAMsROKjTJkwkWZhx0iqRrWilA1PIwX2PvSEbpQ//AC2Gz1gtG3uS2l68S5r/BYvJQHUa1Ewia0tNO30icQJ2zV/Zs1OMhZQmDV69P1hU4xp7PVjbkjkWIqKjBwpgs/+4KrWggZBGjBuLWbP2D/uiflRAoGBAL9FQUHvwT+WARnKam1qnwXyf3aSZIrtMB+1wS9XAQms6E95Ai08ox6fIIlhD1gPRwxqMlMFwex4TaJ+F5xmNV9lv2U6N0AXv+OwzOaY45WNYGq6Hsjy1QSJlwiRotjiieV0X5aYCQ642j3j/LMAIo+v0uyw5gnXn6+tNk4Hkb+9";
		PKCS8EncodedKeySpec privateSpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(encodedPrivate));
		KeyFactory factory;
		try {
			factory = KeyFactory.getInstance("RSA");
			RSAPrivateKey privateKey = (RSAPrivateKey) factory.generatePrivate(privateSpec);
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			// bos.write(cipher.update(Base64.getDecoder().decode(key)));
			bos.write(cipher.doFinal(Base64.getDecoder().decode(key)));
			byte[] decrypted = bos.toByteArray();

			System.out.println(Base64.getEncoder().encodeToString(decrypted));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void encryptForClient2() {
		String plainText = "이런 현상이 심화되면 올해 한국 경제의 3% 성장 달성도 힘겨울 수밖에 없다. 무엇보다 우리 제조업이 구조적 위기에 직면하고 있다. 제조업은 좋은 일자리의 원천이고 성장을 떠받치는 근간이다. 제조업의 체질 개선과 구조개혁, 규제철폐로 기업의 혁신을 촉진할 수 있는 산업정책의 근본적인 재정립이 시급하다. 그런 정책이 안보인다.이런 현상이 심화되면 올해 한국 경제의 3% 성장 달성도 힘겨울 수밖에 없다. 무엇보다 우리 제조업이 구조적 위기에 직면하고 있다. 제조업은 좋은 일자리의 원천이고 성장을 떠받치는 근간이다. 제조업의 체질 개선과 구조개혁, 규제철폐로 기업의 혁신을 촉진할 수 있는 산업정책의 근본적인 재정립이 시급하다. 그런 정책이 안보인다.이런 현상이 심화되면 올해 한국 경제의 3% 성장 달성도 힘겨울 수밖에 없다. 무엇보다 우리 제조업이 구조적 위기에 직면하고 있다. 제조업은 좋은 일자리의 원천이고 성장을 떠받치는 근간이다. 제조업의 체질 개선과 구조개혁, 규제철폐로 기업의 혁신을 촉진할 수 있는 산업정책의 근본적인 재정립이 시급하다. 그런 정책이 안보인다.이런 현상이 심화되면 올해 한국 경제의 3% 성장 달성도 힘겨울 수밖에 없다. 무엇보다 우리 제조업이 구조적 위기에 직면하고 있다. 제조업은 좋은 일자리의 원천이고 성장을 떠받치는 근간이다. 제조업의 체질 개선과 구조개혁, 규제철폐로 기업의 혁신을 촉진할 수 있는 산업정책의 근본적인 재정립이 시급하다. 그런 정책이 안보인다.이런 현상이 심화되면 올해 한국 경제의 3% 성장 달성도 힘겨울 수밖에 없다. 무엇보다 우리 제조업이 구조적 위기에 직면하고 있다. 제조업은 좋은 일자리의 원천이고 성장을 떠받치는 근간이다. 제조업의 체질 개선과 구조개혁, 규제철폐로 기업의 혁신을 촉진할 수 있는 산업정책의 근본적인 재정립이 시급하다. 그런 정책이 안보인다.이런 현상이 심화되면 올해 한국 경제의 3% 성장 달성도 힘겨울 수밖에 없다. 무엇보다 우리 제조업이 구조적 위기에 직면하고 있다. 제조업은 좋은 일자리의 원천이고 성장을 떠받치는 근간이다. 제조업의 체질 개선과 구조개혁, 규제철폐로 기업의 혁신을 촉진할 수 있는 산업정책의 근본적인 재정립이 시급하다. 그런 정책이 안보인다.이런 현상이 심화되면 올해 한국 경제의 3% 성장 달성도 힘겨울 수밖에 없다. 무엇보다 우리 제조업이 구조적 위기에 직면하고 있다. 제조업은 좋은 일자리의 원천이고 성장을 떠받치는 근간이다. 제조업의 체질 개선과 구조개혁, 규제철폐로 기업의 혁신을 촉진할 수 있는 산업정책의 근본적인 재정립이 시급하다. 그런 정책이 안보인다.이런 현상이 심화되면 올해 한국 경제의 3% 성장 달성도 힘겨울 수밖에 없다. 무엇보다 우리 제조업이 구조적 위기에 직면하고 있다. 제조업은 좋은 일자리의 원천이고 성장을 떠받치는 근간이다. 제조업의 체질 개선과 구조개혁, 규제철폐로 기업의 혁신을 촉진할 수 있는 산업정책의 근본적인 재정립이 시급하다. 그런 정책이 안보인다.이런 현상이 심화되면 올해 한국 경제의 3% 성장 달성도 힘겨울 수밖에 없다. 무엇보다 우리 제조업이 구조적 위기에 직면하고 있다. 제조업은 좋은 일자리의 원천이고 성장을 떠받치는 근간이다. 제조업의 체질 개선과 구조개혁, 규제철폐로 기업의 혁신을 촉진할 수 있는 산업정책의 근본적인 재정립이 시급하다. 그런 정책이 안보인다.이런 현상이 심화되면 올해 한국 경제의 3% 성장 달성도 힘겨울 수밖에 없다. 무엇보다 우리 제조업이 구조적 위기에 직면하고 있다. 제조업은 좋은 일자리의 원천이고 성장을 떠받치는 근간이다. 제조업의 체질 개선과 구조개혁, 규제철폐로 기업의 혁신을 촉진할 수 있는 산업정책의 근본적인 재정립이 시급하다. 그런 정책이 안보인다.이런 현상이 심화되면 올해 한국 경제의 3% 성장 달성도 힘겨울 수밖에 없다. 무엇보다 우리 제조업이 구조적 위기에 직면하고 있다. 제조업은 좋은 일자리의 원천이고 성장을 떠받치는 근간이다. 제조업의 체질 개선과 구조개혁, 규제철폐로 기업의 혁신을 촉진할 수 있는 산업정책의 근본적인 재정립이 시급하다. 그런 정책이 안보인다.이런 현상이 심화되면 올해 한국 경제의 3% 성장 달성도 힘겨울 수밖에 없다. 무엇보다 우리 제조업이 구조적 위기에 직면하고 있다. 제조업은 좋은 일자리의 원천이고 성장을 떠받치는 근간이다. 제조업의 체질 개선과 구조개혁, 규제철폐로 기업의 혁신을 촉진할 수 있는 산업정책의 근본적인 재정립이 시급하다. 그런 정책이 안보인다.이런 현상이 심화되면 올해 한국 경제의 3% 성장 달성도 힘겨울 수밖에 없다. 무엇보다 우리 제조업이 구조적 위기에 직면하고 있다. 제조업은 좋은 일자리의 원천이고 성장을 떠받치는 근간이다. 제조업의 체질 개선과 구조개혁, 규제철폐로 기업의 혁신을 촉진할 수 있는 산업정책의 근본적인 재정립이 시급하다. 그런 정책이 안보인다.이런 현상이 심화되면 올해 한국 경제의 3% 성장 달성도 힘겨울 수밖에 없다. 무엇보다 우리 제조업이 구조적 위기에 직면하고 있다. 제조업은 좋은 일자리의 원천이고 성장을 떠받치는 근간이다. 제조업의 체질 개선과 구조개혁, 규제철폐로 기업의 혁신을 촉진할 수 있는 산업정책의 근본적인 재정립이 시급하다. 그런 정책이 안보인다.이런 현상이 심화되면 올해 한국 경제의 3% 성장 달성도 힘겨울 수밖에 없다. 무엇보다 우리 제조업이 구조적 위기에 직면하고 있다. 제조업은 좋은 일자리의 원천이고 성장을 떠받치는 근간이다. 제조업의 체질 개선과 구조개혁, 규제철폐로 기업의 혁신을 촉진할 수 있는 산업정책의 근본적인 재정립이 시급하다. 그런 정책이 안보인다.이런 현상이 심화되면 올해 한국 경제의 3% 성장 달성도 힘겨울 수밖에 없다. 무엇보다 우리 제조업이 구조적 위기에 직면하고 있다. 제조업은 좋은 일자리의 원천이고 성장을 떠받치는 근간이다. 제조업의 체질 개선과 구조개혁, 규제철폐로 기업의 혁신을 촉진할 수 있는 산업정책의 근본적인 재정립이 시급하다. 그런 정책이 안보인다.이런 현상이 심화되면 올해 한국 경제의 3% 성장 달성도 힘겨울 수밖에 없다. 무엇보다 우리 제조업이 구조적 위기에 직면하고 있다. 제조업은 좋은 일자리의 원천이고 성장을 떠받치는 근간이다. 제조업의 체질 개선과 구조개혁, 규제철폐로 기업의 혁신을 촉진할 수 있는 산업정책의 근본적인 재정립이 시급하다. 그런 정책이 안보인다.이런 현상이 심화되면 올해 한국 경제의 3% 성장 달성도 힘겨울 수밖에 없다. 무엇보다 우리 제조업이 구조적 위기에 직면하고 있다. 제조업은 좋은 일자리의 원천이고 성장을 떠받치는 근간이다. 제조업의 체질 개선과 구조개혁, 규제철폐로 기업의 혁신을 촉진할 수 있는 산업정책의 근본적인 재정립이 시급하다. 그런 정책이 안보인다.asdf123";
		try {
			// Generate Client Public key.
			String clientE = "AQAB";
			String clientN = "d41zfuC7n8loCB4Vzhk_l-eUkJwg2gouUUBPpdHecZ2Ih_FdsYOSUOd7V15ks9puo-ZTPjSMe-crvMQE6dHaky1EUd3Kk3X8MG1qvtI7IQSGLucZXWaRWURbyHI-tZtpa6z9QOgGfcfd18ILxUfq976SWbq1csmQxoRsCKDCPg9rImQ7ZunzDPQP64DcmEIxNWVWkH8BiRQqmsGaFvzzLIttpqJ9cSwlFv0ns1EGTmcCmSTw6pa7q19C0f7Zs5oOhUsKj1ElytoJoZLRsObwc_GAuHPLWRsfyPoWsREfia3Z39Odde7RXfdBri8xzauCKA9S7EVq5k3Y1gVUqbzT7Q";

			String aesKey = ManagerProvider.crypto().generateAES();
			String iv = ManagerProvider.crypto().generateIV();

			System.out.println("encoded aesKey : " + aesKey);
			System.out.println("encoded iv : " + iv);

			String encryptKey = ManagerProvider.crypto().encryptRSA(aesKey, clientN, clientE);
			System.out.println("encrypt aeskey : " + encryptKey);

			String encryptData = ManagerProvider.crypto().encryptAES(plainText, aesKey, iv);
			System.out.println("encrypt data : " + encryptData);

			String encodedD = "aXT7FkaC-tYc0FxJe73F3OdIo681Q2CLrtx95ZWVFL-TeectcLLQ1FD8-fqn9gaOZkF72HleGsW2TRLUCrU0i3L4uwZb9Wu0A7vg12Z3Bg8JlkIAm-Un_YhRNiWgr23htjuoQiLp5vXw-KuQ2nswB02xpzkNaa3n6VVSPuIftcGLEml4SYN8PnsMxqYOzadO-tz-iRnOD8MUoGmUgyyxT9xD9ejjP5ItVbKgLGKqCZmMpVOUkOqHBF3oadKccfBCrcIoN5qlKA-uZW3Gd6G8az8zy8tvJu-1yRCA0GhjaKYD5DCFLF5OpWZcNbL55R4PByxsZBgl776Yj7jcegpfQQ";
			String decryptKey = ManagerProvider.crypto().decryptRSA(encryptKey, clientN, encodedD);
			System.out.println("decrypt aeskey : " + decryptKey);

			String decryptData = ManagerProvider.crypto().decryptAES(encryptData, decryptKey, iv);
			System.out.println("decrypt data : " + decryptData);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void crypto2() {

		try {
			ManagerProvider.property().initialize(InitialEvent.RUNTIME);
			ManagerProvider.key().initialize(InitialEvent.RUNTIME);

			String privateKey = ManagerProvider.key().getPrivKeyStr(PrivateProperties.CERT_NAME);
			String publicKey = ManagerProvider.key().getPubKeyStr(PrivateProperties.CERT_NAME);

			System.out.println("privateKey : " + privateKey);
			System.out.println("publicKey : " + publicKey);

			String data = "test data";

			ManagerProvider.crypto().initialize(InitialEvent.RUNTIME);

			String encryptData = ManagerProvider.crypto().encryptRSA(data, publicKey);
			System.out.println("encryptData : " + encryptData);

			String decryptData = ManagerProvider.crypto().decryptRSA(encryptData, privateKey);
			System.out.println("decryptData : " + decryptData);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Test
	// public void rsaPublicKey() {
	// String msg =
	// "w5Sjr3UekqqLntnSQtJfx22YEP/DsYt3CFDUPgb9K/ITkte9PAh7DlaApVcOhsC11z1hNQJUBBBElGZA5QYwXamiVtZxPLdXI0dlLh15r4/2ynVN/vJhF9P9UgLxwkLCAQyxJ6Z5JlnrHByrLmdWGMS48nZSes7JoYK19qrYEjEik1SoCCXy8Lxm4P/r/IF9dklo63TkADIPvESrV2uLDeF8/+esboH5Oe4IUMuRwRVYWyhU+txbdhGZzqV+f6mLwnRw3uXprkd7pi3i4jXeh93StiK2Ppn4t/L0FfW55YA0p+kZNibHiDQYtmHBIpAFBxaz8E45yOcmmrpKnSlcKQ==";
	//
	// BigInteger number = new BigInteger(
	// "26117861690756466922005502890492983429718971505174851527216050438193513120829100504242088239625964284130697990307396258889032108152689205086555601458635169493602779830552798194731493978822258685798041478774230028957910265586399972103649544625437563265444492376117012507009289114401734004904273340822290411951794203877257919310864247321035933862101020093580160716689507556520642358058037385494671261875083361334567033763700996422147938731251915537425731210595536000885779297748874583017456138823651086417627850770669559273655966641096946358487871922883992440055851895727272346861531213280981966925100427230835789134263");
	// byte[] data = number.toByteArray();
	// String encodedString = new
	// String(Base64.getUrlEncoder().encode(number.toByteArray()));
	// System.out.println(encodedString);
	//
	// byte[] decoded = Base64.getUrlDecoder().decode(
	// "zuSwchiSRGQZUMSuLgFj8smjQWc7Qi2p0C1reirsEdwtx0mjoGEqAYkLy9FCh4lfWeMLgzh_dKztqw1Tnj0czvMwpwAhVvcWqgBn5gXmoVC8cP4Gtyg8VtVlnfNdv3K0cw7nCZ1sYFIH3tn8K0S0DImVo78V3lctARceE9K46kgIznV3jDfVmHum6ZqBwTMARah3Q2Sf2WYYb4KD5N5z5FLw3Zcmu4Eh0AUElZsZGpDicP8DoBtxIn_BRk2Xl9Lejc-z_2cNBwaoOFqqLk5Kn5UBNGOXF529tstWnS-3DFRdIg4g1_HNixXJm2fjl0KZ20vBHPcKTtr77cKb1X35tw");
	// ByteBuffer buffer = ByteBuffer.allocate(257);
	// buffer.put((byte) 0x00);
	// buffer.put(decoded);
	//
	// BigInteger number2 = new BigInteger(buffer.array());
	// System.out.println(number2);
	// }
	//
	// @Test
	// public void rsaKey() {
	// KeyPairGenerator gen;
	// try {
	// gen = KeyPairGenerator.getInstance("RSA");
	// gen.initialize(2048, new
	// SecureRandom(Long.toHexString(System.currentTimeMillis()).getBytes()));
	// KeyPair keyPair = gen.generateKeyPair();
	// String pubKey = new
	// String(Base64.getEncoder().encode(keyPair.getPublic().getEncoded()));
	// System.out.println(pubKey);
	// String priKey = new
	// String(Base64.getEncoder().encode(keyPair.getPrivate().getEncoded()));
	// System.out.println(priKey);
	//
	// KeyFactory factory = KeyFactory.getInstance("RSA");
	// X509EncodedKeySpec pubSpec = new
	// X509EncodedKeySpec(keyPair.getPublic().getEncoded());
	// PKCS8EncodedKeySpec priSpec = new
	// PKCS8EncodedKeySpec(keyPair.getPrivate().getEncoded());
	// PublicKey publicKey = factory.generatePublic(pubSpec);
	// PrivateKey privateKey = factory.generatePrivate(priSpec);
	//
	// System.out.println(publicKey.getAlgorithm());
	// } catch (NoSuchAlgorithmException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (InvalidKeySpecException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }
	//
	// @Test
	// public void pemToPublic(){
	// String pemPublic = "-----BEGIN PUBLIC KEY-----\n" +
	// "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAz2hcqg0E+tv32oQ2Ays7\n" +
	// "HjRX0mf6HoCKdIJvGbXXgowsOyXnTW3OMeNpO7iPRvqJUe+UwVTZreNF3KygyvjI\n" +
	// "J2U6GOU9anfK0QchFnGbmPXxh7rXpx1yWlR1V/bAg19LsdJ3o8BaZr0PqSMonwTD\n" +
	// "B9MoGIuscdxCvQPVrlwXkeXEpwybrYZVUw4LjETQEXprz23waG/+ZxuCvkKYZBWF\n" +
	// "0WhtuzQ7EaNWyXTlww/c0hS6obIkomkA5CcpgipDSqqAupucinFGWolTyJWoM4QL\n" +
	// "ZOvH2kXudnLWX0PG9EFCXqVqgjz3xcROGKFDiTfAVKcak1nUkOKzSgm/EZqD2lVG\n" +
	// "bQIDAQAB\n" +
	// "-----END PUBLIC KEY-----";
	// String pemPrivate = "-----BEGIN PRIVATE KEY-----\n" +
	// "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDPaFyqDQT62/fa\n" +
	// "hDYDKzseNFfSZ/oegIp0gm8ZtdeCjCw7JedNbc4x42k7uI9G+olR75TBVNmt40Xc\n" +
	// "rKDK+MgnZToY5T1qd8rRByEWcZuY9fGHutenHXJaVHVX9sCDX0ux0nejwFpmvQ+p\n" +
	// "IyifBMMH0ygYi6xx3EK9A9WuXBeR5cSnDJuthlVTDguMRNARemvPbfBob/5nG4K+\n" +
	// "QphkFYXRaG27NDsRo1bJdOXDD9zSFLqhsiSiaQDkJymCKkNKqoC6m5yKcUZaiVPI\n" +
	// "lagzhAtk68faRe52ctZfQ8b0QUJepWqCPPfFxE4YoUOJN8BUpxqTWdSQ4rNKCb8R\n" +
	// "moPaVUZtAgMBAAECggEBAImVfzLG5DDMzkPERFghy1uj+UX393FYb4AOLkjt2YIe\n" +
	// "HhE2eZNhbdCmPF6DdXfuJj2WIiWFdcjl5KgYU6mREqUff0SYfP8ta/rxMb/DqSX0\n" +
	// "sD4LcZbH791//T9WzinXg0z4gqi7prxUWhkm24FxFTcRmZ1+uevl4tejvZGmQXRj\n" +
	// "tuuPioCY4WX5aTqdSEB5l3BLTMmjaRtEBP+3BFkjVFnHCQWOjCb21Nq01OLT7O8D\n" +
	// "iFmBudBpU4OooCdSUzAzP8bOIrQWIDVbttdt4RrTlPUU+bcmQ2emA+2CqNB4u4wf\n" +
	// "TFd3rbyyfHOqGkR31MEvJbyRB03OO2JPhaV1WH2arwECgYEA8IivaH6TxpTVjUsk\n" +
	// "FAw2LAfy9roV8u/m+EY+Uu6ThvLeceIJgzrcjOYPpsDZuGs04Yy/N/Rs2qXcM5gg\n" +
	// "HHeszv/3kIYE52D6ezmiZryBp54ds/1hkAmyFG38yDNCjhRgXshCyA2FdRPazvJG\n" +
	// "GOxpXOJb8AIt3S0KG16/gM6b3U0CgYEA3L5mrh/RRn84TDOLuYx+KG0BATaBmZCX\n" +
	// "FCs6w4tYM5PLAzSedzh5V9dNXU0uGYywJ3zt0TbpRWNazU6RDlMZrUnsFDN63e+D\n" +
	// "NXw1YZzDSf4TWJr5t7I30LnBoPQ3aUC5qROW6+SSydxOPxUovYHGTa44Oc6uwJrh\n" +
	// "Fvq8x74C/aECgYEAwlc9mikDDuR78pAuk0AWmNbHWM+bIlDxBdIDUxBOBC/AyLS/\n" +
	// "yNbLEdBj/vA8OQPtkvscGIKVXMe7tbgI4ddTuWAYzx1pYuT5HIzBiU5vV1WqXB2y\n" +
	// "MSWUS2teXQcFlDQkdRBK3MYH+UNUe/ZF6qdArfp6G9423kggrWKNSe4lAtUCgYAY\n" +
	// "86zHofEV9ut2MZ1pq+fWyfuDF5YutZlePkS2NdoSenJIOnpvH4MX2s5z7XV4jd6b\n" +
	// "mBOJwps7rK9m5aqf4+TTIbf90JPi4mACe7fNANtSUdX9/gacLRkCDv1EJFbfHuHd\n" +
	// "qsk0gPHWv4BYwRuJ0FGKFldksWgvfVJUNqOAMIpGAQKBgQDVehXs+tBhMFhCSps6\n" +
	// "15WgMar8UBnZRymD42ULpEw3pljt15tfDziIT4ZWn9sYidLstTehlLxO4zVT2BmU\n" +
	// "T0hi9bkZii+MdSAyASrtO8QZDQNOFOhqubdOvR5Drq4kHO7yOyHF+LTYU/8E5waa\n" +
	// "Z1EO+KRDqeLaXvh9iaQ6a6v5Pg==\n" +
	// "-----END PRIVATE KEY-----";
	//
	// pemPublic = pemPublic.replaceAll("\\n", "").replace("-----BEGIN PUBLIC
	// KEY-----", "").replace("-----END PUBLIC KEY-----", "");
	// pemPrivate = pemPrivate.replaceAll("\\n", "").replace("-----BEGIN PRIVATE
	// KEY-----", "").replace("-----END PRIVATE KEY-----", "");
	// X509EncodedKeySpec pubSpec = new
	// X509EncodedKeySpec(Base64.getDecoder().decode(pemPublic.getBytes()));
	// PKCS8EncodedKeySpec priSpec = new
	// PKCS8EncodedKeySpec(Base64.getDecoder().decode(pemPrivate.getBytes()));
	// try {
	// KeyFactory factory = KeyFactory.getInstance("RSA");
	// PublicKey pubKey = factory.generatePublic(pubSpec);
	// PrivateKey priKey = factory.generatePrivate(priSpec);
	// System.out.println(Base64.getEncoder().encode(pubKey.getEncoded()));
	// System.out.println(Base64.getEncoder().encode(priKey.getEncoded()));
	// } catch (NoSuchAlgorithmException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (InvalidKeySpecException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	// @Test
	// public void aesKey() {
	// String encodedKey = "XWpoJXKGG1kJpmkUm9WvnPfU+rGwiO7OIRoALv6Ta/s=";
	// String encodedIV = "dqtpYnbdYdfqY8pQKCCbkw==";
	//
	// String encryptedString = "V3CA9GVNH3EnRq6O0M2ljw==";
	//
	//
	//
	// SecretKey decodedKey = new
	// SecretKeySpec(Base64.getDecoder().decode(encodedKey), "AES");
	// try {
	// IvParameterSpec ivSpec = new
	// IvParameterSpec(Base64.getDecoder().decode(encodedIV));
	// Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	// cipher.init(Cipher.DECRYPT_MODE, decodedKey, ivSpec);
	// cipher.update(Base64.getDecoder().decode(encryptedString.getBytes()));
	// byte[] decrypted = cipher.doFinal();
	// System.out.println((new String(decrypted)));
	// } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (InvalidKeyException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IllegalBlockSizeException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (BadPaddingException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (InvalidAlgorithmParameterException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	@Test
	public void amqFullMsgDecryptTest() {

		ManagerProvider.property().initialize(InitialEvent.RUNTIME);
		ManagerProvider.key().initialize(InitialEvent.RUNTIME);

		String msg = "{\r\n" + " cmd : \"SearchRecord\",\r\n" + " mid : \"leifajlsif\",\r\n" + " sid : \"serverID\",\r\n" + " args : {\r\n"
				+ " familyNameEN : \"familyNameEN\",\r\n" + " firstNameEN : \"firstNameEN\",\r\n" + " fullNameEN : \"PARKHUNWOOK\",\r\n"
				+ " familyNameKO : \"familyNameKO\",\r\n" + " firstNameKO : \"firstNameKO\",\r\n" + " fullNameKO : \"박헌욱\",\r\n" + " birth : '19870123',\r\n"
				+ " phone : '01064749282',\r\n" + " subIDs : ['RCOGC0008', 'RCOGC0009'],\r\n" + " gender : 1,\r\n" + " ci : '123456789abcdeftg',\r\n"
				+ " pkey : 'pkey',\r\n" + " require : ['12060991'],\r\n"
				+ " records : [{hashed: \"f1b9d789ac9ef7343b03c79d331230d7aa99be4b035c91c056bddd750da7dbfc\", subID:\"RCOGC0009\", txid:\"sdfsdfsdf\"},{hashed: \"dddd\", subID:\"RCOGC0008\", txid:\"234234234234\"}]\r\n"
				+ " }\r\n" + "}";

		String aesKey = ManagerProvider.crypto().generateAES();
		String iv = ManagerProvider.crypto().generateIV();

		String encryptData = ManagerProvider.crypto().encryptAES(msg, aesKey, iv);

		String amqFullMessage = "{\r\n" + " key : \"" + aesKey + "\",\r\n" + " iv : \"" + iv + "\",\r\n" + " msg : \"" + encryptData + "\"\r\n}";

		System.out.println("amqFullMessage : " + amqFullMessage);
		System.out.println("amqFullMessage length : " + amqFullMessage.length());

		String privateKey = ManagerProvider.key().getPrivKeyStr(PrivateProperties.CERT_NAME);
		String publicKey = ManagerProvider.key().getPubKeyStr(PrivateProperties.CERT_NAME);

		String encryptedAMQFullMessage = ManagerProvider.crypto().encryptRSA(amqFullMessage, publicKey);
		System.out.println("encrypted AMQ Full message : " + encryptedAMQFullMessage);

		String decrytedAMQFullMessage = ManagerProvider.crypto().decryptRSA(encryptedAMQFullMessage, privateKey);
		System.out.println("decrypted AMQ Full message : " + decrytedAMQFullMessage);

		AMQMessageCryptoEntity amqCryptoEntity = new AMQMessageCryptoEntity();
		amqCryptoEntity = JSON.fromJson(amqFullMessage, AMQMessageCryptoEntity.class);

		System.out.println(amqCryptoEntity.toString());

		// AMQ User basic message AES Decrypt
		String clientKey = amqCryptoEntity.getKey();
		String clientIv = amqCryptoEntity.getIv();
		String amqMessage = amqCryptoEntity.getMsg();
		amqMessage = ManagerProvider.crypto().decryptAES(amqMessage, clientKey, clientIv);

		System.out.println("decrypted amqMessage : " + amqMessage);

		AMQMessageEntity amqEntity = new AMQMessageEntity();
		amqEntity = JSON.fromJson(amqMessage, AMQMessageEntity.class);

		System.out.println(amqEntity.toString());

	}

	@Test
	public void DBUserExistAndDataExistTest() {
		// throw new ServiceException("error");

		// InitialEvent event = InitialEvent.RUNTIME;
		try {
			ServiceInitializer.initialize(InitialEvent.RUNTIME);
			// ManagerProvider.property().initialize(InitialEvent.RUNTIME);
			// ManagerProvider.clsarrange().initialize(InitialEvent.RUNTIME);
			// ManagerProvider.pushcommand().initialize(InitialEvent.RUNTIME);
			// ManagerProvider.database().initialize(InitialEvent.RUNTIME);
			// ManagerProvider.job().initialize(InitialEvent.RUNTIME);
			// ManagerProvider.network().initialize(InitialEvent.RUNTIME);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		AMQMessageEntity msg = this.AMQMessageUserExistAndDataExist();
		System.out.println(msg.toString());
		AMQMessageHandlerImpl.getInstance().handleMessage(msg);
	}

	/*
	 * @Test public void DBUserNotExistTest() { // throw new
	 * ServiceException("error");
	 * 
	 * InitialEvent event = InitialEvent.RUNTIME; try {
	 * ServiceInitializer.initialize(event); } catch (Throwable e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * AMQMessageEntity msg = this.AMQMessageUserNotExist();
	 * AMQMessageHandlerImpl.getInstance().handleMessage(msg); }
	 * 
	 * @Test public void DBRequriedKeyTest() { // throw new
	 * ServiceException("error");
	 * 
	 * InitialEvent event = InitialEvent.RUNTIME; try {
	 * ServiceInitializer.initialize(event); } catch (Throwable e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * AMQMessageEntity msg = this.AMQMessageRequiredKey();
	 * AMQMessageHandlerImpl.getInstance().handleMessage(msg); }
	 */
	public AMQMessageEntity AMQMessageUserExistAndDataExist() {
		AMQMessageEntity entity = new AMQMessageEntity();
		String msg = "{\r\n" + " cmd : \"SearchRecord\",\r\n" + " mid : \"leifajlsif\",\r\n" + " sid : \"serverID\",\r\n" + " args : {\r\n"
				+ " familyNameEN : \"familyNameEN\",\r\n" + " firstNameEN : \"firstNameEN\",\r\n" + " fullNameEN : \"PARKHUNWOOK\",\r\n"
				+ " familyNameKO : \"familyNameKO\",\r\n" + " firstNameKO : \"firstNameKO\",\r\n" + " fullNameKO : \"박헌욱\",\r\n" +
				// " fullNameKO : \"PARKHUNWOOK\",\r\n" +
				" birth : '19870123',\r\n" + " phone : '01064749282',\r\n" +
				// " subIDs : ['RCCNF0001'],\r\n" +
				" subIDs : ['RCOGC0008', 'RCOGC0009'],\r\n" + " gender : 1,\r\n" + " ci : '123456789abcdeftg',\r\n" + " pkey : 'pkey',\r\n"
				+ " require : ['12060991'],\r\n"
				+ " records : [{hashed: \"f1b9d789ac9ef7343b03c79d331230d7aa99be4b035c91c056bddd750da7dbfc\", subID:\"RCOGC0009\", txid:\"sdfsdfsdf\"},{hashed: \"dddd\", subID:\"RCOGC0008\", txid:\"234234234234\"}]\r\n"
				+
				// " records : [{hashed: \"dddd\", subID:\"RCLPT0005\",
				// txid:\"sdfsdfsdf\"},{hashed:
				// \"dddd\",subID:\"RCLPT0006\",
				// txid:\"23123123235234234\"}]\r\n" +
				" }\r\n" + "}";

		try {
			entity = JSON.fromJson(msg, AMQMessageEntity.class);
			System.out.println("AMQMessageUserExistAndDataExist : ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}

	public AMQMessageEntity AMQMessageRequiredKey() {
		AMQMessageEntity entity = null;
		entity = new AMQMessageEntity();
		String msg = "{\r\n" + " cmd : \"SearchRecord\",\r\n" + " mid : \"leifajlsif\",\r\n" + " token : \"welajslkdjfasdf\",\r\n" + " args : {\r\n"
				+ " username : 'PARKHUNWOOK',\r\n" + " birth : '1987-03-18',\r\n" + " gender : 1,\r\n" + " phone : '010-6474-9283',\r\n"
				+ " ci : '123456789abcdeftg',\r\n" + " email : 'exle@nate.com'\r\n" + " }\r\n" + "}";
		try {
			entity = JSON.fromJson(msg, AMQMessageEntity.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}

	public AMQMessageEntity AMQMessageUserNotExist() {
		AMQMessageEntity entity = null;
		entity = new AMQMessageEntity();
		String msg = "{\r\n" + " cmd : \"SearchRecord\",\r\n" + " mid : \"leifajlsif\",\r\n" + " token : \"welajslkdjfasdf\",\r\n" + " args : {\r\n"
				+ " username : 'PARK',\r\n" + " birth : '1987-03-18',\r\n" + " gender : 1,\r\n" + " phone : '010-6474-9283',\r\n"
				+ " ci : '123456789abcdeftg',\r\n" + " email : 'exle@nate.com'\r\n" + " }\r\n" + "}";
		try {
			entity = JSON.fromJson(msg, AMQMessageEntity.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}

	// @Test
	// public void initializeTest() {
	// InitialEvent event = InitialEvent.RUNTIME;
	// try {
	// ServiceInitializer.initialize(event);
	// } catch (Throwable e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	// @Test
	// public void httpClientTest() {
	// ManagerProvider.network().initialize(InitialEvent.RUNTIME);
	//
	// RequestPacketEntity requestEntity = new RequestPacketEntity();
	//
	// requestEntity.setCmd("SearchResult");
	// RequestSearchArgsEntity argsEntity = new RequestSearchArgsEntity();
	// argsEntity.setOrgCode("orgcode");
	// argsEntity.setKey("");
	// argsEntity.setIv("");
	//
	// requestEntity.setArgs(argsEntity);
	//
	// RequestPacket packet = new RequestPacket("/",
	// JSON.toJson(requestEntity));
	// try {
	// ResponsePacketEntity responseEntity =
	// ManagerProvider.network().request(packet);
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	//
	//
	// @Test
	// public void httpClientTest2() throws IOException {
	// // URL url = new
	// //
	// URL("http://devportalalb-1075047289.ap-northeast-2.elb.amazonaws.com:3000/agent/");
	// URL url = new
	// URL("https://www.google.com/accounts/ClientLogin?accountType=GOOGLE&Email=im.pkingkong@gmail.com&Passwd=phu1224!");
	//
	// try {
	// HttpURLConnection connection = (HttpURLConnection) url
	// .openConnection();
	//
	// if (connection instanceof HttpsURLConnection) {
	//
	// SSLContext ctx = SSLContext.getInstance("TLS");
	// ctx.init(new KeyManager[0],
	// new TrustManager[] { new DefaultTrustManager() },
	// new SecureRandom());
	// final SSLSocketFactory sslSocketFactory = ctx
	// .getSocketFactory();
	// SSLContext.setDefault(ctx);
	// HttpsURLConnection httpsConnection = (HttpsURLConnection) connection;
	// httpsConnection.setHostnameVerifier(new HostnameVerifier() {
	// @Override
	// public boolean verify(String arg0, SSLSession arg1) {
	// return true;
	// }
	// });
	// httpsConnection.setSSLSocketFactory(sslSocketFactory);
	// }
	//
	// connection.setConnectTimeout(60000);
	// connection.setReadTimeout(60000);
	//
	// connection.connect();
	//
	// String response = getResponse(connection.getInputStream());
	// System.out.println(response);
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	//
	// private static class DefaultTrustManager implements X509TrustManager {
	// @Override
	// public void checkClientTrusted(X509Certificate[] arg0, String arg1)
	// throws CertificateException {}
	//
	// @Override
	// public void checkServerTrusted(X509Certificate[] arg0, String arg1)
	// throws CertificateException {}
	//
	// @Override
	// public X509Certificate[] getAcceptedIssuers() {
	// return null;
	// }
	// }
	//
	// private String getResponse(InputStream inputStream) throws
	// UnsupportedEncodingException,
	// IOException {
	// BufferedReader reader = new BufferedReader(new
	// InputStreamReader(inputStream,
	// Constants.PARAM_UTF_8));
	// StringBuffer response = new StringBuffer();
	// String inputLine;
	//
	// while ((inputLine = reader.readLine()) != null) {
	// response.append(inputLine);
	// }
	// reader.close();
	// return reader.toString();
	// }
	//
	//
	// @Test
	// public void throwTest() {
	// try {
	// throwError();
	// } catch (Exception e) {
	// throw new ServiceException(e.getMessage(), e);
	// }
	// }
	//
	// @Test
	// public void throwTest2() {
	// try {
	// throwError();
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	// public void throwError() throws Exception {
	// int[] arr = new int[5];
	// try {
	// arr[6] = 1;
	// } catch (Exception e) {
	// throw new ServiceException("parsing error", e);
	// }
	// }
	//
	// public void throwError2() throws Exception {
	// int[] arr = new int[5];
	// arr[6] = 1;
	// }
	//
	// @Test
	// public void httpsClientTest() {
	// ManagerProvider.network().initialize(InitialEvent.RUNTIME);
	//
	// RequestPacketEntity requestEntity = new RequestPacketEntity();
	//
	// requestEntity.setCmd("SearchResult");
	// RequestSearchArgsEntity argsEntity = new RequestSearchArgsEntity();
	// argsEntity.setOrgCode("orgcode");
	// argsEntity.setKey("");
	// argsEntity.setIv("");
	//
	// requestEntity.setArgs(argsEntity);
	//
	// // RequestPacket packet = new RequestPacket("",
	// JSON.toJson(requestEntity));
	// RequestPacket packet = new RequestPacket("", null);
	// try {
	// ResponsePacketEntity responseEntity =
	// ManagerProvider.network().request(packet);
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	// @Test
	// public void hashTest() {
	// String hash = "";
	// try {
	// MessageDigest md = MessageDigest.getInstance("SHA-256");
	// md.update("sdfsadfasefasefasefasefasefasef".toString().getBytes());
	// byte byteData[] = md.digest();
	// StringBuffer sb = new StringBuffer();
	// for (int i = 0; i < byteData.length; i++) {
	// sb.append(Integer.toString((byteData[i] & 0xff) + 0x100,
	// 16).substring(1));
	// }
	// hash = sb.toString();
	// } catch (NoSuchAlgorithmException e) {
	// e.printStackTrace();
	// }
	//
	// System.out.println("hash : " + hash);
	// }
	//
	@Test
	public void crypto() {
		ManagerProvider.crypto().initialize(InitialEvent.RUNTIME);

		String data = "test data";

		// AES TEST
		String aesKey = ManagerProvider.crypto().generateAES();
		String iv = ManagerProvider.crypto().generateIV();
		String encData = ManagerProvider.crypto().encryptAES(data, aesKey, iv);
		System.out.println("encData : " + encData);
		String decData = ManagerProvider.crypto().decryptAES(encData, aesKey, iv);
		System.out.println("decData : " + decData);

		// RSA TEST
		Map<String, String> keys = ManagerProvider.crypto().generateRSA();
		String publicKey = keys.get("PUBLIC_KEY");
		String privateKey = keys.get("PRIVATE_KEY");
		System.out.println("publicKey : " + publicKey);
		System.out.println("privateKey : " + privateKey);
		encData = ManagerProvider.crypto().encryptRSA(data, publicKey);
		System.out.println("encData : " + encData);
		decData = ManagerProvider.crypto().decryptRSA(encData, privateKey);
		System.out.println("decData : " + decData);
	}

	//
	// @Test
	// public void searchRecordJsonTrasnTest() {
	// RequestPacketEntity requestPacket = new RequestPacketEntity();
	// RzmRsltEntity rzmResultEntity = new RzmRsltEntity();
	// rzmResultEntity.setOrgCode("ORG001");
	// rzmResultEntity.setEncKey("");
	// rzmResultEntity.setEncIv("");
	//
	// String aesKey = ManagerProvider.crypto().generateAES();
	// String iv = ManagerProvider.crypto().generateIV();
	// String encKey = "encKey";
	// String encIv = "encIv";
	//
	// List<RequestArgsEntity> records = new ArrayList<RequestArgsEntity>();
	// RequestSearchRecordsEntity record = null;
	//
	// // 1
	// record = new RequestSearchRecordsEntity();
	// String encData = ManagerProvider.crypto().encryptAES("test", aesKey, iv);
	// String hashData = ManagerProvider.crypto().hash("test");
	// record.setData(encData);
	// record.setHash(hashData);
	// record.setStored("Y");
	// records.add(record);
	//
	// // 2
	// record = new RequestSearchRecordsEntity();
	// encData = ManagerProvider.crypto().encryptAES("test2", aesKey, iv);
	// hashData = ManagerProvider.crypto().hash("test2");
	// record.setData(encData);
	// record.setHash(hashData);
	// record.setStored("N");
	// records.add(record);
	//
	//
	// rzmResultEntity.setEncKey(encKey);
	// rzmResultEntity.setEncIv(encIv);
	// rzmResultEntity.setRecords(records);
	// requestPacket.setCode("USER_EXIST_AND_DATA_EXIST");
	// requestPacket.setArgs(rzmResultEntity);
	// requestPacket.setCmd("cmd");
	// requestPacket.setMid("mid");
	//
	// System.out.println(JSON.toJson(requestPacket));
	// }

	@Test
	public void tojson() {
		// List<String> list = new ArrayList<String>();
		// list.add("기차1");
		// list.add("기차2");
		// list.add("기차3");
		// list.add("기차4");

		List<InhaMapperEntity> list = new ArrayList<InhaMapperEntity>();
		InhaMapperEntity mapperEntity = new InhaMapperEntity();
		mapperEntity.setDate("dddddd");
		;
		list.add(mapperEntity);

		Gson gson = new Gson();
		System.out.println(gson.toJson(list));
	}

	@Test
	public void toStringJson() {

		List<String> test = new ArrayList<String>();
		test.add("fffff");
		test.add("vbvvbbb");
		// String orgUserId = "sdfasdfasdf";
		// DBEntity dbEntity = JSON.fromJson("{orgUserId:" + orgUserId + "}",
		// DBEntity.class);
		// System.out.println("eeeeeeeeeeeeeeeeeeeee " + dbEntity);

		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("test", test);

		String jsonString = gson.toJson(map);

		System.out.println(jsonString);
	}
}
