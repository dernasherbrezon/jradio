package ru.r2cloud.jradio.lucky7;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class Lucky7BeaconTest {

	@Test
	public void testTelemetry() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("000000003FBE4F4B305341544C55434B5937CC7F0032BE181420AF4007D107D107D100");
		Lucky7Beacon result = new Lucky7Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Lucky7BeaconFull.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(Lucky7Beacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
