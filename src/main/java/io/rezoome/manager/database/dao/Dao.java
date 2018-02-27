package io.rezoome.manager.database.dao;

import java.io.IOException;
import java.util.ArrayList;

import io.rezoome.manager.database.entity.DatabaseResultEntity;
import io.rezoome.manager.database.entity.DatabaseEntity;

public interface Dao {
  public DatabaseResultEntity getRecord(DatabaseEntity entity) throws IOException;
}
