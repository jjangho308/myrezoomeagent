package io.rezoome.job;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

import io.rezoome.vo.JobImpl;

public abstract class AbstractJobRunner implements JobRunner, Callable<Integer> {

  BlockingQueue queue = null;

  public AbstractJobRunner() {

  }

  public AbstractJobRunner(BlockingQueue queue) {
    this.queue = queue;
  }

  protected void setJobQueue(BlockingQueue queue) {
    // TODO Auto-generated method stub
    this.queue = queue;
  }

  protected JobImpl takeJob(BlockingQueue queue) throws Exception {
    // TODO Auto-generated method stub
    return (JobImpl) this.queue.take();
  }

  @Override
  public abstract Integer call() throws Exception;

}
