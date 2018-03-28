package io.rezoome.manager.database.dao;

import java.io.IOException;
import java.util.ArrayList;

import io.rezoome.manager.database.entity.DBEntity;
import io.rezoome.manager.database.entity.DBRsltEntity;

public interface Dao {

  public int getUserCountByCI(DBEntity entity) throws Exception;

  public ArrayList<DBRsltEntity> getUserRecordByName(DBEntity entity) throws IOException;

  public ArrayList<DBRsltEntity> getCertRecords(DBEntity entity) throws IOException;

  public int getRecordsByName(DBEntity entity) throws IOException;

  public int getRecordsByPhone(DBEntity entity) throws IOException;
}
