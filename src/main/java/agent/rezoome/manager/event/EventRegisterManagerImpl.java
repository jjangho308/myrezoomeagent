package agent.rezoome.manager.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;

import agent.rezoome.core.ServiceInitializer.InitialEvent;
import agent.rezoome.manager.AbstractManager;
import agent.rezoome.manager.event.listener.EventListener;

/**
 * Implementation of {@link EventRegisterManager}. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 * @author Saver
 *
 */
public final class EventRegisterManagerImpl extends AbstractManager implements EventRegisterManager {

	private final Map<Class<EventListener>, List<EventListener>> listenerMap = new HashMap<>();

	/**
	 * Hide constructor. <br />
	 * 
	 * @since 1.0.0
	 */
	private EventRegisterManagerImpl() {
	}

	private static class Singleton {
		private static final EventRegisterManager instance = new EventRegisterManagerImpl();
	}

	public static EventRegisterManager getInstance() {
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
	@SuppressWarnings("unchecked")
	public void addEventListener(EventListener listener) {
		Class<? extends EventListener> listenerCls = listener.getClass();
		if (!listenerMap.containsKey(listenerCls)) {
			listenerMap.put((Class<EventListener>) listenerCls, new ArrayList<EventListener>());
		}
		listenerMap.get(listenerCls).add(listener);
	}

	@Override
	public void removeEventListener(EventListener eventListener) {
	}
}
