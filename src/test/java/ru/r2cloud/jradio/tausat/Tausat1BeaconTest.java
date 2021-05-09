package ru.r2cloud.jradio.tausat;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class Tausat1BeaconTest {

	@Test
	public void testBeacon() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("A882AA8EA640E0A882AAA682A86303F0031900326097558C20450164004F01170A3D0A3500000000FFFFFFFE00020002082C05B6004E0054000000000000000300000000046094CAE070");
		Tausat1Beacon result = new Tausat1Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Tausat1Beacon.json", result);
	}
	
	@Test
	public void testPojo() {
		assertThat(Tausat1Beacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Tausat1Telemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
