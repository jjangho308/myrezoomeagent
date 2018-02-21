package agent.rezoome.manager.job;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import agent.rezoome.core.ServiceInitializer.InitialEvent;
import agent.rezoome.http.HttpConnector;
import agent.rezoome.http.HttpManager;
import agent.rezoome.jdbc.ConnectionManagerImpl;
import agent.rezoome.jdbc.OracleConnectionManager;
import agent.rezoome.manager.AbstractManager;
import agent.rezoome.manager.job.entity.JobAction;
import agent.rezoome.manager.job.entity.JobEntity;
import agent.rezoome.manager.provider.ManagerProvider;
import agent.rezoome.thread.WorkerThread;

/**
 * Implementation of {@link JobManager}. <br />
 * 
 * @since 1.0.0 
 * @author TACKSU
 *
 */
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
