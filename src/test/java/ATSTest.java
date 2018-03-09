import org.junit.Before;
import org.junit.Test;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.manager.amq.AMQMessageEntity;
import io.rezoome.manager.amq.AMQMessageHandlerImpl;
import io.rezoome.manager.provider.ManagerProvider;
import junit.framework.TestSuite;

public class ATSTest extends TestSuite{

	@Before
	public void initialize(){
	  ManagerProvider.property().initialize(InitialEvent.RUNTIME);
	  ManagerProvider.clsarrange().initialize(InitialEvent.RUNTIME);
	  ManagerProvider.pushcommand().initialize(InitialEvent.RUNTIME);
	}
	
	@Test
	public void amqMessageTest(){
	}
}
