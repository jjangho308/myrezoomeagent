import org.junit.Before;
import org.junit.Test;

import io.rezoome.utils.MeasureUtil;
import junit.framework.TestSuite;

public class ATSTest extends TestSuite {

	@Before
	public void initialize() {
		// ManagerProvider.property().initialize(InitialEvent.RUNTIME);
		// ManagerProvider.clsarrange().initialize(InitialEvent.RUNTIME);
		// ManagerProvider.pushcommand().initialize(InitialEvent.RUNTIME);
	}

	@Test
	public void amqMessageTest() {
	}

	@Test
	public void timeStampTest() {
		Thread.currentThread().setName("Main thread");
		new Thread("Worker thread") {

			@Override
			public void run() {
				try {
					Thread.sleep(3000);
					int number = MeasureUtil.newInstance();
					MeasureUtil.getTimeStamp(number).tick("First stamp");
					Thread.sleep(3000);
					MeasureUtil.getTimeStamp(number).tick("Second stamp");
					Thread.sleep(3000);
					MeasureUtil.getTimeStamp(number).tick("Third stamp");
					MeasureUtil.getTimeStamp(number).end();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
		
		try {
			Thread.sleep(3000);
			int number = MeasureUtil.newInstance();
			MeasureUtil.getTimeStamp(number).tick("First stamp");
			Thread.sleep(3000);
			MeasureUtil.getTimeStamp(number).tick("Second stamp");
			Thread.sleep(3000);
			MeasureUtil.getTimeStamp(number).tick("Third stamp");
			MeasureUtil.getTimeStamp(number).end();
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
