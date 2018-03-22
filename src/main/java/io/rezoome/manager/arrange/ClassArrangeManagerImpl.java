package io.rezoome.manager.arrange;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.core.annotation.ManagerType;
import io.rezoome.core.entity.Action;
import io.rezoome.core.entity.Entity;
import io.rezoome.lib.json.util.ConstructorUtils;
import io.rezoome.manager.AbstractManager;

/**
 * Implementation of {@link ClassArrangeManager}. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
@ManagerType(value = "ClassArrange", initPriority = 20)
public final class ClassArrangeManagerImpl extends AbstractManager implements ClassArrangeManager {

  private static final Logger LOG = LoggerFactory.getLogger("AGENT_LOG");

  private static class Singleton {
    private static final ClassArrangeManager instance = new ClassArrangeManagerImpl();
  }

  public static ClassArrangeManager getInstance() {
    return Singleton.instance;
  }

  @Override
  public void initialize(InitialEvent event) {
    ClassMapper.setEntityKeyMap(this);
    LOG.info("{} Init Complete.", this.getClass());
    this.setPrepared();
  }

  @Override
  public void initializeOnThread(InitialEvent event) {
    // TODO Auto-generated method stub
  }

  protected <T extends Entity> void mapCodeEntity(Class<T> rootCls, String code, Class<? extends T> entityCls) {
    // synchronized(this){
    // if(!classMap.containsKey(rootCls)){
    // classMap.put(rootCls, new HashMap<>());
    // }
    // }
  }

  protected <T extends Entity, U extends Action<T>> void mapEntityAction(Class<? super T> superCls, Class<T> entity, Class<U> action) {

  }

  @Override
  public <T extends Entity> Map<String, Class<? extends T>> getEntityCodeMap(Class<T> cls) {
    Map<String, Class<? extends T>> result = new HashMap<>();
    for (Entry<String, Class<? extends Entity>> entry : entityKeyMap.get(cls).entrySet()) {
      result.put(entry.getKey(), entry.getValue().asSubclass(cls));
    }
    return Collections.unmodifiableMap(result);
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T extends Entity, U extends Action<? super T>> Map<Class<? extends T>, U> getActionMap(Class<T> entityCls, Class<U> actionCls) {
    Map<Class<? extends T>, U> result = new HashMap<>();
    for (Entry<Class<? extends Entity>, Action<? super Entity>> entry : entityActionMap.get(entityCls).entrySet()) {
      result.put(entry.getKey().asSubclass(entityCls), (U) entry.getValue());
    }
    return Collections.unmodifiableMap(result);
  }

  private static final Map<Class<? extends Entity>, Map<String, Class<? extends Entity>>> entityKeyMap = new HashMap<>();
  private static final Map<Class<? extends Entity>, Map<Class<? extends Entity>, Action<? super Entity>>> entityActionMap = new HashMap<>();

  /**
   * Key-Entity map에 항목 추가.
   * 
   * @author TACKSU
   * @since 180308
   * 
   * @param rootEntityInterface
   * @param key
   * @param entityCls
   * @return
   */
  <T extends Entity, U extends T> void addEntityKeyMap(Class<T> rootEntityInterface, String key, Class<U> entityCls) {
    Map<String, Class<? extends Entity>> keyMap;
    if (!entityKeyMap.containsKey(rootEntityInterface)) {
      keyMap = new HashMap<>();
      entityKeyMap.put(rootEntityInterface, keyMap);
    } else {
      keyMap = entityKeyMap.get(rootEntityInterface);
    }

    keyMap.put(key, entityCls);
  }

  /**
   * Entity-Action map에 항목 추가
   * 
   * @author TACKSU
   * @since 180308
   * 
   * @param rootEntityInterface
   * @param entity
   * @param action
   */
  @SuppressWarnings("unchecked")
  <T extends Entity, U extends T, V extends Action<? extends T>> void addActionMap(Class<T> rootEntityInterface, Class<U> entity, Class<V> action) {
    Map<Class<? extends Entity>, Action<? super Entity>> actionMap;
    if (!entityActionMap.containsKey(rootEntityInterface)) {
      actionMap = new HashMap<>();
      entityActionMap.put(rootEntityInterface, actionMap);
    } else {
      actionMap = entityActionMap.get(rootEntityInterface);
    }
    try {
      actionMap.put(entity.asSubclass(Entity.class), (Action<? super Entity>) ConstructorUtils.newInstance(action));
    } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException e) {
      e.printStackTrace();
    }
  }
}
