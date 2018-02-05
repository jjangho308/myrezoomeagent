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
  //������ �ּ� Thread��.
  private int corePoolSize;
  //�ִ� Thread ������
  private int maximumPoolSize;
  //���� Ǯ�� corePoolSize �� ������ ���� thread�� �ִ� ���, �ʰ��� ��ŭ�� thread��, IDLE ���°� �Ǿ� �ִ� �Ⱓ�� keepAliveTime �� ������(��) �����մϴ�
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