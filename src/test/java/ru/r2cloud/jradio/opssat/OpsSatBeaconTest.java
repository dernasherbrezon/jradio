package ru.r2cloud.jradio.opssat;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;

public class OpsSatBeaconTest {

	@Test
	public void testUnknown() throws Exception {
		byte[] data = new byte[] { 11, 0, 0, 2, -124, 1, 0, 1, 12, 0, 0, 0, 0, 0, -86, 101, -33, 7, -1, 65 };
		OpsSatBeacon result = new OpsSatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("OpsSatBeaconUnknown.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(OpsSatBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Telemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
