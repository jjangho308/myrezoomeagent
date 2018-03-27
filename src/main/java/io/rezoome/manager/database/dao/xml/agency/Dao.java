package io.rezoome.manager.database.dao.xml.agency;

import java.io.IOException;
import java.util.ArrayList;

import io.rezoome.manager.database.entity.DBEntity;
import io.rezoome.manager.database.entity.DBRsltEntity;

public interface Dao {

  public int getUserCountByCI(DBEntity entity) throws Exception;

  public ArrayList<DBRsltEntity> getUserRecordByName(DBEntity entity) throws IOException;

  public ArrayList<DBRsltEntity> getCertRecords(DBEntity entity) throws IOException;

  // 최초


  // 두번째
  // 결과 0 이면 끝
  // 결과 1 이상이면 생년월일, 젠더, 까지 확인
  public int getRecordsByName(DBEntity entity) throws IOException;

  public int getRecordsByPhone(DBEntity entity) throws IOException;
}
