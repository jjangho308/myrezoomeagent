package io.rezoome.manager.pushcommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.core.annotation.ManagerType;
import io.rezoome.manager.AbstractManager;
import io.rezoome.manager.provider.ManagerProvider;
import io.rezoome.manager.pushcommand.entity.PushCommandAction;
import io.rezoome.manager.pushcommand.entity.PushCommandEntity;
import io.rezoome.manager.pushcommand.entity.PushCommandResult;
import io.rezoome.manager.pushcommand.entity.search.SearchCommand;
import io.rezoome.manager.pushcommand.entity.search.SearchCommandEntity;

/**
 * Implementation of {@link PushCommandManager}. <br />
 * 
 * @author Saver
 *
 */
@ManagerType("PushCommand")
public class PushCommandManagerImpl extends AbstractManager implements PushCommandManager {

	private final Map<String, Class<? extends PushCommandEntity>> entityCodeMap;
	private final Map<Class<? extends PushCommandEntity>, PushCommandAction<? extends PushCommandEntity>> actionMap;

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
	public void initialize(InitialEvent event) {
		Map<String, Class<?>> map = ManagerProvider.clsarrange().getEntityCodeMap(PushCommandEntity.class);
	

		this.entityCodeMap.put("SearchRecord", SearchCommandEntity.class);
		this.actionMap.put(SearchCommandEntity.class, new SearchCommand());
		setPrepared();
	}

	@Override
	public void initializeOnThread(InitialEvent event) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public PushCommandResult invokeCommand(PushCommandEntity command) {
		
		PushCommandAction<PushCommandEntity> action =
				(PushCommandAction<PushCommandEntity>) this.actionMap
				.get(command.getClass());
		action.process(command);
		
		return null;
	}

	@Override
	public <T extends PushCommandEntity> PushCommandAction<T> getAction(T command) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public Class<? extends PushCommandEntity> getCommandEntity(String cmdName) {
		return this.entityCodeMap.get(cmdName);
	}

}