package io.rezoome.manager.amq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import io.rezoome.lib.json.JSON;
import io.rezoome.manager.provider.ManagerProvider;
import io.rezoome.manager.pushcommand.entity.PushCommandEntity;
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
      PushCommandEntity pcEntity = JSON.fromJson(msg.getMessage(), SearchCommandEntity.class);
      
      /*{"mid":"msgid-0001","token":"Rm9vYmFyIQ==Rm9vYmFyIQ==Rm9vYmFyIQ==","cmd":"Search",
        "args":{"username":"CH","birth":"1987-03-08","gender":1,"phone":"010-0000-0000","from":"2016-10-10","to":"2017-02-28","pkey":"asdlf;kjasl;dfkjasl;dfkjjjjeic==",
        "orgs":["\"01\"","\
        "02\""]}}
      */
      System.out.println("YYY");
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
