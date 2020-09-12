package ru.r2cloud.jradio.nexus;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class NexusBeaconTest {

	@Test
	public void testImageBeacon() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("9C8AB0AAA6406094A662B282AC6103F0C1000494812800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A2800A280000");
		NexusBeacon result = new NexusBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("NexusBeaconImage.json", result);
	}

	@Test
	public void testRealtime() throws Exception {
		byte[] data = new byte[] {-100, -118, -80, -86, -90, 64, 96, -108, -90, 98, -78, -126, -84, 97, 3, -16, -95, 0, 5, 74, 72, 3, -98, -84, -66, 83, 1, 116, 1, 1, 1, 12, 124, 1, -48, 0, 5, 0, 4, 0, 4, 0, 5, 0, 6, 0, 9, 9, -80, 9, -118, 8, -22, 8, -8, 9, 19, 9, 28, 9, -102, 9, -119, 10, -23, 12, 99, 11, 52, 10, -2, 11, 73, 11, 7, 9, 82, 9, 80, -55, -40, -23, -26, -102, -36, 23, -50, 17, -13, 3, -93, 6, -56, 8, 93, 8, 13, 7, -28, 3, -98, -84, -46, 83, 1, 116, 1, 1, 1, 12, 123, 1, -7, 0, 13, 0, 16, 0, 5, 0, 5, 0, 5, 0, 5, 9, -84, 9, 101, 8, -47, 8, -17, 9, 12, 8, -72, 9, -117, 9, -122, 10, -27, 11, -6, 11, 47, 10, -66, 11, 64, 10, -12, 9, 83, 9, 63, -89, -40, 55, -26, -77, -36, 23, -90, 18, 48, 3, -85, 4, -6, 7, -99, 10, -81, 7, -29, 0, 0, 0, 0, 0, 0, 0};
		NexusBeacon result = new NexusBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("NexusBeaconRealtime.json", result);
	}
	
	@Test
	public void testHistorical() throws Exception {
		byte[] data = new byte[] {-100, -118, -80, -86, -90, 64, 96, -108, -90, 98, -78, -126, -84, 97, 3, -16, -96, 0, 3, 75, -8, 4, 15, -21, 18, 20, 1, -123, 1, 1, 1, 12, -93, 1, -14, 0, 0, 0, 0, 0, 38, 0, 0, 3, 22, 0, 109, 10, -38, 10, 12, 10, 38, 10, -128, 10, 103, 8, -107, 10, -53, 10, -16, 12, 44, 10, -61, 12, 29, 10, -57, 12, 111, 11, -113, 10, -43, 10, 96, -33, -58, -61, -46, 125, -57, 35, -46, 25, -55, 4, -125, 7, -30, 9, 76, 13, 8, 7, 0};
		NexusBeacon result = new NexusBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("NexusBeaconHistorical.json", result);
	}

	@Test
	public void testFiData() throws Exception {
		byte[] data = new byte[] {-100, -118, -80, -86, -90, 64, 96, -108, -90, 98, -78, -126, -84, 97, 3, -16, -80, 0, 1, -67, 99, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 3};
		NexusBeacon result = new NexusBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("NexusBeaconFiData.json", result);
	}
	
	@Test
	public void testStatusData() throws Exception {
		byte[] data = new byte[] {-100, -118, -80, -86, -90, 64, 96, -108, -90, 98, -78, -126, -84, 97, 3, -16, -64, 0, 2, -114, -41, 50, 109, -89, -120, 2, 5, 1, 0, 0, 70, 2, 0, 0, -56, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 50, 52, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 50, 53, -99, -89, -120, 2, 5, 1, 0, 0, 35, 2, 0, 0, -44, 50, 54, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 50, 55, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 50, 56, -50, -89, -120, 2, 5, 1, 0, 0, 11, 2, 0, 0, -32, 50, 57, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 50, 58, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 50, 59, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0};
		NexusBeacon result = new NexusBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("NexusBeaconStatusData.json", result);
	}
	
	@Test
	public void testRomStatusData() throws Exception {
		byte[] data = new byte[] {-100, -118, -80, -86, -90, 64, 96, -108, -90, 98, -78, -126, -84, 97, 3, -16, -64, 0, 2, -128, -41, 0, -1, 15, -16, -1, -1, 127, -37, -1};
		NexusBeacon result = new NexusBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("NexusBeaconRomStatus.json", result);
	}
	
}
