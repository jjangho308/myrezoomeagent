
import java.util.Collections;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import io.rezoome.core.ServiceInitializer;
import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.lib.json.JSON;
import io.rezoome.manager.amq.AMQMessageEntity;
import io.rezoome.manager.amq.AMQMessageHandlerImpl;
import io.rezoome.manager.provider.ManagerProvider;
import io.rezoome.manager.pushcommand.entity.PushCommandAction;
import io.rezoome.manager.pushcommand.entity.PushCommandEntity;
import junit.framework.Assert;
import junit.framework.TestSuite;

public class AllTests extends TestSuite {

	@Before
	public void initialize() {
		// ServiceInitializer.initialize(InitialEvent.RUNTIME);
		ManagerProvider.property().initialize(InitialEvent.RUNTIME);
		ManagerProvider.clsarrange().initialize(InitialEvent.RUNTIME);
		ManagerProvider.pushcommand().initialize(InitialEvent.RUNTIME);
	}

	@Test
	@Ignore
	public void amqMessageTest() {
		AMQMessageEntity msg = new AMQMessageEntity();
		AMQMessageHandlerImpl.getInstance().handleMessage(msg);
	}

	@Test
	public void AMQMessageParseTest() {
		AMQMessageEntity entity = null;
		entity = new AMQMessageEntity();
		String msg = "{\r\n" + "  cmd : \"Search\",\r\n" + "  mid : \"leifajlsif\",\r\n"
				+ "  token : \"welajslkdjfasdf\",\r\n" + "  args : {\r\n" + "    username : 'ATS',\r\n"
				+ "    birth : '1987-03-18',\r\n" + "    gender : 1,\r\n" + "    phone : '010-6464-4554'\r\n"
				+ "  }\r\n" + "}";

		entity = JSON.fromJson(msg, AMQMessageEntity.class);
		Assert.assertEquals(entity.toString(), msg.trim());
	}

	@Test
	public void checkedMapTest() {
	}
}