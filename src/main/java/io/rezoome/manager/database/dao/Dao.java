package io.rezoome.manager.database.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import io.rezoome.external.common.entity.AgencyKeyEntity;
import io.rezoome.external.common.entity.AgencyResultEntity;
import io.rezoome.external.common.entity.AgencyUserEntity;
import io.rezoome.manager.database.entity.UserEntity;

public interface Dao {

  public ArrayList<AgencyKeyEntity> getUserRecords(UserEntity entity) throws IOException;
  public ArrayList<AgencyResultEntity> getCertRecords(AgencyKeyEntity entity) throws IOException;
  public ArrayList<AgencyResultEntity> getScoreRecord(AgencyKeyEntity entity) throws IOException;
  public ArrayList<AgencyResultEntity> getScoreStatisticRecord(AgencyKeyEntity entity) throws IOException;
  public ArrayList<AgencyResultEntity> getJolupRecord(AgencyKeyEntity entity) throws IOException;
}
