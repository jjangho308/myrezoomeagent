package JobManager;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

import MessageManager.JobImpl;

public class JobRunnerImpl implements JobRunner , Callable<Integer>{

  BlockingQueue queue = null;
  public JobRunnerImpl(BlockingQueue queue){
    this.queue = queue;
  }
  
  @Override
  public Integer call() throws Exception {
    // TODO Auto-generated method stub
    
    // Job을 받아서..
    JobImpl job = (JobImpl)this.queue.take(); 
    
    
    // DB Converter
    
    
    // DB Driver
    
    
    return null;
  }

  
}
