package agent.rezoome.core;

import agent.rezoome.manager.provider.ManagerProvider;

/**
 * Service initializer. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
public final class ServiceInitializer {
	
	public enum InitialEvent{
		RUNTIME
	}
	
	public enum InitializationPhase{
		UNINITLIAZED,
		INITIALIZING,
		SYNC_INITIALIZED,
		ASYNC_INITIALIZED
	}
	
	private static InitializationPhase phase = InitializationPhase.UNINITLIAZED;
	private static InitialEvent event;
	
	public static synchronized void  initialize(InitialEvent from){
		if(phase != InitializationPhase.UNINITLIAZED){
			return;
		}
		event = from;
		phase = InitializationPhase.INITIALIZING;
		
		// TODO Do sync initialization
		ManagerProvider.pushcommand().initialize(from);
		
		ManagerProvider.log().initialize(from);
		
		phase = InitializationPhase.SYNC_INITIALIZED;
		
		// TODO do async initialization.
	}
	
	/**
	 * Hide constructor. <br />
	 * 
	 * @since 1.0.0
	 * @author TACKSU
	 */
	private ServiceInitializer(){}
}
