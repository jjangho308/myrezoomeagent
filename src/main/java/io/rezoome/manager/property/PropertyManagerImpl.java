package io.rezoome.manager.property;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.core.annotation.ManagerType;
import io.rezoome.manager.AbstractManager;

/**
 * Implementation of {@link PropertyManager}. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
@ManagerType(value = "Property", initPriority = 10)
public class PropertyManagerImpl extends AbstractManager implements PropertyManager {

  private static final Logger LOG = LoggerFactory.getLogger("AGENT_LOG");

  private static class Singleton {
    private static final PropertyManager instance = new PropertyManagerImpl();
  }

  public static PropertyManager getInstance() {
    return Singleton.instance;
  }

  private String configFile;
  private Properties properties;

  @Override
  public void initialize(InitialEvent event) {
    // TODO Auto-generated method stub
    configFile = "./agent.prop";
    try {
      properties = this.readProperties();
      LOG.info("{} Init Complete.", this.getClass());
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    setPrepared();

  }

  @Override
  public void initializeOnThread(InitialEvent event) {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean hasProperty(PropertyEnum key, boolean... refresh) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String getProperty(PropertyEnum key, boolean... refresh) {
    // TODO Auto-generated method stub
    return properties.getProperty(key.toString());
  }

  @Override
  public Map<String, String> getProperties(PropertyEnum... keys) {
    // TODO Auto-generated method stub
    return null;
  }

  private synchronized Properties readProperties() throws IOException {
    Properties tempProperties = new Properties();
    FileInputStream in = new FileInputStream(configFile);
    tempProperties.load(in);
    return tempProperties;
  }
}
