package JobManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

import DatabaseManager.DbaseConnecter;
import DatabaseManager.DbaseConnecterFactory;
import DatabaseManager.DbaseConnecterImpl;
import DatabaseManager.DbaseConverter;
import DatabaseManager.DbaseConverterImpl;
import DatabaseManager.DbaseManagerImpl;
import MessageManager.JobImpl;
import vo.RzmVOImpl;

public class JobRunnerImpl implements JobRunner , Callable<Integer>{

  BlockingQueue queue = null;
  ArrayList<String> dbModuleNameArr = null;
  
  public JobRunnerImpl(BlockingQueue queue, ArrayList<String> dbModuleNameArr){
    this.queue = queue;
    this.dbModuleNameArr = dbModuleNameArr;
  }
  
  @Override
  public Integer call() throws Exception {
    // TODO Auto-generated method stub
    
    // Job을 받아서..
    JobImpl job = (JobImpl)this.queue.take(); 
    
   
  
    for(String dbModuleName :dbModuleNameArr){  
  
      // Connecter
      DbaseManagerImpl.getInstance().getConnecter(dbModuleName);
      
      // Converter
      DbaseManagerImpl.getInstance().getConverter(dbModuleName);
      
    }
    
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
