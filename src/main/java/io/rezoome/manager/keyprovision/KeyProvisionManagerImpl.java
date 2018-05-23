package io.rezoome.manager.keyprovision;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStore.Entry;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableEntryException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Calendar;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.rezoome.constants.Constants;
import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.core.annotation.ManagerType;
import io.rezoome.exception.ServiceException;
import io.rezoome.lib.json.JSON;
import io.rezoome.manager.AbstractManager;
import io.rezoome.manager.keyprovision.entity.RequestKeyProvisionArgsEntity;
import io.rezoome.manager.network.entity.request.RequestPacket;
import io.rezoome.manager.network.entity.request.RequestPacketEntity;
import io.rezoome.manager.network.entity.response.ResponsePacket;
import io.rezoome.manager.property.PropertyEnum;
import io.rezoome.manager.provider.ManagerProvider;


@ManagerType(value = Constants.MANAGER_TYPE_KEYPROVISION, initPriority = 20)
public class KeyProvisionManagerImpl extends AbstractManager implements KeyProvisionManager {

  private static String keyStoreLocation;
  private static String certName;
  private static String issuerDn;
  private static String certPwd;
  private static String orgId;

  private KeyStore keyStore = null;

  private static final Logger LOG = LoggerFactory.getLogger(Constants.AGENT_LOG);

  private static class Singleton {
    private static final KeyProvisionManager instance = new KeyProvisionManagerImpl();
  }

  public static KeyProvisionManager getInstance() {
    return Singleton.instance;
  }



