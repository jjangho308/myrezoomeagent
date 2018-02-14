package io.rezoome.message;

import java.util.concurrent.BlockingQueue;



public class MessageManagerImpl implements MessageManager {


  private AmqAgentImpl amqAgentThread = null;
  private MessageWrapperImpl msgWpr = null;

  private static class Singleton {
    private static final MessageManagerImpl instance = new MessageManagerImpl();
  }

  public static MessageManagerImpl getInstance() {
    System.out.println("create instance");
    return Singleton.instance;
  }

  public MessageManagerImpl() {
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
