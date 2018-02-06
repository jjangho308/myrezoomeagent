package JobManager;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

import DatabaseManager.DatabaseConnecterImpl;
import DatabaseManager.DatabaseConverterImpl;
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
    DatabaseConverterImpl dbConverter = new DatabaseConverterImpl(this.contertJobToRzmVO(job));
    
    // DB Driver
    DatabaseConnecterImpl dbConnecter = new DatabaseConnecterImpl();
    
    // Under Info will get Property File
    String dbDriverName = "";
    String host= "";
    String port= "";
    String dbName= "";
    String userName= "";
    String userPwd= "";
    String maxPoolSize= "";
    
    dbConnecter.setConnectInfo(dbDriverName, host, port, dbName, userName, userPwd, maxPoolSize);
    dbConnecter.connect();
    //dbConnecter
    
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
