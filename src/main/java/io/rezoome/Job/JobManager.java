package io.rezoome.Job;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

import io.rezoome.Vo.JobImpl;

public interface JobManager {
  
  public void setJobManagerImpl(int queueSize, int corePoolSize, int maximumPoolSize, int keepAliveTime);
  public void setJobRunner(AbstractJobRunner jobRunner);
  public BlockingQueue getJobQueue();  
  public void putMessageToJobQueue(JobImpl job);
  public void prepare();
  public void run();
}
