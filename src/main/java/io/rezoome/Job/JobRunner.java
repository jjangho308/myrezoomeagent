package io.rezoome.Job;

import java.util.concurrent.BlockingQueue;

import io.rezoome.Vo.JobImpl;
import io.rezoome.Vo.RzmVOImpl;

public interface JobRunner {

  //public RzmVOImpl convertJobToRzmVO(JobImpl job);
  
  public void setJobQueue(BlockingQueue queue);
}
