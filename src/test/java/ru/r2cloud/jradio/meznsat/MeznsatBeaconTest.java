package ru.r2cloud.jradio.meznsat;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class MeznsatBeaconTest {

	@Test
	public void testUnknown() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("826C709A9240E0826C709AB4406103F04D5A0100004D657A6E534154");
		MeznsatBeacon result = new MeznsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("MeznsatBeaconUnknown.json", result);
	}

	@Test
	public void testSuccess() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("826C709A9240E0826C709AB4406103F04D455A4E534154031601091A086500000016008A4036011400060006002D00610206047C076B0A016300CEFF75FF0000000000005E02A3500000642B0000AD230000CE440000C7570000CB250000");
		MeznsatBeacon result = new MeznsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("MeznsatBeacon.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(MeznsatBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(MeznsatTelemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
