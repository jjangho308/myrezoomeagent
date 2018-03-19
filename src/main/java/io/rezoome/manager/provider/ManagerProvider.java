package io.rezoome.manager.provider;

import io.rezoome.manager.amq.AMQManager;
import io.rezoome.manager.amq.AMQManagerImpl;
import io.rezoome.manager.arrange.ClassArrangeManager;
import io.rezoome.manager.arrange.ClassArrangeManagerImpl;
import io.rezoome.manager.auth.AuthManager;
import io.rezoome.manager.auth.AuthManagerImpl;
import io.rezoome.manager.crypto.CryptoManager;
import io.rezoome.manager.crypto.CryptoManagerImpl;
import io.rezoome.manager.database.DatabaseManager;
import io.rezoome.manager.database.DatabaseManagerImpl;
import io.rezoome.manager.health.HealthCheckManager;
import io.rezoome.manager.health.HealthCheckManagerImpl;
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
import io.rezoome.manager.status.StatusManager;
import io.rezoome.manager.status.StatusManagerImpl;

/**
 * Manager provider. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 */
public final class ManagerProvider {

  private static final JobManager job = JobManagerImpl.getInstance();
  private static final LogManager log = LogManagerImpl.getInstance();
  private static final AMQManager push = AMQManagerImpl.getInstance();
  private static final PushCommandManager command = PushCommandManagerImpl.getInstance();
  private static final ClassArrangeManager cls = ClassArrangeManagerImpl.getInstance();
  private static final PropertyManager property = PropertyManagerImpl.getInstance();
  private static final DatabaseManager db = DatabaseManagerImpl.getInstance();
  private static final NetworkManager network = NetworkManagerImpl.getInstance();
  private static final AuthManager auth = AuthManagerImpl.getInstance();
  private static final StatusManager status = StatusManagerImpl.getInstance();
  private static final MapperManager mapper = MapperManagerImpl.getInstance();
  private static final CryptoManager crypto = CryptoManagerImpl.getInstance();
  private static final HealthCheckManager health = HealthCheckManagerImpl.getInstance();

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
    return network;
  }

  public static AuthManager authentication() {
    return auth;
  }

  public static StatusManager status() {
    return status;
  }

  public static CryptoManager crypto() {
    return crypto;
  }

  public static HealthCheckManager health() {
    return health;
  }
}
