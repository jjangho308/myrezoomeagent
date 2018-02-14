package io.rezoome.manager.property;

import java.util.Map;

import io.rezoome.manager.Manager;

/**
 * Manager for reading/writing system properties. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 */
public interface PropertyManager extends Manager {
	
	public boolean hasProperty(final String key, boolean... refresh);

	public String getProperty(final String key, boolean... refresh);

	public Map<String, String> getProperties(String... keys);
}
