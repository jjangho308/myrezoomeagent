package io.rezoome.manager.log;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.core.annotation.ManagerType;
import io.rezoome.manager.AbstractManager;

@ManagerType("Log")
public class LogManagerImpl extends AbstractManager implements LogManager {
	
	private static class Singleton{
		private static final LogManager instance;
		static {
			instance = new LogManagerImpl();
		}
	}
	
	public static LogManager getInstance(){
		return Singleton.instance;
	}
	
	/**
	 * Hide public constructor for singleton. <br />
	 * 
	 * @since 1.0.0
	 * @author TACKSU
	 */
	private LogManagerImpl(){
		super();
	}

	@Override
	public void initialize(InitialEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initializeOnThread(InitialEvent event) {
		// TODO Auto-generated method stub

	}
}
