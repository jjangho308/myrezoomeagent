package MessageManager;

import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public interface MessageManager {
 
  // Amazon Queue Message Recieve Agent 
  public void initAmqAgent();  
  public void runAmqRecieve(int timeoutTime) throws Exception;
  public void endRecive() throws Exception;
  
  // Message Convert to Job
  public void convertToJobMessage();
  
  // Message transfer to Job Queue
  public void putMessageToJobQueue(Object job);
  
  
}


