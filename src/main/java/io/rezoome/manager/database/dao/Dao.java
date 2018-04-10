package io.rezoome.manager.database.dao;

import java.io.IOException;
import java.util.ArrayList;

import io.rezoome.manager.database.entity.DBEntity;
import io.rezoome.manager.database.entity.DBRsltEntity;

public interface Dao {

  public ArrayList<DBRsltEntity> getUserRecords(DBEntity entity) throws IOException;

  public ArrayList<DBRsltEntity> getCertRecords(DBEntity entity) throws IOException;

  public ArrayList<DBRsltEntity> getSungjukRecord(DBEntity entity) throws IOException;

  public ArrayList<DBRsltEntity> getJolupRecord(DBEntity entity) throws IOException;
}
