package ru.r2cloud.jradio.atl1;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class Atl1BeaconTest {

	@Test
	public void testAtlTelemetry3() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("8393A87B5E010090A87B5E8D7DBFA8E34BD04975505E4E3553010090A87B5E237743A8044C574AC650424E4353010091A87B5E4475E1A77F4B944802507E4DD152010091A87B5ED5735FA9EE4A19499D4FB14D51520000000000000000000000000000000000000000000000000000000000000000009C7FD6BFB9D6C3C22ED9");
		Atl1Beacon result = new Atl1Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Atl1Telemetry3.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(Atl1Beacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(AtlAccuMeasurement.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(AtlTelemetry3.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
