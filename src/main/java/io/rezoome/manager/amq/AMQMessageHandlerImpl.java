package io.rezoome.manager.amq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import io.rezoome.lib.json.JSON;
import io.rezoome.manager.provider.ManagerProvider;
import io.rezoome.manager.pushcommand.entity.PushCommandEntity;

public class AMQMessageHandlerImpl implements AMQMessageHandler , MessageListener {
  private static class Singleton {
    private static final AMQMessageHandler instance = new AMQMessageHandlerImpl();
  }

  public static AMQMessageHandler getInstance() {
    return Singleton.instance;
  }

  @Override
  public void onMessage(Message message) {
    // TODO Auto-generated method stub
    try {
      TextMessage consumerTextMessage = (TextMessage) message;      
      AMQMessageEntity amqEntity = new AMQMessageEntity(consumerTextMessage.getText());
      AMQMessageHandlerImpl.getInstance().handleMessage(amqEntity);
    } catch (NullPointerException ne) {
      // TODO Auto-generated catch block
      ne.printStackTrace();
    } catch (JMSException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public boolean handleMessage(AMQMessageEntity msg) {
    // TODO Auto-generated method stub    
    try{
      PushCommandEntity pcEntity = JSON.fromJson(msg.getMessage(), PushCommandEntity.class);
      ManagerProvider.pushcommand().invokeCommand(pcEntity);
      
    } catch(Exception e){
      e.printStackTrace();
      return false;
    }finally{
     
    }
    return true;
  }
}
