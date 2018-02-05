package JobManager;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class JobManagerImpl implements JobManager{

  private BlockingQueue queue = null;
  private ThreadPoolExecutor threadPool = null;

  private int queueSize;
  //실행할 최소 Thread수.
  private int corePoolSize;
  //최대 Thread 지원수
  private int maximumPoolSize;
  //현재 풀에 corePoolSize 의 수보다 많은 thread가 있는 경우, 초과한 만큼의 thread는, IDLE 상태가 되어 있는 기간이 keepAliveTime 를 넘으면(자) 종료합니다
  private int keepAliveTime;
  
  
  public JobManagerImpl(int queueSize, int corePoolSize, int maximumPoolSize, int keepAliveTime ){
    this.queueSize = queueSize;
    this.corePoolSize = corePoolSize;
    this.maximumPoolSize = maximumPoolSize;
    this.keepAliveTime = keepAliveTime;
  }
  
  @Override
  public BlockingQueue getJobQueue() {
    // TODO Auto-generated method stub
    return queue;
  }
  
  @Override  
  public void prepare(){
    queue = new LinkedBlockingQueue(50);    
    threadPool = new ThreadPoolExecutor( 2, 4, 60, TimeUnit.SECONDS, queue);
  }
  @Override  
  public void run(){
 // TODO Auto-generated method stub
    threadPool.execute((Runnable) new JobRunnerImpl());
  }
  
}