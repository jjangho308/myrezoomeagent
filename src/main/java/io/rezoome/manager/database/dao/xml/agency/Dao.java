package io.rezoome.manager.database.dao.xml.agency;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.rezoome.manager.database.entity.DBRsltEntity;
import io.rezoome.manager.database.entity.DBEntity;

public interface Dao {
  //public List<DBRsltEntity> getRecord(DBEntity entity) throws IOException;
  public DBRsltEntity getRecord(DBEntity entity) throws IOException;
}
