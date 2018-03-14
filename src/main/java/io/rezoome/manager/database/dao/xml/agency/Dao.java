package io.rezoome.manager.database.dao.xml.agency;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.rezoome.manager.database.entity.DBEntity;
import io.rezoome.manager.database.entity.DBRsltEntity;

public interface Dao {
  
  public ArrayList<DBRsltEntity> getRecords(DBEntity entity) throws IOException;

  public DBRsltEntity getRecordByCi(DBEntity entity) throws IOException;

  public List<DBRsltEntity> getRecordsByName(DBEntity entity) throws IOException;

  public List<DBRsltEntity> getRecordsByPhone(DBEntity entity) throws IOException;
}
