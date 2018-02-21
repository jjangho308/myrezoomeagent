package io.rezoome.manager.job;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.http.HttpConnector;
import io.rezoome.http.HttpManager;
import io.rezoome.jdbc.ConnectionManager;
import io.rezoome.jdbc.OracleConnectionManager;
import io.rezoome.manager.AbstractManager;
import io.rezoome.manager.job.entity.JobAction;
import io.rezoome.manager.job.entity.JobEntity;
import io.rezoome.manager.provider.ManagerProvider;
import io.rezoome.thread.WorkerThread;

public final class JobManagerImpl extends AbstractManager implements JobManager {
	
	private static class Singleton{
		private static final JobManager instance = new JobManagerImpl();
	}
	
	public static JobManager getInstance(){
		return Singleton.instance;
	}
	
	@SuppressWarnings("unchecked")
	private JobManagerImpl(){
		this.actionMap = (Map<Class<? extends JobEntity>, ? extends JobAction<? extends JobEntity>>) ManagerProvider.clsarrange().getActionMap(JobEntity.class);
	}

	private final ExecutorService executor = Executors.newScheduledThreadPool(1, new ThreadFactory() {
		
		@Override
		public Thread newThread(Runnable r) {
			return new WorkerThread(r);
		}
	});
	
	private final Map<Class<? extends JobEntity>, ? extends JobAction<? extends JobEntity>> actionMap;

	
	  
	@Override
	public void initialize(InitialEvent event) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void initializeOnThread(InitialEvent event) {
		// TODO Auto-generated method stub
	  
	  
	  
	}

	@Override
	public JobEntity addJob(JobEntity job) {
		this.executor.submit(this.actionMap.get(job.getClass()));
		return null;
	}
}