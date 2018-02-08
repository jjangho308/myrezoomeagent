package agentRuntime;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import io.rezoome.Database.DbaseManagerImpl;
import io.rezoome.Job.AbstractJobRunner;
import io.rezoome.Job.JobManagerImpl;
import io.rezoome.Job.JobRunnerImpl;
import io.rezoome.Message.MessageManagerImpl;

public class main {

	public static void main(String[] args) {
		
	  
	  
	  // Under Info will get Property File
    String dbDriverName = "";
    String host= "";
    String port= "";
    String dbName= "";
    String userName= "";
    String userPwd= "";
    String maxPoolSize= "";
    
    
	  
    //dbMgr.createConnection(dbDriverName, host, port, dbName, userName, userPwd, maxPoolSize);
	  
    
    ArrayList<String> dbModuleNameArr = new ArrayList<String>();
	  String dbModuleName = "Inha Graduate Database";
	  dbModuleNameArr.add(dbModuleName);
	  DbaseManagerImpl dbMgr = new DbaseManagerImpl();
	  dbMgr.createAll(dbModuleName, dbDriverName, host, port, dbName, userName, userPwd, maxPoolSize);
    
	 
	  
	  
	  // Default Job Thread
	  JobManagerImpl.getInstance().setJobManagerImpl(50, 2, 10, 60);
	  JobManagerImpl.getInstance().run();
	  
	  
	  // Customize Job Thread
	  JobManagerImpl.getInstance().setJobManagerImpl(50, 2, 10, 60);
	  JobManagerImpl.getInstance().setJobRunner(new AbstractJobRunner(){
      @Override
      public Integer call() throws Exception {
        // TODO Auto-generated method stub
        
        
        return null;
      }
	  });
	  JobManagerImpl.getInstance().run();
	  
	  	  
	  // Message Manager
	  MessageManagerImpl.getInstance().run();
	  
	  
	   
	  
	  
	}

}
/*



class Producer implements Runnable {

	private BlockingQueue queue;
	
	public Producer(BlockingQueue queue) {
		this.queue = queue;
	}
	
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
				
				Date d = new Date();
				String msg = "메시지"+d.toString();
				
				queue.add(msg);
				
				System.out.println("메시지를 생성합니다. [" + queue.size() + "]");
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}



class Consumer implements Runnable {

	private BlockingQueue queue;
	
	public Consumer(BlockingQueue queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
				
				String msg = (String) queue.take();
				
				System.out.println("메시지를 꺼냅니다. : " + msg + "[" + queue.size() + "]");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}
	}

}

*/