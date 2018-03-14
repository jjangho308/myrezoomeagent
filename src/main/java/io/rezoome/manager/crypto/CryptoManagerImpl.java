package io.rezoome.manager.crypto;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.core.annotation.ManagerType;
import io.rezoome.manager.AbstractManager;
import io.rezoome.manager.mapper.MapperEntity;

@ManagerType(value = "Crypto")
public class CryptoManagerImpl extends AbstractManager implements CryptoManager{

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
  public String hash(MapperEntity entity) {
    // TODO Auto-generated method stub
    return entity.toString();
  }


  @Override
  public String encryptRSA(String clientKey, String agentKey) {
    // TODO Auto-generated method stub
    return agentKey + clientKey;
  }


  @Override
  public String encryptAES(MapperEntity entity) {
    // TODO Auto-generated method stub
    return entity.toString();
  }


  

}
