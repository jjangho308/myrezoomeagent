package io.rezoome.Job;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

import io.rezoome.Vo.JobImpl;

public abstract class AbstractJobRunner implements JobRunner, Callable<Integer>{

  BlockingQueue queue = null;
  public AbstractJobRunner(){
    
  }
  public AbstractJobRunner(BlockingQueue queue){
    this.queue = queue; 
  }
  
  protected void setJobQueue(BlockingQueue queue) {
    // TODO Auto-generated method stub
    this.queue = queue;
  }
  
  protected JobImpl takeJob(BlockingQueue queue) throws Exception{
    // TODO Auto-generated method stub
    return (JobImpl)this.queue.take();
  }
  
  public abstract Integer call() throws Exception;

}
