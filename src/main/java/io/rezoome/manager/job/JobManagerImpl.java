package io.rezoome.manager.job;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

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
	private JobManagerImpl() {
	}

	private ExecutorService executor;

	{
		this.actionMap = new HashMap<>();
		this.actionMap.put(IORequestJobEntity.class, new IORequestJobAction());
	}

	private final Map<Class<? extends JobEntity>, JobAction<? extends JobEntity>> actionMap;

	@Override
	public void initialize(InitialEvent event) {
		JOB_TEMP_FILE_PATH = ManagerProvider.property().getProperty(PropertyEnum.JOB_TEMP_FILE_PATH, true);

		ExecutorService service = null;
		try {
			service = Executors.newScheduledThreadPool(Integer.parseInt(ManagerProvider.property().getProperty(PropertyEnum.THREAD_POOL_CAPAVILITY, false)),

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

	}

	@Override
	public void initializeOnThread(InitialEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addJob(final JobEntity job) {
		this.executor.execute(new Runnable() {

			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				((JobAction<JobEntity>) JobManagerImpl.this.actionMap.get(job.getClass())).process(job);
			}
		});
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
}