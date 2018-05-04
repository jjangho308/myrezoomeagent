package io.rezoome.manager.mapper;

import io.rezoome.constants.Constants;
import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.core.annotation.ManagerType;
import io.rezoome.external.common.mapper.DaoMapper;
import io.rezoome.manager.AbstractManager;
import io.rezoome.manager.property.PropertyEnum;
import io.rezoome.manager.provider.ManagerProvider;

@ManagerType(Constants.MANAGER_TYPE_MAPPER)
public class MapperManagerImpl extends AbstractManager implements MapperManager {

  private static class Singleton {
    private static final MapperManager instance = new MapperManagerImpl();
  }

  public static MapperManager getInstance() {
    return Singleton.instance;
  }

  Mapper mapper;
  DaoMapper daoMapper;
  
  @Override
  public void initialize(InitialEvent event) {
    // TODO Auto-generated method stub
    try {
      createMapper();
      createDaoMapper();
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
    setPrepared();
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

  @Override
  public void createMapper() throws InstantiationException, ClassNotFoundException, IllegalAccessException {
    // TODO Auto-generated method stub
    String mapperClass = ManagerProvider.property().getProperty(PropertyEnum.MAPPER_CLASS_NAME, true);
    // "io.rezoome.agent.db.dao.inha.InhaUnivDao.class"
    ClassLoader loader = ClassLoader.getSystemClassLoader();
    Class<?> mapperCls;
    mapperCls = loader.loadClass(mapperClass);
    this.mapper = (Mapper) mapperCls.newInstance();
  }

  @Override
  public Mapper getMapper() {
    // TODO Auto-generated method stub
    if (mapper != null)
      return this.mapper;
    else
      return null;
  }

  @Override
  public void createDaoMapper() {
    try {   
      String mapperClass = ManagerProvider.property().getProperty(PropertyEnum.DAO_MAPPER_CLASS_NAME, true);
      // "io.rezoome.agent.db.dao.inha.InhaUnivDao.class"
      ClassLoader loader = ClassLoader.getSystemClassLoader();
      Class<?> mapperCls;
     
        mapperCls = loader.loadClass(mapperClass);
     
      this.daoMapper = (DaoMapper) mapperCls.newInstance();
    }catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (InstantiationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public DaoMapper getDaoMapper() {
    if (daoMapper != null)
      return this.daoMapper;
    else
      return null;
  }

}
