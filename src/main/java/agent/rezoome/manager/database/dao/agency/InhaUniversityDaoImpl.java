package agent.rezoome.manager.database.dao.agency;

import java.io.IOException;
import java.util.ArrayList;

import agent.rezoome.manager.database.dao.OracleDao;
import agent.rezoome.manager.database.dao.UniversityDao;
import agent.rezoome.manager.database.entity.DatabaseEntity;
import agent.rezoome.manager.database.entity.DatabaseResultEntity;


public class InhaUniversityDaoImpl extends OracleDao implements UniversityDao{

 
  
  @Override
  public DatabaseResultEntity selectIsGraudateByRzmVo(DatabaseEntity entity) throws IOException {
    // TODO Auto-generated method stub

    
    return null;
  }

  @Override
  public DatabaseResultEntity selectIsGraudateById(DatabaseEntity entity) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<DatabaseResultEntity> selectGradeByRzmVo(DatabaseEntity entity) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<DatabaseResultEntity> selectGradeById(DatabaseEntity entity) {
    // TODO Auto-generated method stub
    return null;
  }
  
}
