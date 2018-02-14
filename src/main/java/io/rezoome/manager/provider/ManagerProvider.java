package io.rezoome.manager.provider;

import io.rezoome.job.JobManager;
import io.rezoome.job.JobManagerImpl;
import io.rezoome.manager.arrange.ClassArrangeManager;
import io.rezoome.manager.arrange.ClassArrangeManagerImpl;
import io.rezoome.manager.log.LogManager;
import io.rezoome.manager.log.LogManagerImpl;
import io.rezoome.manager.push.PushManager;
import io.rezoome.manager.pushcommand.PushCommandManager;
import io.rezoome.manager.pushcommand.PushCommandManagerImpl;

/**
 * Manager provider. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
public final class ManagerProvider {

	public static JobManager job() {
		return JobManagerImpl.getInstance();
	}

	public static LogManager log() {
		return LogManagerImpl.getInstance();
	}

	public static PushManager push() {
		return null;
	}

	public static PushCommandManager pushcommand() {
		return PushCommandManagerImpl.getInstance();
	}

	public static ClassArrangeManager clsarrange() {
		return ClassArrangeManagerImpl.getInstance();
	}
}
