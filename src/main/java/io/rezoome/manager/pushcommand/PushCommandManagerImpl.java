package io.rezoome.manager.pushcommand;

import java.util.HashMap;
import java.util.Map;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.core.annotation.ManagerType;
import io.rezoome.manager.AbstractManager;
import io.rezoome.manager.provider.ManagerProvider;
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

	private final Map<String, Class<PushCommandEntity>> entityCodeMap;
	private final Map<Class<PushCommandEntity>, PushCommandAction<? extends PushCommandEntity>> actionMap;

	{
		this.entityCodeMap = new HashMap<String, Class<PushCommandEntity>>();
		this.actionMap = new HashMap<Class<PushCommandEntity>, PushCommandAction<? extends PushCommandEntity>>();
	}

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
		ManagerProvider.clsarrange().getEntityCodeMap(PushCommandEntity.class);
		setPrepared();
	}

	@Override
	public void initializeOnThread(InitialEvent event) {
		// TODO Auto-generated method stub

	}
	@SuppressWarnings("unchecked")
	@Override
	public PushCommandResult invokeCommand(PushCommandEntity command) {
    PushCommandAction<PushCommandEntity> action = (PushCommandAction<PushCommandEntity>) this.actionMap.get(command.getClass());
		action.process(command);
		return null;
	}

	@Override
	public <T extends PushCommandEntity> PushCommandAction<T> getAction(T command) {
		// TODO Auto-generated method stub
	  
		return null;
	}

}
