package io.rezoome.manager.property;

import java.util.Map;

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

	@Override
	public void initialize(InitialEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initializeOnThread(InitialEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasProperty(String key, boolean... refresh) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getProperty(String key, boolean... refresh) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getProperties(String... keys) {
		// TODO Auto-generated method stub
		return null;
	}

}
