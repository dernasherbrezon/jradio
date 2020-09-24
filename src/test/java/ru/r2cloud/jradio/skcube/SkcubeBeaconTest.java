package ru.r2cloud.jradio.skcube;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;
import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class SkcubeBeaconTest {

	@Test
	public void testCom() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("9E9A66968282E09E9A72A682A86103F003CC0E1E033300020032008F0D7B0F590C440EA3055806030DBE8E418E2A02083E8E881A0100490000");
		SkcubeBeacon result = new SkcubeBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("SkcubeBeaconCom.json", result);
	}

	@Test
	public void testPwr() throws Exception {
		byte[] data = new byte[] { -98, -102, 102, -106, -126, -126, -32, -98, -102, 114, -90, -126, -88, 97, 3, -16, 5, 44, 37, 46, 86, -20, -43, 19, 0, 23, 18, 19, 0, 2, 0, 24, 16, 93, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -48, 0, 0, 0, -96, -1, -60, -1, -47, -1, -49, -1, -31, -1, -51, -1, 0, 14, 1, 14, 9, 0, 100, 0, 100, 0, 100, 0, 72, 0, 72, 0, 0, 0, 0, 0, 21, 8, 47, 8, -1, -1, 0, 0, 17, 0, 60, 3, 22, 0, 25, 0, 30, 0, 56, 5, 76, 0, 37, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
				0, 105, 0, 2, 0, 2, 0, 72, -91, 18, 14, 3, 14, 2, 4, 57, 0, -5, -1, -71, 115, 0, 0, 0, 0, 0, 0, 0, 0, 4, 66, 30, 120, 40, -101, 20, 44, 44, 44, 20, 14, -44, -102, 21, 14, -70, -102, -70, 85 };
		SkcubeBeacon result = new SkcubeBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("SkcubeBeaconPwr.json", result);
	}

	@Test
	public void testCdhs() throws Exception {
		byte[] data = new byte[] { -98, -102, 102, -106, -126, -126, -32, -98, -102, 114, -90, -126, -88, 97, 3, -16, 1, 99, 0, -100, 102, 65, -113, -75, 12, 0, 5, 0, 12, 0, 1, 2, 3, 2, 7, 2, -110, 111, 111, -108, 35, -37, 0, 0, 22, -110, 0, 0, 0, 0, 94, 70, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 25, 0, 0, 0, 0, 0, 0, 0, 88, 110, 1, 0, 94, 70, 0, 0, -56, -121, 2, 0, 120, -92, 12, 0, -61, 26, 0, 0, 0, 55, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, -13, 6, -97, 63, 2, -96, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		SkcubeBeacon result = new SkcubeBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("SkcubeBeaconCdhs.json", result);
	}

	@Test
	public void testAdcs() throws Exception {
		byte[] data = new byte[] { -98, -102, 102, -106, -126, -126, -32, -98, -102, 114, -90, -126, -88, 97, 3, -16, 2, 0, 50, 94, 1, -106, 0, -100, -1, 53, 0, -7, -1, 0, 6, 0, -6, -1, 0, 102, 0, -9, -1, 0, -33, -1, 30, 0, 6, -37, -1, -2, -1, 0, 0, 0, 0, 0, 0, 123, 0, 16, 1, 8, 0, 0, 0, 67, 0, -69, -3, 74, -1, 0, 0, 36, -5, 86, 4, -43, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		SkcubeBeacon result = new SkcubeBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("SkcubeBeaconAdcs.json", result);
	}

	@Test
	public void testPojo() {
		assertPojoMethodsFor(SkcubeBeacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Cdhs.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Adcs.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Com.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Pwr.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}
}
