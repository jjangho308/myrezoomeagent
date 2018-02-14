package io.rezoome.manager.pushcommand;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.core.annotation.ManagerType;
import io.rezoome.manager.AbstractManager;
import io.rezoome.manager.pushcommand.entity.PushCommandAction;
import io.rezoome.manager.pushcommand.entity.PushCommandEntity;
import io.rezoome.manager.pushcommand.entity.PushCommandResult;

/**
 * Implementation of {@link PushCommandManager}. <br />
 * 
 * @author Saver
 *
 */
@ManagerType("PushCommand")
public class PushCommandManagerImpl extends AbstractManager implements PushCommandManager {

	/**
	 * Hide defaut constructor. <br />
	 */
	private PushCommandManagerImpl() {

	}

	private static class Singleton {
		private static final PushCommandManager instance = new PushCommandManagerImpl();
	}

	public static PushCommandManager getInstance() {
		return Singleton.instance;
	}

	@Override
	public void initialize(InitialEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initializeOnThread(InitialEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public PushCommandResult invokeCommand(PushCommandEntity command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends PushCommandEntity> PushCommandAction<T> getAction(T command) {
		// TODO Auto-generated method stub
		return null;
	}

}
