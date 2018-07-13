package io.rezoome.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Time elapsed measuring util. <br />
 * 
 * @since 180713
 * @author TACKSU
 * 
 */
public class MeasureUtil {

	public static class TimeMeasureHandler<T> implements InvocationHandler {

		private final T instance;

		public TimeMeasureHandler(T instance) {
			this.instance = instance;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			return method.invoke(instance, args);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T measuringProxy(T instance) {
		return (T) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class<?>[] { instance.getClass() }, new TimeMeasureHandler<T>(instance));
	}

	public static class TimeMeasure {

		private final long		beginNs;
		private StringBuffer	msgBuffer;

		{
			msgBuffer = new StringBuffer();
			beginNs = System.nanoTime();
		}

		private TimeMeasure() {
			this.msgBuffer.append("Thread name : ").append(Thread.currentThread().getName()).append("\r\n");
		}

		public TimeMeasure stamp(String msg) {
			if (this.msgBuffer == null) {
				System.err.println("Cannot stamp");
				return null;
			}

			long now = System.nanoTime();
			long since = now - beginNs;

			this.msgBuffer.append(String.format("Elapsed : %dm %ds", TimeUnit.NANOSECONDS.toMinutes(since), TimeUnit.NANOSECONDS.toSeconds(since)))
					.append("\r\n");
			this.msgBuffer.append(msg).append("\r\n");
			return this;
		}

		public TimeMeasure flush() {
			long now = System.nanoTime();
			long since = now - beginNs;
			this.msgBuffer.append(String.format("Elapsed : %dm %ds", TimeUnit.NANOSECONDS.toMinutes(since), TimeUnit.NANOSECONDS.toSeconds(since)))
					.append("\r\n").append("Thread name : ").append(Thread.currentThread().getName()).append(" Flushed\r\n");
			System.out.println(this.msgBuffer.toString());
			this.msgBuffer = null;
			return this;
		}
	};

	private static final ThreadLocal<List<TimeMeasure>> threadLocal;

	static {
		threadLocal = new ThreadLocal<>();
	}

	public static int newInstance() {
		if (threadLocal.get() == null) {
			threadLocal.set(new ArrayList<TimeMeasure>());
		}
		threadLocal.get().add(new TimeMeasure());
		return threadLocal.get().size() - 1;
	}

	public static TimeMeasure getTimeStamp(int key) {
		return threadLocal.get().get(key);
	}
}