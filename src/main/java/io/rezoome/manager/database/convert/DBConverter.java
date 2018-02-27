package io.rezoome.manager.database.convert;

import io.rezoome.manager.database.entity.DatabaseEntity;
import io.rezoome.manager.job.entity.AbstractJobEntity;

public interface DBConverter {
  public DatabaseEntity convert(AbstractJobEntity job);
}
