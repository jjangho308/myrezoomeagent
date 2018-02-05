package agent;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import JobManager.JobManagerImpl;
import MessageManager.MessageManagerImpl;

public class main {

	public static void main(String[] args) {
		
	  
	  JobManagerImpl a = new JobManagerImpl();	  
	  MessageManagerImpl b = new MessageManagerImpl(a.getJobQueue());
	  
	 
	  try {
	    
	    a.call();
      b.call();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
	  
	  
	  
	}

}




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
				String msg = "�޽���"+d.toString();
				
				queue.add(msg);
				
				System.out.println("�޽����� �����մϴ�. [" + queue.size() + "]");
				
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
				
				System.out.println("�޽����� �����ϴ�. : " + msg + "[" + queue.size() + "]");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}
	}

}

