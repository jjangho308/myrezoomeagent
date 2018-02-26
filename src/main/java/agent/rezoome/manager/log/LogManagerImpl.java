package agent.rezoome.manager.log;

import agent.rezoome.core.ServiceInitializer.InitialEvent;
import agent.rezoome.core.annotation.ManagerType;
import agent.rezoome.manager.AbstractManager;

@ManagerType("Log")
public final class LogManagerImpl extends AbstractManager implements LogManager {

	private static class Singleton {
		private static final LogManager instance = new LogManagerImpl();
	}

	public static LogManager getInstance() {
		return Singleton.instance;
	}

	/**
	 * Hide public constructor for singleton. <br />
	 * 
	 * @since 1.0.0
	 * @author TACKSU
	 */
	private LogManagerImpl() {
		super();
	}

	@Override
	public void initialize(InitialEvent event) {
		this.setPrepared();
	}

	@Override
	public void initializeOnThread(InitialEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void sendLogToServer(Runnable callback) {
		// TODO Auto-generated method stub
	}

	@Override
	public Logger createLogger(Class<?> object) {
		// TODO Auto-generated method stub
		return null;
	}
}