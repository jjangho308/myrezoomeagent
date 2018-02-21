package io.rezoome.manager.push;

import io.rezoome.lib.json.JSON;
import io.rezoome.manager.provider.ManagerProvider;
import io.rezoome.manager.pushcommand.entity.PushCommandEntity;

public class AMQMessageHandlerImpl implements AMQMessageHandler  {
  private static class Singleton {
    private static final AMQMessageHandler instance = new AMQMessageHandlerImpl();
  }

  public static AMQMessageHandler getInstance() {
    return Singleton.instance;
  }
  @Override
  public boolean handleMessage(AMQMessageEntity msg) {
    // TODO Auto-generated method stub    
    try{
      PushCommandEntity pcEntity = JSON.fromJson(msg.toString(), PushCommandEntity.class);
      ManagerProvider.pushcommand().invokeCommand(pcEntity);
      return true;
    } catch(Exception e){
      e.printStackTrace();
      return false;
    }
  }

}
