package io.rezoome.manager.property;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.rezoome.constants.Constants;
import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.core.annotation.ManagerType;
import io.rezoome.exception.ServiceException;
import io.rezoome.external.common.prop.AbstractAgentProperties;
import io.rezoome.manager.AbstractManager;
import io.rezoome.manager.mapper.Mapper;
import io.rezoome.manager.provider.ManagerProvider;

/**
 * Implementation of {@link PropertyManager}. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
@ManagerType(value = Constants.MANAGER_TYPE_PROPERTY, initPriority = 10)
public class PropertyManagerImpl extends AbstractManager implements PropertyManager {

  private static final Logger LOG = LoggerFactory.getLogger(Constants.AGENT_LOG);

  private static class Singleton {
    private static final PropertyManager instance = new PropertyManagerImpl();
  }

  public static PropertyManager getInstance() {
    return Singleton.instance;
  }

  private String configFile;
  private Properties properties;


  @Override
  public void initialize(InitialEvent event) throws ServiceException {
    // TODO Auto-generated method stub
    configFile = "./agent.prop";
    try {
      properties = this.readProperties();
      LOG.info("{} Init Complete.", this.getClass());


      String agentPropClass = ManagerProvider.property().getProperty(PropertyEnum.PROP_CLASS_NAME, true);
      // "io.rezoome.agent.db.dao.inha.InhaUnivDao.class"
      ClassLoader loader = ClassLoader.getSystemClassLoader();
      Class<?> agentPropCls;
      agentPropCls = loader.loadClass(agentPropClass);
      AbstractAgentProperties aap = (AbstractAgentProperties) agentPropCls.newInstance();
      Field[] fList = aap.getClass().getFields();
      System.out.println(fList);
      for (Field f : fList) {

        properties.setProperty(f.getName(), (String) f.get(aap));
      }


      // SystemValue


      
       //java -jar agent.jar -DenvTarget=STG
       /*
       String envTarget = System.getProperty("envTarget");
       if("DEV".equals(envTarget)){
         properties.setProperty(PropertyEnum.PORTAL_URL.toString(), properties.getProperty(PropertyEnum.PORTAL_URL_DEV.toString()));
       }else if("STG".equals(envTarget)){
         properties.setProperty(PropertyEnum.PORTAL_URL.toString(), properties.getProperty(PropertyEnum.PORTAL_URL_STG.toString()));
       }else if("PRD".equals(envTarget)){
         properties.setProperty(PropertyEnum.PORTAL_URL.toString(), properties.getProperty(PropertyEnum.PORTAL_URL_PRD.toString()));
       }else{
         
       }*/
       


    } catch (IOException e) {
      throw new ServiceException("fail to initialize property manager, Not found agent.prop file.", e);
    } catch (ClassNotFoundException e) {
      throw new ServiceException("fail to initialize property manager, Can not load Agent Properties Class. Check a properties in agent.prop file., ", e);
    } catch (InstantiationException e) {
      throw new ServiceException("fail to initialize property manager, Can not create agent prop class.", e);
    } catch (IllegalAccessException e) {
      throw new ServiceException("fail to initialize property manager.", e);
    }

    setPrepared();
  }

  @Override
  public void initializeOnThread(InitialEvent event) {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean hasProperty(PropertyEnum key, boolean... refresh) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String getProperty(PropertyEnum key, boolean... refresh) {
    // TODO Auto-generated method stub
    return properties.getProperty(key.toString());
  }

  @Override
  public Map<String, String> getProperties(PropertyEnum... keys) {
    // TODO Auto-generated method stub
    return null;
  }

  private synchronized Properties readProperties() throws IOException {
    Properties tempProperties = new Properties();
    FileInputStream in = new FileInputStream(configFile);
    tempProperties.load(in);
    return tempProperties;
  }
}
