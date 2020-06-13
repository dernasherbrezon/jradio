package ru.r2cloud.jradio.polyitan1;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;
import ru.r2cloud.jradio.AssertJson;

public class PolyItan1BeaconTest {

	@Test
	public void testBeacon0() throws Exception {
		byte[] data = new byte[] { -94, -90, -88, 64, 64, 64, -32, -118, -102, 96, -86, -106, -96, 97, 3, -16, 0, 67, 81, 32, 100, 101, 32, 69, 77, 48, 85, 75, 80, 73, 32, 81, 83, 76, 32, 118, 105, 97, 32, 85, 84, 52, 85, 90, 66, 32, 107 };
		PolyItan1Beacon result = new PolyItan1Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("PolyItan1Beacon0.json", result);
	}

	@Test
	public void testBeacon1() throws Exception {
		byte[] data = new byte[] { -94, -90, -88, 64, 64, 64, -32, -118, -102, 96, -86, -106, -96, 97, 3, -16, 1, 0, 0, 0, -87, -98, 20, -36, 94, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 22, 0, 9, 0, 0, 0, 32, 0, 1, 15, 37, -112, -61, -3, -22, -106, 40, -56, -11, 12, -51, -1, -47, -1, -36, -1, -127, 0, 71, 0, 71, 4, 0, 0, 0, 0, -118, 4, -29, 3, 40, 16 };
		PolyItan1Beacon result = new PolyItan1Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("PolyItan1Beacon1.json", result);
	}

	@Test
	public void testBeacon2() throws Exception {
		byte[] data = new byte[] { -94, -90, -88, 64, 64, 64, -32, -118, -102, 96, -86, -106, -96, 97, 3, -16, 2, 3, 0, -90, 20, -36, 94, 10, 1, 73, 1, -31, 12, -117, 12, -31, 12 };
		PolyItan1Beacon result = new PolyItan1Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("PolyItan1Beacon2.json", result);
	}

	@Test
	public void testPojo() {
		assertPojoMethodsFor(PolyItan1Beacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Beacon1.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Beacon2.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}
}
