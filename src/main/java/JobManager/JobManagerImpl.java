package JobManager;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class JobManagerImpl implements JobManager , Callable<Integer>{

  private BlockingQueue queue = null;
  private ThreadPoolExecutor threadPool = null;
  
  public JobManagerImpl(){
    queue = new LinkedBlockingQueue(50);
    threadPool = new ThreadPoolExecutor( 2, 4, 60, TimeUnit.SECONDS, queue);
  }
  

  @Override
  public void runJobThreadPool() {
    // TODO Auto-generated method stub
    threadPool.execute((Runnable) new JobRunnerImpl());
  }
  
  @Override
  public BlockingQueue getJobQueue() {
    // TODO Auto-generated method stub
    return queue;
  }

  @Override
  public Integer call() throws Exception {
    // TODO Auto-generated method stub
    threadPool.execute((Runnable) new JobRunnerImpl());
    while(true){
      
    }
    //return null;
  }

  
  
}