package ru.r2cloud.jradio.florsat;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;

public class Floripasat1BeaconTest {

	@Test
	public void testEpsData() throws Exception {
		byte[] data = new byte[] { 1, 48, 80, 89, 48, 69, 70, 83, 105, 0, 105, 64, 3, -32, 1, -72, 4, -112, 17, -94, 0, 9, 7, 24, 0, 69, 0, 0, 4, -111, 0, 51, 14, 67, 4, 22, 12, 64, 1 };
		Floripasat1Beacon beacon = new Floripasat1Beacon();
		beacon.readExternal(data);
		AssertJson.assertObjectsEqual("Floripasat1BeaconEps.json", beacon);
	}

	@Test
	public void testTtcData() throws Exception {
		byte[] data = new byte[] { 2, 48, 80, 89, 48, 69, 70, 83, 70, 76, 79, 82, 73, 80, 65, 83, 65, 84 };
		Floripasat1Beacon beacon = new Floripasat1Beacon();
		beacon.readExternal(data);
		AssertJson.assertObjectsEqual("Floripasat1BeaconTtc.json", beacon);
	}
	
	@Test
	public void testPojo() {
		assertThat(Floripasat1Beacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(OBDHData.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(EPSData.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Floripasat1DownlinkBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Telemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
