package io.rezoome.manager.crypto;

import io.rezoome.entity.RzmRsltEntity;
import io.rezoome.manager.Manager;
import io.rezoome.manager.mapper.MapperEntity;

public interface CryptoManager extends Manager {
  public String hash(MapperEntity entity);
  public String encryptRSA(String clientKey, String agentKey);
  public String encryptAES(MapperEntity entity);
}
