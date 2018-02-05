package MessageManager;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;


public class AmqAgentImpl implements AmqAgent , Callable<Integer> , MessageListener {
  
  private ActiveMQConnectionFactory connectionFactory = null;
  private Connection consumerConnection = null;
  private Session consumerSession = null;
  private Destination consumerDestination = null;
  private MessageConsumer consumer = null;
  
  private String amqAddr = "";
  private String uName = "";  
  private String uPwd = "";
  private int consumerTimeout = 1000;
  private BlockingQueue queue = null;
  
  
  public AmqAgentImpl(BlockingQueue queue){
    this.queue = queue;
  }
    
  @Override
  public void recieveMsg(int timeoutTime) throws Exception {
    // TODO Auto-generated method stub
  //"ssl://b-1234a5b6-78cd-901e-2fgh-3i45j6k178l9-1.mq.us-east-1.amazonaws.com:61617"
    connectionFactory = new ActiveMQConnectionFactory(this.amqAddr);
    
    // Specify the username and password.
      connectionFactory.setUserName(this.uName);
      connectionFactory.setPassword(this.uPwd);

    // TODO Auto-generated method stub
    // Establish a connection for the consumer.
    // Note: Consumers should not use PooledConnectionFactory.
    consumerConnection = connectionFactory.createConnection();
    consumerConnection.start();
    
    // TODO Auto-generated method stub
    // Create a session.
    Session consumerSession = consumerConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);

    // Create a queue named "MyQueue".
    Destination consumerDestination = consumerSession.createQueue("MyQueue");
// TODO Auto-generated method stub
 // Create a connection factory.
    
   // Create a message consumer from the session to the queue.
   MessageConsumer consumer = consumerSession.createConsumer(consumerDestination);
   consumer.setMessageListener(this);
   
   /*
   // Begin to wait for messages.
   Message consumerMessage = (Message) consumer.receive(this.consumerTimeout);

   // Receive the message when it arrives.
   TextMessage consumerTextMessage = (TextMessage) consumerMessage;   
   System.out.println("Message received: " + consumerTextMessage.getText());*/
   
  }

  @Override
  public void endRecive() throws Exception {
    // TODO Auto-generated method stub
    // Clean up the consumer.
    consumer.close();
    consumerSession.close();
    consumerConnection.close();   
  }
  @Override
  public void putMessageToJobQueue(Object job) {
    // TODO Auto-generated method stub
    this.queue.add(job);
  }

  
  @Override
  public Integer call() throws Exception {
    // TODO Auto-generated method stub
    
    return null;
  }
  
  @Override
  public void onMessage(Message consumerMessage) {
    try {
      // Receive the message when it arrives.
      TextMessage consumerTextMessage = (TextMessage) consumerMessage;   
      System.out.println("Message received: " + consumerTextMessage.getText());
      
      JobImpl job = new JobImpl();
      
      job.setJob((JobImpl)MessageWrapperImpl.getInstance().convertMessageToJob(consumerTextMessage.getText()));
      System.out.println("Message received: " + consumerTextMessage.getText());
      
      putMessageToJobQueue(job);
      
      
    
      System.out.println(
          "Consumer " + Thread.currentThread().getName() + " received message: " + consumerTextMessage.getText());
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }


  
}
