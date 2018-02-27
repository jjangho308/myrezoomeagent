package io.rezoome.manager.log;

import java.util.UUID;

/**
 * Logger builder. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
public class LoggerBuilder {
	
	private final ThreadLocal<String> taskIdLocal = new ThreadLocal<>();

	private String taskId;

	private Class<?> cls;

	public LoggerBuilder() {
	}

	public LoggerBuilder setClass(Class<?> cls) {
		this.cls = cls;
		return this;
	}

	public Logger create() {
		Logger logger = null;
		
		if(taskIdLocal.get() == null){
			taskIdLocal.set(UUID.randomUUID().toString());
		}
		
		// TODO Set task id to logger.

		return logger;
	}
}