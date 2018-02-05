package MessageManager;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;



public class MessageManagerImpl implements MessageManager {

  
  private AmqAgentImpl amqAgentThread = null;
  
  private BlockingQueue queue = null;  
  private MessageWrapperImpl msgWpr = null;
  
  public MessageManagerImpl(BlockingQueue queue){
    
    this.queue = queue;
    msgWpr = new MessageWrapperImpl();
    
 
	}

  @Override
  public void prepare(BlockingQueue queue) {
    // TODO Auto-generated method stub
    amqAgentThread = new AmqAgentImpl(queue);
  }

  @Override
  public void run() {
    // TODO Auto-generated method stub
    try {
      amqAgentThread.call();
      
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  

  
	
 
	
}

