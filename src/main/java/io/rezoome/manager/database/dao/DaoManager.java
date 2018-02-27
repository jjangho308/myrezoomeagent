package io.rezoome.manager.database.dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import io.rezoome.manager.property.PropertyEnum;
import io.rezoome.manager.provider.ManagerProvider;

public class DaoManager extends AbstractDaoManager{

  private static class Singleton {
    private static final AbstractDaoManager instance = new DaoManager();
  }

  public static DaoManager getInstance() {
    return (DaoManager) Singleton.instance;
  }
  
  protected SqlSession sqlsession = null;
  protected Dao dao;
  
  public DaoManager(){
    String resource = super.mybatisConfigXmlPath;    
    InputStream inputStream;
    try {
      inputStream = Resources.getResourceAsStream(resource);
   
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);      
      sqlsession = sqlSessionFactory.openSession(ManagerProvider.database().getConnectManager().getConnection());
      //sqlsession.getMapper(OpicDaoImpl.class);
      
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  
  public Dao getDao() throws ClassNotFoundException , ReflectiveOperationException {
    String daoClass = ManagerProvider.property().getProperty(PropertyEnum.DAO_CLASS_NAME, true);
    //"io.rezoome.agent.db.dao.inha.InhaUnivDao.class"
    ClassLoader loader = ClassLoader.getSystemClassLoader();
    Class<?> daoCls = loader.loadClass(daoClass);
    this.dao = (Dao) daoCls.newInstance();
    this.dao = sqlsession.getMapper(dao.getClass());
    return dao;
  }
  
}