package JobManager;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import MessageManager.JobImpl;

public class JobManagerImpl implements JobManager{

  private BlockingQueue queue = null;
  private ThreadPoolExecutor threadPool = null;

  private int queueSize = 10;
  //������ �ּ� Thread��.
  private int corePoolSize = 2;
  //�ִ� Thread ������
  private int maximumPoolSize = 10;
  //���� Ǯ�� corePoolSize �� ������ ���� thread�� �ִ� ���, �ʰ��� ��ŭ�� thread��, IDLE ���°� �Ǿ� �ִ� �Ⱓ�� keepAliveTime �� ������(��) �����մϴ�
  private int keepAliveTime = 60;
  
  private static class Singleton {
    private static final JobManagerImpl instance = new JobManagerImpl();
  }
  
  public static JobManagerImpl getInstance () {
    System.out.println("create instance");
    return Singleton.instance;
  }
  
  public void setJobManagerImpl(int queueSize, int corePoolSize, int maximumPoolSize, int keepAliveTime ){
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
    threadPool = new ThreadPoolExecutor( this.queueSize, this.corePoolSize, this.maximumPoolSize, TimeUnit.SECONDS, queue);
  }
  @Override  
  public void run(){
 // TODO Auto-generated method stub
    threadPool.execute((Runnable) new JobRunnerImpl(this.queue));
  }

  @Override
  public void putMessageToJobQueue(JobImpl job) {    
    // TODO Auto-generated method stub
    this.queue.add(job); 
    
  }
  
}