package io.rezoome.manager.arrange;

import java.util.Map;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.core.annotation.ManagerType;
import io.rezoome.core.entity.Entity;
import io.rezoome.manager.AbstractManager;

@ManagerType("ClassArrange")
public class ClassArrangeManagerImpl extends AbstractManager implements ClassArrangeManager {
	
	private Map<Class<? extends Entity>, Map<String, Class<? extends Entity>>> classMap = null;
	
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
}
