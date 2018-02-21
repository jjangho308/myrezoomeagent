package io.rezoome.manager.property;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.manager.AbstractManager;
/**
 * Implementation of {@link PropertyManager}. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
public class PropertyManagerImpl extends AbstractManager implements PropertyManager {

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
	  configFile = "";
	  try {
	    properties = this.readProperties();
      
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
	  
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
