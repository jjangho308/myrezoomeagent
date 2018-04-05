package io.rezoome.manager;

import java.lang.annotation.Annotation;

import io.rezoome.core.annotation.ManagerType;

/**
 * Abstract class of manager. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 */
public abstract class AbstractManager implements Manager {

	private final ManagerType ant;

	/**
	 * Hide default constructor. <br />
	 * 
	 */
	protected AbstractManager() {
	}

	{
		ManagerType found = null;
		// Class.getDeclaredAnnotation() method가 1.8에서만 지원되서 아래와 같이 변경함.
		for (Annotation annot : this.getClass().getAnnotations()) {
			if (ManagerType.class.equals(annot.annotationType())) {
				found = ManagerType.class.cast(annot);
			}
		}

		ant = found;
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
