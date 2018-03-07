package io.rezoome.manager.provider;

import io.rezoome.manager.amq.AMQManager;
import io.rezoome.manager.amq.AMQManagerImpl;
import io.rezoome.manager.arrange.ClassArrangeManager;
import io.rezoome.manager.arrange.ClassArrangeManagerImpl;
import io.rezoome.manager.auth.AuthManager;
import io.rezoome.manager.auth.AuthManagerImpl;
import io.rezoome.manager.database.DatabaseManager;
import io.rezoome.manager.database.DatabaseManagerImpl;
import io.rezoome.manager.job.JobManager;
import io.rezoome.manager.job.JobManagerImpl;
import io.rezoome.manager.log.LogManager;
import io.rezoome.manager.log.LogManagerImpl;
import io.rezoome.manager.mapper.MapperManager;
import io.rezoome.manager.mapper.MapperManagerImpl;
import io.rezoome.manager.network.NetworkManager;
import io.rezoome.manager.network.NetworkManagerImpl;
import io.rezoome.manager.property.PropertyManager;
import io.rezoome.manager.property.PropertyManagerImpl;
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

	static final JobManager job = JobManagerImpl.getInstance();
	static final LogManager log = LogManagerImpl.getInstance();
	static final AMQManager push = AMQManagerImpl.getInstance();
	static final PushCommandManager command = PushCommandManagerImpl.getInstance();
	static final ClassArrangeManager cls = ClassArrangeManagerImpl.getInstance();
	static final PropertyManager property = PropertyManagerImpl.getInstance();
	static final DatabaseManager db = DatabaseManagerImpl.getInstance();
	static final NetworkManager network = NetworkManagerImpl.getInstance();
	static final AuthManager auth = AuthManagerImpl.getInstance();
	private static MapperManager mapper = MapperManagerImpl.getInstance();

	public static JobManager job() {
		return job;
	}

	public static LogManager log() {
		return log;
	}

	public static AMQManager push() {
		return push;
	}

	public static PushCommandManager pushcommand() {
		return command;
	}

	public static ClassArrangeManager clsarrange() {
		return cls;
	}

	public static PropertyManager property() {
		return property;
	}

	public static DatabaseManager database() {
		return db;
	}

	public static MapperManager mapper() {
		return mapper;
	}

	public static NetworkManager network() {
		return NetworkManagerImpl.getInstance();
	}

	public static AuthManager authentication() {
		return AuthManagerImpl.getInstance();
	}

}
