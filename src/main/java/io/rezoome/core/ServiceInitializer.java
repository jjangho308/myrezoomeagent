package io.rezoome.core;

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
