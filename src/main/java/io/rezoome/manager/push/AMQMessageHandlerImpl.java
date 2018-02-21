package io.rezoome.manager.push;

import io.rezoome.manager.pushcommand.entity.PushCommandEntity;

public class AMQMessageHandlerImpl implements AMQMessageHandler {
  private static class Singleton {
    private static final AMQMessageHandler instance = new AMQMessageHandlerImpl();
  }

  public static AMQMessageHandler getInstance() {
    return Singleton.instance;
  }
  @Override
  public boolean handleMessage(AMQMessageEntity msg) {
    // TODO Auto-generated method stub
    
    // AMQMessageEntity -> PushCommandEntituy 
    
    return true;
  }

}
