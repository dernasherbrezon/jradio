package ru.r2cloud.jradio.vzlusat;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class Vzlusat2BeaconTest {

	@Test
	public void testSuccess() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("83A49200565A4C555341542D326320AEEB000000C300000100205D0208015C000200210026B40E00030F083FF1504F");
		Vzlusat2Beacon result = new Vzlusat2Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Vzlusat2BeaconTelemetry.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(Vzlusat2Beacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Vzlusat2Telemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Vzlusat2Drop.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
