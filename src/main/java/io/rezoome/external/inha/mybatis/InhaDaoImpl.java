package io.rezoome.external.inha.mybatis;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import io.rezoome.manager.database.dao.Dao;
import io.rezoome.manager.database.entity.DBEntity;
import io.rezoome.manager.database.entity.DBRsltEntity;

public class InhaDaoImpl implements Dao {

  SqlSession sqlsession;

  @Override
  public ArrayList<DBRsltEntity> getCertRecords(DBEntity entity) throws IOException {
    // TODO Auto-generated method stub

    return null;
  }

  @Override
  public ArrayList<DBRsltEntity> getJolupRecord(DBEntity entity) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<DBRsltEntity> getSungjukRecord(DBEntity entity) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<DBRsltEntity> getUserRecords(DBEntity entity) throws IOException {
    // TODO Auto-generated method stub
    System.out.println("##################################################");
    sqlsession.selectList("commonDAO.getUserRecords");
    return null;
  }

}
