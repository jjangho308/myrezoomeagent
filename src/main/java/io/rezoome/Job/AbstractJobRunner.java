package io.rezoome.Job;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

public abstract class AbstractJobRunner implements JobRunner, Callable<Integer>{

  BlockingQueue queue = null;
  public AbstractJobRunner(){
    
  }
  public AbstractJobRunner(BlockingQueue queue){
    this.queue = queue; 
  }
  
  @Override
  public void setJobQueue(BlockingQueue queue) {
    // TODO Auto-generated method stub
    this.queue = queue;
  }
  
  
  public abstract Integer call() throws Exception;

}
