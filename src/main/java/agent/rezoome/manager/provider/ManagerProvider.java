package agent.rezoome.manager.provider;



import agent.rezoome.manager.amq.AMQManager;
import agent.rezoome.manager.amq.AMQManagerImpl;
import agent.rezoome.manager.arrange.ClassArrangeManager;
import agent.rezoome.manager.arrange.ClassArrangeManagerImpl;
import agent.rezoome.manager.database.connect.AbstractDBConnectionManager;
import agent.rezoome.manager.database.connect.DBConnectionManager;
import agent.rezoome.manager.job.JobManager;
import agent.rezoome.manager.job.JobManagerImpl;
import agent.rezoome.manager.log.LogManager;
import agent.rezoome.manager.log.LogManagerImpl;
import agent.rezoome.manager.property.PropertyManager;
import agent.rezoome.manager.property.PropertyManagerImpl;
import agent.rezoome.manager.pushcommand.PushCommandManager;
import agent.rezoome.manager.pushcommand.PushCommandManagerImpl;

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
  
  public static AbstractDBConnectionManager dbConnect(){
    return DBConnectionManager.getInstance();    
  }
  
  
  
  
  
}
