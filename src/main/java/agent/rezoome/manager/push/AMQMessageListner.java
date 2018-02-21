package agent.rezoome.manager.push;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class AMQMessageListner implements MessageListener {

  private static class Singleton {
    private static final AMQMessageListner instance = new AMQMessageListner();
  }

  public static AMQMessageListner getInstance() {
    return Singleton.instance;
  }
  
  @Override
  public void onMessage(Message message){
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

}
