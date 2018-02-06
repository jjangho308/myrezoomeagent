package JobManager;

import java.util.concurrent.BlockingQueue;

import MessageManager.JobImpl;

public interface JobManager {
  
  public void setJobManagerImpl(int queueSize, int corePoolSize, int maximumPoolSize, int keepAliveTime );
  public BlockingQueue getJobQueue();  
  public void putMessageToJobQueue(JobImpl job);
  public void prepare();
  public void run();
}
