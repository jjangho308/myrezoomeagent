package io.rezoome.core;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import io.rezoome.core.annotation.ManagerType;
import io.rezoome.manager.Manager;
import io.rezoome.manager.provider.ManagerProvider;
import io.rezoome.thread.WorkerThread;

/**
 * Service initializer. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
public final class ServiceInitializer {

	public enum InitialEvent {
		NOT_RUNTIME, RUNTIME
	}

	public enum InitializationPhase {
		UNINITLIAZED, INITIALIZING, SYNC_INITIALIZED, ASYNC_INITIALIZED
	}

	private static InitializationPhase phase = InitializationPhase.UNINITLIAZED;
	private static InitialEvent event;

	private static final List<Manager> managers = new ArrayList<>();

	public static synchronized void initialize(InitialEvent from) {
		if (phase != InitializationPhase.UNINITLIAZED) {
			return;
		}
		event = from;
		phase = InitializationPhase.INITIALIZING;

		for (Field manager : ManagerProvider.class.getDeclaredFields()) {
			
			// Static member이자 Manager type의 member만 가져옴
			if (Modifier.isStatic(manager.getModifiers())
					&& manager.getType().asSubclass(Manager.class) != null) {
				
				try {
					managers.add((Manager) manager.get(null));

					// @ManagerType의 initPriority를 기준으로 정렬. 숫자가 낮을 수록 먼저 초기화 됨
					managers.sort(new Comparator<Manager>() {

						@Override
						public int compare(Manager o1, Manager o2) {
							return o2.getClass().getAnnotation(ManagerType.class).initPriority()
									- o1.getClass().getAnnotation(ManagerType.class).initPriority();
						}
					});
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

		for (Manager manager : managers) {
			manager.initialize(event);
		}

		phase = InitializationPhase.SYNC_INITIALIZED;

		// Async initialization.
		new WorkerThread(new Runnable() {

			@Override
			public void run() {
				// {
				for (Manager manager : managers) {
					manager.initializeOnThread(event);
				}

			}
		}).start();
	}

	/**
	 * Hide constructor. <br />
	 * 
	 * @since 1.0.0
	 * @author TACKSU
	 */
	private ServiceInitializer() {
	}
}
