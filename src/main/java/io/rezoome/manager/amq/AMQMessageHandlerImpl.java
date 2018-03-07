package io.rezoome.manager.amq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import io.rezoome.manager.provider.ManagerProvider;
import io.rezoome.manager.pushcommand.entity.PushCommandEntity;
import io.rezoome.manager.pushcommand.entity.search.MemberProfile;
import io.rezoome.manager.pushcommand.entity.search.SearchCommandEntity;

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
      System.out.println(consumerTextMessage.getText());
      AMQMessageEntity amqEntity = null;//new AMQMessageEntity(consumerTextMessage.getText());
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
      //PushCommandEntity pcEntity = JSON.fromJson(msg.getMessage(), SearchCommandEntity.class);
      
      // 안택수 
    	MemberProfile profile = new MemberProfile();
    	profile.setUsername("안택수");
    	profile.setBirth("1987-45");
      PushCommandEntity pcEntity = new SearchCommandEntity(profile);
      
      System.out.println(pcEntity);
      ManagerProvider.pushcommand().invokeCommand(pcEntity);
      
    } catch(RuntimeException re){
      re.printStackTrace();
      //return false; 
    } catch(Exception e){
      e.printStackTrace();
      return false;
    }finally{
     
    }
    return true;
  }
}
