package io.rezoome.manager.database.convert;

import io.rezoome.manager.database.entity.DBEntity;
import io.rezoome.manager.job.entity.AbstractJobEntity;

public interface DBConverter {
  public DBEntity convert(AbstractJobEntity job);
}
