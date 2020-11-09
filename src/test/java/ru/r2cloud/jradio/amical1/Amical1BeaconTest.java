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
	public void testCur() throws Exception {
		byte[] data = new byte[] { -126, -96, -120, -90, -88, 104, 108, -92, -90, 98, 110, -90, 64, 108, -82, -110, -120, -118, 98, 64, 98, -82, -110, -120, -118, 100, 64, 99, 3, -16, 61, 67, 85, 95, 82, 59, 76, 79, 71, 59, 49, 54, 48, 52, 55, 53, 57, 55, 56, 54, 59, 53, 53, 51, 54, 59, 53, 51, 56, 59, 48, 120, 57, 48, 48, 48 };
		Amical1Beacon input = new Amical1Beacon();
		input.readBeacon(data);
		AssertJson.assertObjectsEqual("Amical1BeaconCur.json", input);
	}
	
	@Test
	public void testM1Log() throws Exception {
		byte[] data = new byte[] { -126, -96, -120, -90, -88, 104, 108, -92, -90, 98, 110, -90, 64, 108, -82, -110, -120, -118, 98, 64, 98, -82, -110, -120, -118, 100, 64, 99, 3, -16, 61, 77, 49, 59, 76, 79, 71, 59, 49, 54, 48, 52, 55, 53, 57, 55, 55, 57, 59, 51, 56, 59, 54, 49, 51, 48, 53, 49, 55, 59, 51, 51, 53, 48, 59, 54 };
		Amical1Beacon input = new Amical1Beacon();
		input.readBeacon(data);
		AssertJson.assertObjectsEqual("Amical1BeaconM1log.json", input);
	}

	@Test
	public void testErLog() throws Exception {
		byte[] data = new byte[] { -126, -96, -120, -90, -88, 104, 108, -92, -90, 98, 110, -90, 64, 108, -82, -110, -120, -118, 98, 64, 98, -82, -110, -120, -118, 100, 64, 99, 3, -16, 61, 69, 82, 59, 76, 79, 71, 59, 49, 54, 48, 52, 49, 53, 57, 52, 53, 48, 59, 51, 56, 59, 49, 48, 55, 49, 53, 59, 52, 48, 59, 49, 48, 48, 59, 52, 55, 53, 59, 54, 50 };
		Amical1Beacon input = new Amical1Beacon();
		input.readBeacon(data);
		AssertJson.assertObjectsEqual("Amical1BeaconErlog.json", input);
	}

	@Test
	public void testM1flags() throws Exception {
		byte[] data = new byte[] { -126, -96, -120, -90, -88, 104, 108, -92, -90, 98, 110, -90, 64, 108, -82, -110, -120, -118, 98, 64, 98, -82, -110, -120, -118, 100, 64, 99, 3, -16, 61, 77, 49, 59, 70, 76, 65, 71, 83, 59, 49, 54, 48, 51, 54, 56, 54, 54, 53, 52, 59, 48, 120, 49, 48, 48, 48, 48, 48, 48, 48, 48, 51, 49, 53, 101, 59 };
		Amical1Beacon input = new Amical1Beacon();
		input.readBeacon(data);
		AssertJson.assertObjectsEqual("Amical1BeaconM1flags.json", input);
	}
	
	@Test
	public void testEmmn() throws Exception {
		byte[] data = new byte[] { -126, -96, -120, -90, -88, 104, 108, -92, -90, 98, 110, -90, 64, 108, -82, -110, -120, -118, 98, 64, 98, -82, -110, -120, -118, 100, 64, 99, 3, -16, 61, 69, 77, 59, 77, 78, 59, 49, 54, 48, 51, 54, 51, 55, 56, 49, 48, 59, 49, 50, 51, 55, 52, 59, 51, 49, 51, 55, 59, 52, 48, 59, 49, 54, 59, 49, 55, 55, 53, 59, 50, 59, 51, 51, 52 };
		Amical1Beacon input = new Amical1Beacon();
		input.readBeacon(data);
		AssertJson.assertObjectsEqual("Amical1BeaconEmmn.json", input);
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
