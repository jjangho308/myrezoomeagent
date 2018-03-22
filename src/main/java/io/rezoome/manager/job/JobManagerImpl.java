package io.rezoome.manager.job;

import java.io.File;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.core.annotation.ManagerType;
import io.rezoome.core.entity.annotation.EntityType;
import io.rezoome.manager.AbstractManager;
import io.rezoome.manager.job.entity.JobAction;
import io.rezoome.manager.job.entity.JobEntity;
import io.rezoome.manager.job.iorequest.IORequestJobAction;
import io.rezoome.manager.job.iorequest.IORequestJobEntity;
import io.rezoome.manager.property.PropertyEnum;
import io.rezoome.manager.provider.ManagerProvider;
import io.rezoome.thread.WorkerThread;

/**
 * Implementation of {@link JobManager}. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
@ManagerType("Job")
public final class JobManagerImpl extends AbstractManager implements JobManager {

  private final Logger LOG = LoggerFactory.getLogger("AGENT_LOG");

  private static class Singleton {
    private static final JobManager instance = new JobManagerImpl();
  }

  public static JobManager getInstance() {
    return Singleton.instance;
  }

  private String JOB_TEMP_FILE_PATH;

  /**
   * Hide contructor. <br />
   */
  private JobManagerImpl() {}

  private ExecutorService executor;

  {
    this.actionMap = new HashMap<>();
    this.actionMap.put(IORequestJobEntity.class, new IORequestJobAction());
  }

  private final Map<Class<? extends JobEntity>, JobAction<? extends JobEntity>> actionMap;

  @Override
  public void initialize(InitialEvent event) {
    JOB_TEMP_FILE_PATH = ManagerProvider.property().getProperty(PropertyEnum.JOB_TEMP_FILE_PATH, true);

    if (false) {
      ExecutorService service = null;
      try {
        service =
            Executors.newScheduledThreadPool(Integer.parseInt(ManagerProvider.property().getProperty(PropertyEnum.THREAD_POOL_CAPAVILITY,
                false)),

                new ThreadFactory() {

                  @Override
                  public Thread newThread(Runnable r) {
                    return new WorkerThread(r);
                  }
                });
      } catch (NumberFormatException nfe) {
        nfe.printStackTrace();
        service = null;
      } finally {
        executor = service;
      }
    } else {
      /* Callable & Future model */
      executor = Executors.newFixedThreadPool(Integer.parseInt(ManagerProvider.property().getProperty(PropertyEnum.THREAD_POOL_CAPAVILITY, false)));
    }
  }

  @Override
  public void initializeOnThread(InitialEvent event) {
    // TODO Auto-generated method stub
  }

  @Override
  public void addJob(final JobEntity job) {

    if (false) {
      this.executor.execute(new Runnable() {
        @SuppressWarnings("unchecked")
        @Override
        public void run() {
          long startTime = System.currentTimeMillis();
          long lRnd = (long) (new SecureRandom().nextDouble() * 10000000000L);
          LOG.info("[{}] start[{}] : Job Thread Execution", new Object[] { Long.toString(lRnd),
              startTime });
          ((JobAction<JobEntity>) JobManagerImpl.this.actionMap.get(job.getClass())).process(job);
        }
      });
    } else {
      /* Callable & Future model */
      // executor = Executors.newFixedThreadPool(1);

      long startTime = System.currentTimeMillis();
      long lRnd = (long) (new SecureRandom().nextDouble() * 10000000000L);

      Callable<String> callable = new JobCallable(job);

      LOG.info("[{}] start[{}] : Job Thread Execution",
          new Object[] { Long.toString(lRnd), startTime });
      Future<String> future = executor.submit(callable);

      try {
        String obj = future.get();
        long endTime = System.currentTimeMillis();
        long nTime = endTime - startTime;

        LOG.info("[{}] end[{}] : Job Thread Exit {} ms",
            new Object[] { Long.toString(lRnd), endTime, Long.toString(nTime) });
        LOG.debug(obj);
      } catch (Exception e) {

      } finally {
        // executor.shutdown();
      }
    }
  }

  @Override
  public boolean executeUncompleteJob() {
    // TODO Auto-generated method stub
    File folder = new File(JOB_TEMP_FILE_PATH);
    File[] listOfFiles = folder.listFiles();

    for (File file : listOfFiles) {
      if (file.isFile()) {

        String uncompleteJob = file.getName().substring(JOB_TEMP_FILE_PATH.length() + 1);
        // JobEntity job = new
        JobEntity job = new JobEntity() {

          @Override
          public EntityType getAnnotation() {
            // TODO Auto-generated method stub
            return null;
          }
        };
        this.addJob(job);
      }
    }
    return false;
  }

  @Override
  public boolean createJobFile(JobEntity job) {
    // TODO Auto-generated method stub
    try {
      File file = new File(JOB_TEMP_FILE_PATH + job.toString());
      // FileWriter fw = new FileWriter(file);
      // fw.write(job.toString());
      // fw.flush();
      if (file.createNewFile())
        return true;
      else
        return false;

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean deleteJobFile(JobEntity job) {
    // TODO Auto-generated method stub
    try {
      File file = new File(JOB_TEMP_FILE_PATH + job.toString());
      // FileWriter fw = new FileWriter(file);
      // fw.write(job.toString());
      // fw.flush();
      if (file.delete())
        return true;
      else
        return false;

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return false;
  }

  class JobCallable implements Callable<String> {
    private JobEntity job;

    public JobCallable(JobEntity job) {
      this.job = job;
    }

    @Override
    public String call() throws Exception {
      // TODO Auto-generated method stub
      ((JobAction<JobEntity>) JobManagerImpl.this.actionMap.get(job.getClass())).process(job);
      return "OK";
    }
  }
}
