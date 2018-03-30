package io.rezoome.manager.database.dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import io.rezoome.manager.database.DatabaseManagerImpl;
import io.rezoome.manager.provider.ManagerProvider;

public class DaoManagerImpl extends DatabaseManagerImpl implements DaoManager {

  private static class Singleton {
    private static final DatabaseManagerImpl instance = new DaoManagerImpl();
  }

  public static DatabaseManagerImpl getInstance() {
    return Singleton.instance;
  }

  protected SqlSession sqlsession = null;
  protected Dao dao;

  public DaoManagerImpl() {

  }

  @Override
  public Dao getDao() {
    return this.dao;
  }

  @SuppressWarnings("static-access")
  @Override
  public void createDao() {
    // TODO Auto-generated method stub
    String resource = super.mybatisConfigXmlPath;
    InputStream inputStream;
    try {
      inputStream = Resources.getResourceAsStream(resource);

      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      sqlsession = sqlSessionFactory.openSession(ManagerProvider.database().getConnectManager().getConnection(super.poolName));

      ClassLoader loader = ClassLoader.getSystemClassLoader();
      Class<?> daoCls = loader.loadClass(daoClass);

      this.dao = sqlsession.getMapper(Dao.class);
    } catch (IOException | ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
