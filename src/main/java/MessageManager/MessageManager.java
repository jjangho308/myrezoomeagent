package MessageManager;

import java.util.concurrent.BlockingQueue;

import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public interface MessageManager {
  public void prepare(BlockingQueue queue);
  public void run();
  
}


