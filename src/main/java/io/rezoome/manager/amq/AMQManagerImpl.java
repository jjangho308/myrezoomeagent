package io.rezoome.manager.amq;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.manager.AbstractManager;
import io.rezoome.manager.property.PropertyEnum;
import io.rezoome.manager.provider.ManagerProvider;
/**
 * Implementation of {@link AMQManager}. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 * @author SEONGYEON
 *
 */
public class AMQManagerImpl extends AbstractManager implements AMQManager {

  private static class Singleton {
    private static final AMQManager instance = new AMQManagerImpl();
  }

  public static AMQManager getInstance() {
    return Singleton.instance;
  }
  
  private ActiveMQConnectionFactory connectionFactory;
  private Connection consumerConnection;
  private Session consumerSession;
  private Destination consumerDestination;
  private MessageConsumer consumer;
  
	@Override
	public void initialize(InitialEvent event) {
		// TODO Auto-generated method stub
	  connectionFactory = null;
	  consumerConnection = null;
	  consumerSession = null;
	  consumerDestination = null;
	  consumer = null;
	  
	  String serverHost = ManagerProvider.property().getProperty(PropertyEnum.AMAZONE_SERVER_HOST, true);
	  String queueName = ManagerProvider.property().getProperty(PropertyEnum.AMAZONE_QUEUE_NAME, true);
	  String userName = ManagerProvider.property().getProperty(PropertyEnum.AMAZONE_USER_NAME, true);
	  String userPassword = ManagerProvider.property().getProperty(PropertyEnum.AMAZONE_USER_PASSWORD, true);
	  AMQConfigEntity amqConfig = new AMQConfigEntity(queueName, serverHost, userName, userPassword);
	  
	  this.registerPush(amqConfig);
	  this.registerPushHandler();
	  System.out.println("PushManager Init Complete.");
	}

	@Override
	public void initializeOnThread(InitialEvent event) {
		// TODO Auto-generated method stub
		
	}


  @Override
  public synchronized void registerPush(AMQConfigEntity config) {
    // TODO Auto-generated method stub
    try {
 
      connectionFactory = new ActiveMQConnectionFactory(config.getServerHost());      
      connectionFactory.setUserName(config.getUserName());
      connectionFactory.setPassword(config.getUserPassword());

      consumerConnection = connectionFactory.createConnection();
      consumerConnection.start();
      
      consumerSession = consumerConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
      // Create a queue named "MyQueue".
      consumerDestination = consumerSession.createQueue(config.getQueueName());
      // Create a message consumer from the session to the queue.
      consumer = consumerSession.createConsumer(consumerDestination);
    } catch (JMSException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  @Override
  public void unregisterPush() {
    // TODO Auto-generated method stub
    try {
      consumer.close();
      consumerSession.close();
      consumerConnection.close();
    } catch (NullPointerException ne){
      ne.printStackTrace();
    } catch (JMSException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
   
  }

  @Override
  public void registerPushHandler() {
    // TODO Auto-generated method stub
    try {
      consumer.setMessageListener((MessageListener) AMQMessageHandlerImpl.getInstance());
    } catch (NullPointerException ne){
      ne.printStackTrace();
    } catch (JMSException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } 
  }

}
