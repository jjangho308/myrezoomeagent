package io.rezoome.manager.database.convert;

import io.rezoome.manager.database.entity.UserEntity;
import io.rezoome.manager.job.entity.AbstractJobEntity;

public abstract class AbstractDBConverter implements DBConverter {
	public abstract UserEntity convert(AbstractJobEntity job);
}
