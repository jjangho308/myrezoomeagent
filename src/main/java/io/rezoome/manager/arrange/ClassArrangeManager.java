package io.rezoome.manager.arrange;

import java.util.Map;

import io.rezoome.core.entity.Action;
import io.rezoome.core.entity.Entity;
import io.rezoome.manager.Manager;

/**
 * Class arranger. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
public interface ClassArrangeManager extends Manager {

	/**
	 * Get map contains string code integrated entity class. <br />
	 * 
	 * @param cls
	 * @return
	 */
	public <T extends Entity> Map<String, Class<? extends T>> getEntityCodeMap(Class<T> cls);

	/**
	 * 
	 * @param cls
	 * @return
	 */
	public <T extends Entity> Map<Class<T>, ? extends Action<T>> getActionMap(Class<T> cls);

}