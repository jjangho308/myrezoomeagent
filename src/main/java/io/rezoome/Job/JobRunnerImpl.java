package io.rezoome.Job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

import io.rezoome.Database.DbaseConnecter;
import io.rezoome.Database.DbaseConnecterFactory;
import io.rezoome.Database.DbaseConnecterImpl;
import io.rezoome.Database.DbaseConverter;
import io.rezoome.Database.DbaseConverterImpl;
import io.rezoome.Database.DbaseManagerImpl;
import io.rezoome.Vo.JobImpl;
import io.rezoome.Vo.RzmVOImpl;

public class JobRunnerImpl extends AbstractJobRunner implements Callable<Integer>{

  
  public JobRunnerImpl(BlockingQueue queue) {
    super(queue);
    // TODO Auto-generated constructor stub
  }

  @Override
  public Integer call() throws Exception {
    // TODO Auto-generated method stub
    
    // Job을 받아서..
    JobImpl job = (JobImpl)super.queue.take(); 
    
   
  
    
    DbaseManagerImpl.getInstance().getConnecter();     
    // Converter
    DbaseManagerImpl.getInstance().getConverter();
    
    // Rest API gogo ( Mr.park)
    
    return null;
  }

  
}
