package io.rezoome.Job;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.rezoome.Vo.JobImpl;

public class JobManagerImpl implements JobManager{

  private BlockingQueue queue = null;
  private ThreadPoolExecutor threadPool = null;
  private JobRunnerImpl jobRunner = null;
  
  private int queueSize = 10;
  //실행할 최소 Thread수.
  private int corePoolSize = 2;
  //최대 Thread 지원수
  private int maximumPoolSize = 10;
  //현재 풀에 corePoolSize 의 수보다 많은 thread가 있는 경우, 초과한 만큼의 thread는, IDLE 상태가 되어 있는 기간이 keepAliveTime 를 넘으면(자) 종료합니다
  private int keepAliveTime = 60;
  
  private static class Singleton {
    private static final JobManagerImpl instance = new JobManagerImpl();
  }
  
  public static JobManagerImpl getInstance () {
    System.out.println("create instance");
    return Singleton.instance;
  }
  
  @Override
  public void setJobManagerImpl(int queueSize, int corePoolSize, int maximumPoolSize, int keepAliveTime ){
    this.queueSize = queueSize;
    this.corePoolSize = corePoolSize;
    this.maximumPoolSize = maximumPoolSize;
    this.keepAliveTime = keepAliveTime;
  }
  
  @Override
  public void setJobRunner(AbstractJobRunner jobRunner){
    this.jobRunner = (JobRunnerImpl) jobRunner;    
  }
  
  @Override
  public BlockingQueue getJobQueue() {
    // TODO Auto-generated method stub
    return queue;
  }
  
  @Override  
  public void prepare(){
    queue = new LinkedBlockingQueue(50);    
    threadPool = new ThreadPoolExecutor( this.queueSize, this.corePoolSize, this.maximumPoolSize, TimeUnit.SECONDS, queue);
  }
  @Override  
  public void run(){
 // TODO Auto-generated method stub
    if( this.jobRunner == null){
      this.jobRunner = new JobRunnerImpl(queue);
    }else{
      this.jobRunner.setJobQueue(queue);
    }
    threadPool.execute( (Runnable) this.jobRunner);
  }

  @Override
  public void putMessageToJobQueue(JobImpl job) {    
    // TODO Auto-generated method stub
    this.queue.add(job); 
    
  }
  
}