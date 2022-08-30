package ru.r2cloud.jradio.iris;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class IrisABeaconTest {

	@Test
	public void testTelemetry() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("849C608696AAE0849C60929EA86103F0007B7B00080ACC90001E1003192A9891F000FE02FF8900005146084494A900534C85F981000080C6DD01");
		IrisABeacon result = new IrisABeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("IrisABeacon.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(IrisABeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SecondaryHeader.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Telemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
