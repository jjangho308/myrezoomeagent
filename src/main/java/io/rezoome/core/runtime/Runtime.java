package io.rezoome.core.runtime;

import java.util.ArrayList;

import io.rezoome.database.DbaseManagerImpl;
import io.rezoome.job.AbstractJobRunner;
import io.rezoome.job.JobManagerImpl;
import io.rezoome.message.MessageManagerImpl;

public class Runtime {

  public static void main(String[] args) {
    initailize();
  }

  private static void initailize() {

    // JobManager jobManager = JobManager.getInstance();
    // jobManager.runThread();


    // Under Info will get Property File
    String dbDriverName = "";
    String host = "";
    String port = "";
    String dbName = "";
    String userName = "";
    String userPwd = "";
    String maxPoolSize = "";

    // dbMgr.createConnection(dbDriverName, host, port, dbName, userName, userPwd, maxPoolSize);

    ArrayList<String> dbModuleNameArr = new ArrayList<String>();
    String dbModuleName = "Inha Graduate Database";
    dbModuleNameArr.add(dbModuleName);
    DbaseManagerImpl dbMgr = new DbaseManagerImpl();
    dbMgr.createAll(dbModuleName, dbDriverName, host, port, dbName, userName, userPwd,
        maxPoolSize);

    // Default Job Thread
    JobManagerImpl.getInstance().setJobManagerImpl(50, 2, 10, 60);
    JobManagerImpl.getInstance().run();


    // Customize Job Thread
    JobManagerImpl.getInstance().setJobManagerImpl(50, 2, 10, 60);
    JobManagerImpl.getInstance().setJobRunner(new AbstractJobRunner() {
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
 * 
 * 
 * 
 * class Producer implements Runnable {
 * 
 * private BlockingQueue queue;
 * 
 * public Producer(BlockingQueue queue) { this.queue = queue; }
 * 
 * 
 * @Override public void run() { while(true) { try { Thread.sleep(1000);
 * 
 * Date d = new Date(); String msg = "�޽���"+d.toString();
 * 
 * queue.add(msg);
 * 
 * System.out.println("�޽����� �����մϴ�. [" + queue.size() + "]");
 * 
 * } catch (InterruptedException e) { e.printStackTrace(); } }
 * 
 * }
 * 
 * }
 * 
 * 
 * 
 * class Consumer implements Runnable {
 * 
 * private BlockingQueue queue;
 * 
 * public Consumer(BlockingQueue queue) { this.queue = queue; }
 * 
 * @Override public void run() { while(true) { try { Thread.sleep(1000);
 * 
 * String msg = (String) queue.take();
 * 
 * System.out.println("�޽����� �����ϴ�. : " + msg + "[" + queue.size() + "]"); } catch
 * (InterruptedException e) { e.printStackTrace(); }
 * 
 * 
 * } }
 * 
 * }
 * 
 */
