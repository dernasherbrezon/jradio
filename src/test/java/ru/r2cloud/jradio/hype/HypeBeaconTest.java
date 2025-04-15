package ru.r2cloud.jradio.hype;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class HypeBeaconTest {

	@Test
	public void testSuccess() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("004859504500006EAA05190A3215242400000501000000370013BE3400000000000040");
		HypeBeacon result = new HypeBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("HypeBeacon.json", result);
	}
	
	@Test
	public void testPojo() {
		assertThat(HypeBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(HypeTelemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
	
}
