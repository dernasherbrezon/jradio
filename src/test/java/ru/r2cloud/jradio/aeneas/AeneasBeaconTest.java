package ru.r2cloud.jradio.aeneas;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;

public class AeneasBeaconTest {

	@Test
	public void testTelemetry() throws Exception {
		byte[] data = new byte[] { -122, -94, 64, 64, 64, 64, 96, -106, -118, 108, -78, -116, -126, 98, -88, -118, -104, -118, -102, 64, 97, 3, -16, 52, 51, 52, 49, 52, 53, 53, 50, 53, 53, 53, 51, 49, 68, 48, 48, 52, 48, 48, 50, 48, 48, 48, 49, 48, 49, 48, 66, 48, 54, 48, 48, 50, 48, 49, 52, 70, 70,
				48, 55, 48, 55, 48, 48, 48, 48, 49, 48, 48, 48, 70, 48, 55, 53, 48, 48, 48, 48, 55, 48, 49, 51, 48, 48, 52, 57, 48, 48, 66, 54, 48, 50, 52, 48, 53, 51, 54, 53 };
		AeneasBeacon result = new AeneasBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("AeneasBeacon-telemetry.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(AeneasBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(AeneasTelemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(AeneasTimestamp.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
