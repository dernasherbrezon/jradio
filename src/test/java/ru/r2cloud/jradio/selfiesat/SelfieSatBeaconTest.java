package ru.r2cloud.jradio.selfiesat;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class SelfieSatBeaconTest {

	@Test
	public void testSuccess() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("0500850100100000000001EA206A019B52B4341A474D");
		SelfieSatBeacon result = new SelfieSatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("SelfieSatBeacon.json", result);
	}
	
	@Test
	public void testNotEnoughData() throws Exception {
		byte[] data = { 5, 0, -123, 1, 0, 16, 0, 0, 0, 0, 0, -61, 32, 6, 0, 57, 90, -109, -2, -94, 95, -5 };
		SelfieSatBeacon result = new SelfieSatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("SelfieSatBeacon-corrupted.json", result);
	}
	
	@Test
	public void testPojo() {
		assertThat(SelfieSatBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Housekeeping.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
