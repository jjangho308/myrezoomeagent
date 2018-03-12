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
 * @author Saver +
 */
@ManagerType("PushCommand")
public class PushCommandManagerImpl extends AbstractManager implements PushCommandManager {

	private Map<String, Class<? extends PushCommandEntity>>											entityCodeMap;
	private Map<Class<? extends PushCommandEntity>, PushCommandAction<? super PushCommandEntity>>	actionMap;

	{
		this.entityCodeMap = new HashMap<>();
		this.actionMap = new HashMap<>();
	}

	/**
	 * Hide default constructor. <br />
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
	@SuppressWarnings("unchecked")
	public void initialize(InitialEvent event) {

		entityCodeMap = ManagerProvider.clsarrange().getEntityCodeMap(PushCommandEntity.class);

		actionMap = ManagerProvider.clsarrange().getActionMap(PushCommandEntity.class, PushCommandAction.class);

		setPrepared();
	}

	@Override
	public void initializeOnThread(InitialEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public PushCommandResult invokeCommand(PushCommandEntity command) {

		this.actionMap.get(command.getClass()).process(command);
		return null;
	}

	@Override
	public Class<? extends PushCommandEntity> getEntity(String code) {
		return this.entityCodeMap.get(code);
	}

	@Override
	public <V extends PushCommandEntity> PushCommandAction<? super PushCommandEntity> getAction(V entity) {
		return this.actionMap.get(entity);
	}
}