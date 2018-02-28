package io.rezoome.manager.mapper;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.manager.property.PropertyEnum;
import io.rezoome.manager.provider.ManagerProvider;

public class MapperManagerImpl implements MapperManager {
  
  private static class Singleton {
    private static final MapperManager instance = new MapperManagerImpl();
  }

  public static MapperManagerImpl getInstance() {
    return (MapperManagerImpl) Singleton.instance;
  }
  
  AbstractMapper mapper;
  
  @Override
  public void initialize(InitialEvent event) {
    // TODO Auto-generated method stub
    try {
      createMapper();
    } catch (InstantiationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public void initializeOnThread(InitialEvent event) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public boolean isPrepared() {
    // TODO Auto-generated method stub
    return false;
  }

  public void createMapper() throws InstantiationException,  ClassNotFoundException , IllegalAccessException{
    // TODO Auto-generated method stub
    String mapperClass = ManagerProvider.property().getProperty(PropertyEnum.MAPPER_CLASS_NAME, true);
    //"io.rezoome.agent.db.dao.inha.InhaUnivDao.class"
    ClassLoader loader = ClassLoader.getSystemClassLoader();
    Class<?> mapperCls;
    mapperCls = loader.loadClass(mapperClass);
    this.mapper = (AbstractMapper) mapperCls.newInstance();
  }

  public AbstractMapper getMapper() {
    // TODO Auto-generated method stub
    if(mapper != null)  return this.mapper;
    else return null;
  }

  

}
