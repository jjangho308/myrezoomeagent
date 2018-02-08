package io.rezoome.Message;

public class MessageWrapperImpl implements MessageWrapper {

  MessageWrapperImpl(){ }
  
  private static class Singleton {
    private static final MessageWrapperImpl instance = new MessageWrapperImpl();
  }
  
  public static MessageWrapperImpl getInstance () {
    System.out.println("create instance");
    return Singleton.instance;
  }
  
  
  @Override
  public Object convertMessageToJob(String msg) {
    // TODO Auto-generated method stub
    
    // Convert msg to Job
    
    return new Object();
  }

  
}
