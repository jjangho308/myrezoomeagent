package io.rezoome.manager.job;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.manager.AbstractManager;
import io.rezoome.manager.job.entity.JobAction;
import io.rezoome.manager.job.entity.JobEntity;
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
public final class JobManagerImpl extends AbstractManager implements JobManager {

	private static class Singleton {
		private static final JobManager instance = new JobManagerImpl();
	}

	public static JobManager getInstance() {
		return Singleton.instance;
	}

	@SuppressWarnings("unchecked")
	private JobManagerImpl() {
		this.actionMap = (Map<Class<? extends JobEntity>, ? extends JobAction<? extends JobEntity>>) ManagerProvider
				.clsarrange().getActionMap(JobEntity.class);
	}

	private final ExecutorService executor;

	{
		ExecutorService service = null;
		try {
			service = Executors.newScheduledThreadPool(
					Integer.parseInt(
							ManagerProvider.property().getProperty(PropertyEnum.THREAD_POOL_CAPAVILITY, false)),
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

	private final Map<Class<? extends JobEntity>, ? extends JobAction<? extends JobEntity>> actionMap;

	@Override
	public void initialize(InitialEvent event) {
		// TODO Auto-generated method stub
		setPrepared();
	}

	@Override
	public void initializeOnThread(InitialEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addJob(JobEntity job) {
		this.executor.submit(this.actionMap.get(job.getClass()));
	}
}
