package agent.rezoome.manager.database.dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import agent.rezoome.manager.provider.ManagerProvider;

public class DaoManager extends AbstractDaoManager{

  private static class Singleton {
    private static final AbstractDaoManager instance = new DaoManager();
  }

  public static DaoManager getInstance() {
    return (DaoManager) Singleton.instance;
  }
  
  protected SqlSession sqlsession = null;
  protected AbstractDaoManager dao = null;
  
  public DaoManager(){
    String resource = super.mybatisConfigXmlPath;    
    InputStream inputStream;
    try {
      inputStream = Resources.getResourceAsStream(resource);
   
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);      
      sqlsession = sqlSessionFactory.openSession(ManagerProvider.dbConnect().getConnection());
      //sqlsession.getMapper(OpicDaoImpl.class);
      
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  public void setMapper(AbstractDaoManager dao){
    this.dao = sqlsession.getMapper(dao.getClass());
  }
  
  
}