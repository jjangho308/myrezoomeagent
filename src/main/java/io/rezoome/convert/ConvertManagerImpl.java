package io.rezoome.convert;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.core.annotation.ManagerType;
import io.rezoome.core.entity.Entity;
import io.rezoome.manager.AbstractManager;
import io.rezoome.manager.pushcommand.entity.PushCommandEntity;
import io.rezoome.message.MessageManagerImpl;

/**
 * 
 * @version 1.0
 * @author SEONGYEON
 *
 */
@ManagerType(value = "")
public class ConvertManagerImpl extends AbstractManager implements ConvertManager {
  
  private static class Singleton {
    private static final ConvertManagerImpl instance = new ConvertManagerImpl();
  }

  public static ConvertManagerImpl getInstance() {
    System.out.println("create instance");
    return Singleton.instance;
  }
  
  
  @SuppressWarnings("unchecked")
  @Override
  public <T extends Entity> T convert(Entity fromEntity, Entity toEntity) {
    // TODO Auto-generated method stub
    // YSY - 18.02.20
    if (fromEntity.getClass() == toEntity.getClass()){
      return (T) fromEntity;
    }else{
      
    }
    
    return null;
    
  }


  @Override
  public void initialize(InitialEvent event) {
    // TODO Auto-generated method stub
    
  }


  @Override
  public void initializeOnThread(InitialEvent event) {
    // TODO Auto-generated method stub
    
  }
  
  private <T extends Entity> T invokeConvertJob(){
    
    return null;
  }

}
