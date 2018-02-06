package JobManager;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

import DatabaseManager.DbaseConnecterFactory;
import DatabaseManager.DbaseConnecterImpl;
import DatabaseManager.DbaseConverterImpl;
import DatabaseManager.DbaseManagerImpl;
import DatabaseManager.MySQLConnecter;
import MessageManager.JobImpl;
import vo.RzmVOImpl;

public class JobRunnerImpl implements JobRunner , Callable<Integer>{

  BlockingQueue queue = null;
  public JobRunnerImpl(BlockingQueue queue){
    this.queue = queue;
  }
  
  @Override
  public Integer call() throws Exception {
    // TODO Auto-generated method stub
    
    // Job을 받아서..
    JobImpl job = (JobImpl)this.queue.take(); 
  
    // DB Converter
    DbaseConverterImpl dbConverter = new DbaseConverterImpl(this.contertJobToRzmVO(job));
  
    // Rest API gogo ( Mr.park)
    
    return null;
  }

  @Override
  public RzmVOImpl contertJobToRzmVO(JobImpl job) {
    // TODO Auto-generated method stub
    RzmVOImpl rzmVo = new RzmVOImpl();
        
    return rzmVo;
  }

  
}
