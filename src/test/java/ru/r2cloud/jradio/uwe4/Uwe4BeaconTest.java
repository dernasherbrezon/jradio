package ru.r2cloud.jradio.uwe4;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class Uwe4BeaconTest {

	@Test
	public void testSuccess() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("888860AAAE8A6088A060AAAE90E103F0258214BC020101000E2E1F0005AF23FF73C779720100F00000031F08304D005D40065F075BA100701060004C10140511080E0D0F0F1D108BCDFF");
		Uwe4Beacon result = new Uwe4Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Uwe4Beacon.json", result);
	}

	@Test
	public void testUnknownpayload() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("888860AAAE8A6088A060AAAE90E103F0679A17BC4B0201010101060242799BBDC7797201033935309403");
		Uwe4Beacon result = new Uwe4Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Uwe4BeaconUnknown.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(Uwe4Beacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Telemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
