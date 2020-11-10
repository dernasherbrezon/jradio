package ru.r2cloud.jradio.armadillo;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;
import ru.r2cloud.jradio.AssertJson;

public class ArmadilloBeaconTest {

	@Test
	public void testSuccess() throws Exception {
		byte[] data = new byte[] { -82, -112, 100, -80, -78, -92, 96, -106, -118, 106, -120, -88, -82, -31, 3, -16, 8, 23, 18, -5, 1, -53, -96, -68, 58, -3, -24, 0, 0, 0, 112, -71, 1, 58, -45, -110, 69, 60, -80, -96, 69, 92, 120, -73, 68, 86, -65, 125, -64, -99, 76, -105, 64, -58, -124, 73, -64, 0, -72, 64, 7, 0, 46, 0, 20, 1, 0, 0, 1, 18, 0, 21, 0, 19, 0, 19, 0, 15, 0, 15, 0, 2, 0, 6, 0, 6, 0, 2, 0, 9, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 79, 104, 104, 104, 44, 32, 116, 104, 101, 32, 83, 117, 110, 46, 32, 73, 39, 109, 32, 103, 111, 110, 110, 97, 32, 109, 101, 101, 116, 32, 116, 104, 101, 32, 83, 117, 110, 46, 32, 79, 104, 32, 110, 111, 33, 32, 87, 104, 97, 116, 39, 108, 108, 32, 73, 32, 115, 97, 121, 63, 32, 39, 72, 105, 33, 32, 72, 105, 32, 83, 117, 110, 33, 39, 32, 79, 104, 32, 98, 111, 121,
				33, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		ArmadilloBeacon result = new ArmadilloBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("ArmadilloBeacon.json", result);
	}

	@Test
	public void testPojo() {
		assertPojoMethodsFor(ArmadilloBeacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}
}
