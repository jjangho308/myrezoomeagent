package agent.rezoome.manager.arrange;

import java.util.Map;

import agent.rezoome.core.entity.Action;
import agent.rezoome.core.entity.Entity;
import agent.rezoome.manager.Manager;

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
	public <T extends Entity> Map<Class<? extends T>, ? extends Action<? extends T>> getActionMap(Class<T> cls);

}