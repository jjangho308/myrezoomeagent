

 import org.junit.Before;
import org.junit.Test;

import io.rezoome.core.ServiceInitializer;
import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.manager.amq.AMQMessageEntity;
import io.rezoome.manager.amq.AMQMessageHandlerImpl;
import junit.framework.TestSuite;

public class AllTests extends TestSuite{

	@Before
	public void initialize(){
		ServiceInitializer.initialize(InitialEvent.RUNTIME);
	}
	@Test
	public void amqMessageTest(){
		AMQMessageEntity msg = new AMQMessageEntity("asdkfjlhsd");
		AMQMessageHandlerImpl.getInstance().handleMessage(msg);
	}
}