package agent.rezoome.manager.database.dao;

import java.io.IOException;
import java.util.ArrayList;

import agent.rezoome.manager.database.entity.DatabaseEntity;
import agent.rezoome.manager.database.entity.DatabaseResultEntity;

public interface LicenseDao {
  public ArrayList<DatabaseResultEntity> selectGradeByRzmVo(DatabaseEntity entity) throws IOException;
  public ArrayList<DatabaseResultEntity> selectGradeById(DatabaseEntity entity) throws IOException;
}
