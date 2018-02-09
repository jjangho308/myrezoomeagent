package io.rezoome.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class JobManager {

  private static final int POOL_SIZE = 10;
  private static ExecutorService executor;

  private JobManager() {}

  private static class Singleton {
    private static final JobManager instance = new JobManager();
  }

  public static JobManager getInstance() {

    executor = Executors.newFixedThreadPool(POOL_SIZE);
    return Singleton.instance;
  }

  public void runThread() {

    Callable<Map<String, Object>> callable = new AsyncJob();

    // 비동기로 작업 시작
    Future<Map<String, Object>> future = executor.submit(callable);

    Map<String, Object> response = new HashMap<String, Object>();

    try {
      response = future.get();
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void destroyThread() {
    executor.shutdown();
  }

}
