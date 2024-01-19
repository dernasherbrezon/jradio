package ru.r2cloud.jradio.suchai2;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.csp.CspBeacon;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class Suchai2BeaconTest extends CspBeacon {

	@Test
	public void testTelemetry() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"82A4390100000D0100000001000000003B1A3F92000000013B1A3F920000006400002276000000000000227B0065A33100000037000000000000000000000000000000000000000062067F4B0000202D0000000500000060FFFFFFFFFFFFFFE7000000020000000000000012000000010000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000BA363F062EAD49A9");
		Suchai2Beacon result = new Suchai2Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Suchai2BeaconTelemetry.json", result);
	}

	@Test
	public void testUnknown() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("CAA7C000FFB0FFACFF7C075F0000006200000000000049D2000000000102CB00000100B9FCE533FF73000001F99900001471016FB0E90001340E");
		Suchai2Beacon result = new Suchai2Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Suchai2BeaconUnknown.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(Suchai2Beacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(TelemetryStatus.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
