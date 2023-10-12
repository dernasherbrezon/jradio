package ru.r2cloud.jradio.sapling;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class SaplingGiganteumBeaconTest {

	@Test
	public void testTelemetry() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("FFFD00004B4E36484343DF8E17060001D8DC08600000AEC00460A003251115108A906C5060F278327EC9776DFFFFFFFFFFFFFFFFFFFF");
		SaplingGiganteumBeacon result = new SaplingGiganteumBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("SaplingGiganteumBeacon.json", result);
	}
	
	@Test
	public void testPojo() {
		assertThat(SaplingGiganteumBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
