package io.rezoome.thread;

/**
 * Wrapper of thread for Agent module. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
public final class WorkerThread extends Thread {
	public static final UncaughtExceptionHandler handler = new UncaughtExceptionHandler() {

		@Override
		public void uncaughtException(Thread t, Throwable e) {
			// TODO Auto-generated method stub
		}
	};

	/**
	 * Constructor with runnable. <br />
	 * 
	 * @param runnable
	 */
	public WorkerThread(Runnable runnable) {
		super(runnable);
	}

	/**
	 * Basis constructor. <br />
	 * 
	 * @param handler
	 */
	public WorkerThread(UncaughtExceptionHandler handler) {
		this.setUncaughtExceptionHandler(handler);
	}
}