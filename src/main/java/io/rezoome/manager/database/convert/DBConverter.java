package io.rezoome.manager.database.convert;

import io.rezoome.manager.database.entity.UserEntity;
import io.rezoome.manager.job.entity.AbstractJobEntity;

public interface DBConverter {
	public UserEntity convert(AbstractJobEntity job);
}
