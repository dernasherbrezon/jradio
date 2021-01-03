package ru.r2cloud.jradio.opssat;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;
import ru.r2cloud.jradio.AssertJson;

public class OpsSatBeaconTest {

	@Test
	public void testUnknown() throws Exception {
		byte[] data = new byte[] { 11, 0, 0, 2, -124, 1, 0, 1, 12, 0, 0, 0, 0, 0, -86, 101, -33, 7, -1, 65 };
		OpsSatBeacon result = new OpsSatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("OpsSatBeaconUnknown.json", result);
	}

	@Test
	public void testPojo() {
		assertPojoMethodsFor(OpsSatBeacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Telemetry.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}
}
