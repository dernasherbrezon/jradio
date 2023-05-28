package ru.r2cloud.jradio.roseycub;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class RoseyCubesatBeaconTest {

	@Test
	public void testMessage() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("A49EA68AB262E0A49EA68AB2626303F03101FFFF556E652045636F6C6520706F7572206C61205669650000000000000000008B2573640000000070FE03C9000000");
		RoseyCubesatBeacon result = new RoseyCubesatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("RoseyCubesatBeacon-message.json", result);
	}
	
	@Test
	public void testUnknown() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("A49EA68AB262E0A49EA68AB2626303F00601FA02004F000E");
		RoseyCubesatBeacon result = new RoseyCubesatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("RoseyCubesatBeacon-unknown.json", result);
	}
	
	@Test
	public void testPojo() {
		assertThat(RoseyCubesatBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(PeriodicMessage.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
