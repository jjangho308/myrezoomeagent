package io.rezoome.manager.database.dao.xml.agency;

import java.io.IOException;
import java.util.ArrayList;

import io.rezoome.manager.database.entity.DBRsltEntity;
import io.rezoome.manager.database.entity.DBEntity;

public interface Dao {
  public DBRsltEntity getRecord(DBEntity entity) throws IOException;
}
