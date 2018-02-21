package agent.rezoome.manager;

import agent.rezoome.core.annotation.ManagerType;

/**
 * Abstract class of manager. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 */
public abstract class AbstractManager implements Manager {

	private final ManagerType ant;

	protected AbstractManager() {
	}

	{
		ant = this.getClass().getDeclaredAnnotation(ManagerType.class);
	}

	protected boolean prepared = false;

	protected ManagerType getAnnotation() {
		return ant;
	}

	protected void setPrepared() {
		this.prepared = true;
	}

	@Override
	public boolean isPrepared() {
		return prepared;
	}
}
