package io.rezoome.manager.keyprovision;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStore.Entry;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.UnrecoverableEntryException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.x500.X500Principal;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.jce.X509KeyUsage;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DefaultSignatureAlgorithmIdentifierFinder;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.bc.BcRSAContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.rezoome.constants.Constants;
import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.core.annotation.ManagerType;
import io.rezoome.exception.ServiceException;
import io.rezoome.manager.AbstractManager;
import io.rezoome.manager.property.PropertyEnum;
import io.rezoome.manager.provider.ManagerProvider;


@ManagerType(value = Constants.MANAGER_TYPE_KEYPROVISION, initPriority = 10)
public class KeyProvisionManagerImpl extends AbstractManager implements KeyProvisionManager {

  private static String keyStoreLocation;
  private static String certName;
  private static String issuerDn;
  private static String certPwd;
  
  
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
    certName = ManagerProvider.property().getProperty(PropertyEnum.CERT_NAME, true);
    issuerDn = ManagerProvider.property().getProperty(PropertyEnum.ISSUER_DN, true);
    certPwd = ManagerProvider.property().getProperty(PropertyEnum.CERT_PASSWORD, true);
    
    try {
      initKeyStore();     
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    setPrepared();
    LOG.info("{} Init Complete.", this.getClass());
  }

  @Override
  public void initializeOnThread(InitialEvent event) {
    // TODO Auto-generated method stub

  }

  @Override
  public void initKeyStore() throws Exception {
    keyStore = KeyStore.getInstance("JKS");
    
    if (isKeyStore()) {
      loadKeyStore(keyStoreLocation);
    } else {
      
      // Not exist KeyStore
      createKeyStore(keyStoreLocation);
    }
    
    Entry keyEntry = null;
    if(keyStore.getCertificate(certName) != null){
      keyEntry = getKeyEntry(certName);
    }else{
      // Key 생성
      KeyPair keyPair = genRSAKeyPair();      
      // 인증서 생성 후 저장
      keyEntry = createCert(keyPair, true);      
    }
    
  }



  public KeyPair genRSAKeyPair() throws NoSuchAlgorithmException {
    KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
    keyGen.initialize(2048);
    KeyPair keyPair = keyGen.generateKeyPair();

    System.out.println("-----BEGIN PRIVATE KEY-----");
    System.out.println(Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()));
    System.out.println("-----END PRIVATE KEY-----");
    System.out.println("-----BEGIN PUBLIC KEY-----");
    System.out.println(Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
    System.out.println("-----END PUBLIC KEY-----");
    
    return keyPair;
  }

  public Entry createCert(KeyPair keyPair, boolean isSaveKeyStore) throws CertificateException, OperatorCreationException, KeyStoreException, NoSuchAlgorithmException, UnrecoverableEntryException, IOException {

    Calendar start = Calendar.getInstance();
    Calendar expiry = Calendar.getInstance();
    expiry.add(Calendar.YEAR, 1);
    X500Name name = new X500Name(issuerDn);
    X509v3CertificateBuilder certificateBuilder = new X509v3CertificateBuilder(name, BigInteger.ONE,
        start.getTime(), expiry.getTime(), name, SubjectPublicKeyInfo.getInstance(keyPair.getPublic().getEncoded()));
    ContentSigner signer = new JcaContentSignerBuilder("SHA256WithRSA").setProvider(new BouncyCastleProvider()).build(keyPair.getPrivate());
    X509CertificateHolder holder = certificateBuilder.build(signer);
    Certificate cert = new JcaX509CertificateConverter().setProvider(new BouncyCastleProvider()).getCertificate(holder);

    if (isSaveKeyStore)
      saveCertInKeyStore(certName, keyPair.getPrivate(), cert);
    return getKeyEntry(certName);
  }
  

  public String getPubKeyStr(String certAlias) {
    try {
      if (isKeyStore())
        return Base64.getEncoder().encodeToString(keyStore.getCertificate(certAlias).getPublicKey().getEncoded());
    } catch (KeyStoreException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      new ServiceException("Not found Certification in keystore.", e);
    }
    return  null;    
  }
  
  public String getPrivKeyStr(String certAlias){
    try {
      if (isKeyStore())
        try {
          return Base64.getEncoder().encodeToString(keyStore.getKey(certAlias, certPwd.toCharArray()).getEncoded());
        } catch (UnrecoverableKeyException | NoSuchAlgorithmException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
    } catch (KeyStoreException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      new ServiceException("Not found Certification in keystore.", e);
    }
    return  null;    
  }
  
  

  private Entry getKeyEntry(String entryAlias) throws IOException, NoSuchAlgorithmException, UnrecoverableEntryException, KeyStoreException {
    Entry entry = keyStore.getEntry(entryAlias, new PasswordProtection(certPwd.toCharArray()));
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


  
  private void createKeyStore(String path) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, FileNotFoundException, IOException {
    File file = new File(path);
    keyStore.load(null, null);
    keyStore.store(new FileOutputStream(file), certPwd.toCharArray());    
  }


  private void saveCertInKeyStore(String certAlias, PrivateKey privKey, Certificate cert) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, FileNotFoundException, IOException {
    Entry entry = new PrivateKeyEntry(privKey, new Certificate[] { cert });
    keyStore.setEntry(certAlias, entry, new PasswordProtection(certPwd.toCharArray()));
    keyStore.store(new FileOutputStream(new File(keyStoreLocation)), certPwd.toCharArray());
    System.out.println(entry);
    
  }

  
  private void loadKeyStore(String path) throws NoSuchAlgorithmException, CertificateException, FileNotFoundException, IOException{
    File file = new File(path);
    if (isKeyStore()) keyStore.load(new FileInputStream(file), certPwd.toCharArray());
  }

  private boolean isKeyStore() {
    File file = new File(keyStoreLocation);
    if (file.exists())
      return true;
    else
      return false;
  }

  private String encodeBase64(byte[] key) {
    return Base64.getEncoder().encodeToString(key);
  }


}
