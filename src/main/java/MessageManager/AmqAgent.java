package MessageManager;

public interface AmqAgent {
  // Amazon Queue Message Recieve Agent 
  public void recieveMsg(int timeoutTime) throws Exception;
  public void endRecive() throws Exception;
  // Message transfer to Job Queue
  public void putMessageToJobQueue(Object job);
}
