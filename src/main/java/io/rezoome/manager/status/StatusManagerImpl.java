package io.rezoome.manager.status;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.core.annotation.ManagerType;
import io.rezoome.manager.AbstractManager;
import io.rezoome.manager.property.PropertyEnum;
import io.rezoome.manager.provider.ManagerProvider;

/**
 * @author Saver
 *
 */
@ManagerType("Status")
public class StatusManagerImpl extends AbstractManager
		implements StatusManager {
	protected String	keepAliveSendTime;
	protected String	limitAllowNotSignalNumber;
	protected Boolean	isKillAgent;

	@Override
	public void initialize(InitialEvent event) {
		// TODO Auto-generated method stub
		keepAliveSendTime = ManagerProvider.property()
				.getProperty(PropertyEnum.KEEP_ALIVE_SEND_TIME, true);
		limitAllowNotSignalNumber = ManagerProvider.property()
				.getProperty(PropertyEnum.LIMIT_ALLOW_NOT_SIGNAL_NUMBER, true);
		isKillAgent = Boolean.getBoolean(ManagerProvider.property()
				.getProperty(PropertyEnum.IS_KILL_AGENT, true));
		setPrepared();
	}

	@Override
	public void initializeOnThread(InitialEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keepAlive() {
		// TODO Auto-generated method stub

	}

	private static class Singleton {
		private static final StatusManager instance = new StatusManagerImpl();
	}

	public static StatusManager getInstance() {
		return Singleton.instance;
	}

}
