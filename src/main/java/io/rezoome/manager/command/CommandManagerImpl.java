package io.rezoome.manager.command;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.manager.AbstractManager;

/**
 * Implementation of {@link CommandManager}. <br />
 * 
 * @since 180309
 *
 */
public final class CommandManagerImpl extends AbstractManager implements CommandManager {

	private static class Singleton {
		private static final CommandManager instance = new CommandManagerImpl();
	}

	public static CommandManager getInstance() {
		return Singleton.instance;
	}

	private CommandManagerImpl() {
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
