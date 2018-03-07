

 import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import io.rezoome.lib.json.JSON;
import io.rezoome.manager.amq.AMQMessageEntity;
import io.rezoome.manager.amq.AMQMessageHandlerImpl;
import junit.framework.TestSuite;

public class AllTests extends TestSuite{

	@Before
	public void initialize(){
//		ServiceInitializer.initialize(InitialEvent.RUNTIME);
	}
	
	@Test
	@Ignore
	public void amqMessageTest(){
		AMQMessageEntity msg = new AMQMessageEntity();
		AMQMessageHandlerImpl.getInstance().handleMessage(msg);
	}
	
	@Test
	public void AMQMessageParseTest(){
		AMQMessageEntity entity = null;
		entity = new AMQMessageEntity();
		String msg = "{\r\n" + 
				"  cmd : \"SearchRecord\",\r\n" + 
				"  mid : \"leifajlsif\",\r\n" + 
				"  token : \"welajslkdjfasdf\",\r\n" + 
				"  args : {\r\n" + 
				"    username : 'ATS',\r\n" + 
				"    birth : '1987-03-18',\r\n" + 
				"    gender : 1,\r\n" + 
				"    phone : '010-6464-4554'\r\n" + 
				"  }\r\n" + 
				"}";
		
		entity = JSON.fromJson(msg, AMQMessageEntity.class);
		System.out.println(entity.toString());
	}
}