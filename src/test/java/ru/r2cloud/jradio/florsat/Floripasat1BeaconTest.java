package ru.r2cloud.jradio.florsat;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;
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
		assertPojoMethodsFor(Floripasat1Beacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(OBDHData.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(EPSData.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Floripasat1DownlinkBeacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Telemetry.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}
}