  @Override
  public void initialize(InitialEvent event) {

    // TODO Auto-generated method stub
    keyStoreLocation = ManagerProvider.property().getProperty(PropertyEnum.KEYSTORE_LOCATION, true);
    orgId = ManagerProvider.property().getProperty(PropertyEnum.ORG_ID);

    // certName = ManagerProvider.property().getProperty(PropertyEnum.CERT_NAME, true);
    // issuerDn = ManagerProvider.property().getProperty(PropertyEnum.ISSUER_DN, true);
    // certPwd = ManagerProvider.property().getProperty(PropertyEnum.CERT_PASSWORD, true);
    certName = ManagerProvider.property().getProperty(PropertyEnum.CERT_NAME);
    issuerDn = ManagerProvider.property().getProperty(PropertyEnum.ISSUER_DN);
    certPwd = ManagerProvider.property().getProperty(PropertyEnum.CERT_PASSWORD);



    try {
      initKeyStore();
    } catch (ServiceException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    RequestPacket packet = new RequestPacket(ManagerProvider.property().getProperty(PropertyEnum.PORTAL_URL, false), JSON.toJson(convertKeyProvisionedPacketEntity()));
    ResponsePacket responseEntity = ManagerProvider.network().request(packet);
    if (responseEntity != null) {
      LOG.info("Key Provisioned Response {} ", responseEntity);

    } else {
      LOG.info("Key Provisioned Response is null or ERROR ");
    }



    // System.out.println("PUBLIC KEY : " + getPubKeyStr(certName) );
    // System.out.println("PRIVATE KEY : " + getPrivKeyStr(certName) );


    setPrepared();
    LOG.info("{} Init Complete.", this.getClass());
  }

  @Override
  public void initializeOnThread(InitialEvent event) {
    // TODO Auto-generated method stub

  }

  @Override
  public void initKeyStore() throws ServiceException {
    try {
      keyStore = KeyStore.getInstance("JKS");


      if (isKeyStore()) {
        loadKeyStore(keyStoreLocation);
      } else {

        // Not exist KeyStore
        createKeyStore(keyStoreLocation);
      }

      Entry keyEntry = null;
      if (keyStore.getCertificate(certName) != null) {
        keyEntry = getKeyEntry(certName);
      } else {
        // Key 생성
        KeyPair keyPair = ManagerProvider.crypto().generateRSA();
        // 인증서 생성 후 저장
        keyEntry = createCert(keyPair, true);
      }
    } catch (KeyStoreException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }



  @Override
  public Entry createCert(KeyPair keyPair, boolean isSaveKeyStore) {
    try {
      Calendar start = Calendar.getInstance();
      Calendar expiry = Calendar.getInstance();
      expiry.add(Calendar.YEAR, 1);
      X500Name name = new X500Name(issuerDn);
      X509v3CertificateBuilder certificateBuilder = new X509v3CertificateBuilder(name, BigInteger.ONE,
          start.getTime(), expiry.getTime(), name, SubjectPublicKeyInfo.getInstance(keyPair.getPublic().getEncoded()));
      ContentSigner signer;

      signer = new JcaContentSignerBuilder("SHA256WithRSA").setProvider(new BouncyCastleProvider()).build(keyPair.getPrivate());

      X509CertificateHolder holder = certificateBuilder.build(signer);
      Certificate cert = new JcaX509CertificateConverter().setProvider(new BouncyCastleProvider()).getCertificate(holder);

      if (isSaveKeyStore)
        saveCertInKeyStore(certName, keyPair.getPrivate(), cert);

    } catch (OperatorCreationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (CertificateException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return getKeyEntry(certName);
  }

  @Override
  public String getPubKeyStr(String certAlias) {
    try {
      if (isKeyStore())
        return new Base64(true).encodeAsString(keyStore.getCertificate(certAlias).getPublicKey().getEncoded());
    } catch (KeyStoreException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      new ServiceException("Not found Certification in keystore.", e);
    }
    return null;
  }

  @Override
  public String getPrivKeyStr(String certAlias) {
    try {
      if (isKeyStore())
        try {
          return new Base64(true).encodeToString(keyStore.getKey(certAlias, certPwd.toCharArray()).getEncoded());
        } catch (UnrecoverableKeyException | NoSuchAlgorithmException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
    } catch (KeyStoreException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      new ServiceException("Not found Certification in keystore.", e);
    }
    return null;
  }



  private Entry getKeyEntry(String entryAlias) {
    Entry entry = null;
    try {
      entry = keyStore.getEntry(entryAlias, new PasswordProtection(certPwd.toCharArray()));
    } catch (NoSuchAlgorithmException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (UnrecoverableEntryException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (KeyStoreException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return entry;
  }



  public static KeyStore createKeyStore() throws Exception {
    File file = new File(keyStoreLocation);
    KeyStore keyStore = KeyStore.getInstance("JKS");
    if (file.exists()) {
      // if exists, load
      keyStore.load(new FileInputStream(file), certPwd.toCharArray());
    } else {
      // if not exists, create
      keyStore.load(null, null);
      keyStore.store(new FileOutputStream(file), certPwd.toCharArray());
    }
    return keyStore;
  }



  private void createKeyStore(String path) {
    File file = new File(path);
    try {
      keyStore.load(null, null);
      keyStore.store(new FileOutputStream(file), certPwd.toCharArray());
    } catch (NoSuchAlgorithmException | CertificateException | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (KeyStoreException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }


  private void saveCertInKeyStore(String certAlias, PrivateKey privKey, Certificate cert) {
    Entry entry = new PrivateKeyEntry(privKey, new Certificate[] { cert });
    try {
      keyStore.setEntry(certAlias, entry, new PasswordProtection(certPwd.toCharArray()));
      keyStore.store(new FileOutputStream(new File(keyStoreLocation)), certPwd.toCharArray());
      System.out.println(entry);
    } catch (KeyStoreException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (CertificateException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }


  private void loadKeyStore(String path) {
    try {
      File file = new File(path);
      if (isKeyStore())
        keyStore.load(new FileInputStream(file), certPwd.toCharArray());
    } catch (NoSuchAlgorithmException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (CertificateException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      throw new ServiceException("Not found Key Store file", e);
    }
  }

  private boolean isKeyStore() {
    File file = new File(keyStoreLocation);
    if (file.exists())
      return true;
    else
      return false;
  }

  private String encodeBase64(byte[] key) {
    return new Base64(true).encodeToString(key);
  }


  private RequestPacketEntity convertKeyProvisionedPacketEntity() {
    RequestPacketEntity requestEntity = new RequestPacketEntity();
    requestEntity.setCmd(Constants.MANAGER_TYPE_KEYPROVISION);

    RequestKeyProvisionArgsEntity argsEntity = new RequestKeyProvisionArgsEntity();
    argsEntity.setOrgId(orgId);
    argsEntity.setPubKey(this.getPubKeyStr(certName));
    requestEntity.setArgs(argsEntity);

    return requestEntity;
  }


}
