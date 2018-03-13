package io.rezoome.manager.database.dao.xml.agency;

import java.io.IOException;
import java.util.List;

import io.rezoome.manager.database.entity.DBEntity;
import io.rezoome.manager.database.entity.DBRsltEntity;

public interface Dao {

  public DBRsltEntity getRecord(DBEntity entity) throws IOException;

  public List<DBRsltEntity> getRecords(DBEntity entity) throws IOException;
}
