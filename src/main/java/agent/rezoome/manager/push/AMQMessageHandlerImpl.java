package agent.rezoome.manager.push;

import agent.rezoome.manager.provider.ManagerProvider;
import agent.rezoome.manager.pushcommand.entity.PushCommandEntity;
import io.rezoome.lib.json.JSON;

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
