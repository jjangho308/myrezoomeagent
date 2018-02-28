package io.rezoome.manager.provider;



import io.rezoome.manager.amq.AMQManager;
import io.rezoome.manager.amq.AMQManagerImpl;
import io.rezoome.manager.arrange.ClassArrangeManager;
import io.rezoome.manager.arrange.ClassArrangeManagerImpl;
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


  public static JobManager job() {
    return JobManagerImpl.getInstance();
  }

  public static LogManager log() {
    return LogManagerImpl.getInstance();
  }

  public static AMQManager push() {
    return AMQManagerImpl.getInstance();
  }

  public static PushCommandManager pushcommand() {
    return PushCommandManagerImpl.getInstance();
  }

  public static ClassArrangeManager clsarrange() {
    return ClassArrangeManagerImpl.getInstance();
  }
  
  public static PropertyManager property(){
    return PropertyManagerImpl.getInstance();    
  }
  
  public static DatabaseManager database(){
    return DatabaseManagerImpl.getInstance();    
  }
  
  public static MapperManager mapper(){
    return MapperManagerImpl.getInstance();
  }
  
  public static NetworkManager network(){
    return NetworkManagerImpl.getInstance();
  }
  
  
  
  
}
