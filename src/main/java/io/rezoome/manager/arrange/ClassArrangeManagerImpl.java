package io.rezoome.manager.arrange;

import java.util.Map;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.core.annotation.ManagerType;
import io.rezoome.core.entity.Action;
import io.rezoome.core.entity.Entity;
import io.rezoome.manager.AbstractManager;

@ManagerType("ClassArrange")
public final class ClassArrangeManagerImpl extends AbstractManager implements ClassArrangeManager {
	
	private Map<Class<Entity>, Map<String, Class<Entity>>> classMap = null;
	
	private static class Singleton{
		private static final ClassArrangeManager instance = new ClassArrangeManagerImpl();
	}

	public static ClassArrangeManager getInstance(){
		return Singleton.instance;
	}
	
	@Override
	public void initialize(InitialEvent event) {
		// TODO Auto-generated method stub
		this.setPrepared();
	}

	@Override
	public void initializeOnThread(InitialEvent event) {
		// TODO Auto-generated method stub
	}
	
	protected <T extends Entity> void addClass(Class<T> rootCls, String code, Class<? extends T> entityCls){
//		synchronized(this){
//			if(!classMap.containsKey(rootCls)){
//				classMap.put(rootCls, new HashMap<>());
//			}
//		}
	}

	@Override
	public <T extends Entity> Map<String, Class<? extends T>> getEntityCodeMap(Class<T> cls) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Entity> Map<Class<? extends T>, ? extends Action<? extends T>> getActionMap(Class<T> cls) {
		// TODO Auto-generated method stub
		return null;
	}
}
