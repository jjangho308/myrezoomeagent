package agent.rezoome.core.entity;

/**
 * Root implementation of Action. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
public interface Action<T extends Entity> {

	ActionResult process(T entity);
}