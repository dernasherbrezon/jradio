package ru.r2cloud.jradio.amical1;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;
import ru.r2cloud.jradio.AssertJson;

public class Amical1BeaconTest {

	@Test
	public void testRl() throws Exception {
		byte[] data = new byte[] { -126, -96, -120, -90, -88, 104, 108, -92, -90, 98, 110, -90, 64, 108, -82, -110, -120, -118, 98, 64, 98, -82, -110, -120, -118, 100, 64, 99, 3, -16, 61, 85, 50, 59, 82, 76, 59, 49, 54, 48, 52, 56, 53, 56, 55, 49, 53, 59, 51, 50, 57, 57, 59, 49, 50, 52, 57, 51, 59, 52, 59, 48, 59, 48, 120, 55 };
		Amical1Beacon input = new Amical1Beacon();
		input.readBeacon(data);
		AssertJson.assertObjectsEqual("Amical1BeaconRl.json", input);
	}

	@Test
	public void testMs() throws Exception {
		byte[] data = new byte[] { -126, -96, -120, -90, -88, 104, 108, -92, -90, 98, 110, -90, 64, 108, -82, -110, -120, -118, 98, 64, 98, -82, -110, -120, -118, 100, 64, 99, 3, -16, 61, 86, 49, 59, 77, 83, 59, 49, 54, 48, 52, 56, 51, 49, 49, 52, 52, 59, 53, 54, 59, 48, 59, 45, 49, 51, 52, 54 };
		Amical1Beacon input = new Amical1Beacon();
		input.readBeacon(data);
		AssertJson.assertObjectsEqual("Amical1BeaconMs.json", input);
	}

	@Test
	public void testUnknownData() throws Exception {
		byte[] data = new byte[] { -126, -96, -120, -90, -88, 104, 108, -92, -90, 98, 110, -90, 64, 108, -82, -110, -120, -118, 98, 64, 98, -82, -110, -120, -118, 100, 64, 99, 3, -16, 61, 73, 77, 67, 95, 65, 67, 75, 95, 79, 75, 59, 54, 59, 54, 49 };
		Amical1Beacon input = new Amical1Beacon();
		input.readBeacon(data);
		AssertJson.assertObjectsEqual("Amical1BeaconUnknown.json", input);
	}

	@Test
	public void testCpu() throws Exception {
		byte[] data = new byte[] { -126, -96, -120, -90, -88, 104, 108, -92, -90, 98, 110, -90, 64, 108, -82, -110, -120, -118, 98, 64, 98, -82, -110, -120, -118, 100, 64, 99, 3, -16, 61, 67, 85, 95, 76, 59, 76, 79, 71, 59, 49, 54, 48, 52, 56, 51, 48, 55, 50, 49, 59, 51, 50, 54, 49, 59, 50, 56, 56, 51, 50, 59, 48, 120, 57, 48, 48, 48 };
		Amical1Beacon input = new Amical1Beacon();
		input.readBeacon(data);
		AssertJson.assertObjectsEqual("Amical1BeaconCpu.json", input);
	}

	@Test
	public void testPojo() {
		assertPojoMethodsFor(Amical1Beacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(AocsStatus.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(CommunicationFlags.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(CommunicationModuleStatus.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(ComputingUnitFlags.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(ComputingUnitStatus.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(M1Flags.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(M1Type.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(PowerStatus.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}

}
