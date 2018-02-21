package io.rezoome.job;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

import io.rezoome.database.DbaseManagerImpl;
import io.rezoome.vo.JobImpl;

public class JobRunnerImpl extends AbstractJobRunner implements Callable<Integer> {


  public JobRunnerImpl(BlockingQueue queue) {
    super(queue);
    // TODO Auto-generated constructor stub
  }

  @Override
  public Integer call() throws Exception {
    // TODO Auto-generated method stub

    JobImpl job = (JobImpl) super.queue.take();



    DbaseManagerImpl.getInstance().getConnecter();
    // Converter
    DbaseManagerImpl.getInstance().getConverter();

    // Rest API gogo ( Mr.park)

    return null;
  }


}
