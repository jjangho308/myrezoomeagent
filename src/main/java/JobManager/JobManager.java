package JobManager;

import java.util.concurrent.BlockingQueue;

public interface JobManager {
  
  public BlockingQueue getJobQueue();
  
  public void runJobThreadPool();
}
