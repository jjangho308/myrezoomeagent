package io.rezoome.core.runtime;


import io.rezoome.core.ServiceInitializer;
import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.manager.amq.AMQMessageEntity;
import io.rezoome.manager.amq.AMQMessageHandlerImpl;

public class Runtime {

  public static void main(String[] args) {
    System.out.println("Runtime");
    initailize();
  }

  

  private static void initailize() {
    InitialEvent event = InitialEvent.RUNTIME;
    ServiceInitializer.initialize(event);
    
    AMQMessageEntity msg = new AMQMessageEntity("asdkfjlhsd");
	AMQMessageHandlerImpl.getInstance().handleMessage(msg);
  }
}
