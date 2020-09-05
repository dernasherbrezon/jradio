package ru.r2cloud.jradio.falconsat3;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;

public class Falconsat3BeaconTest {

	@Test
	public void testTime() throws Exception {
		byte[] data = new byte[] { -88, -110, -102, -118, 64, 64, 2, -96, -116, -90, 102, 64, 64, 3, 3, -16, 80, 72, 84, 58, 32, 117, 112, 116, 105, 109, 101, 32, 105, 115, 32, 57, 50, 50, 47, 50, 50, 58, 50, 54, 58, 51, 52, 46, 32, 32, 84, 105, 109, 101, 32, 105, 115, 32, 84, 104, 117, 32, 83, 101, 112, 32, 48, 51, 32, 50, 48, 58, 53, 52, 58, 51, 51, 32, 50, 48, 50, 48, 13, 32 };
		Falconsat3Beacon result = new Falconsat3Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Falconsat3BeaconTime.json", result);
	}

	@Test
	public void testLstat() throws Exception {
		byte[] data = new byte[] { -104, -90, -88, -126, -88, 64, 0, -96, -116, -90, 102, 64, 64, 3, 3, -16, 73, 32, 80, 58, 48, 120, 49, 51, 65, 56, 32, 111, 58, 48, 32, 108, 58, 51, 49, 51, 54, 55, 32, 102, 58, 51, 49, 52, 52, 57, 44, 32, 100, 58, 49, 32, 115, 116, 58, 52, 32, 101, 58, 54, 56, 13 };
		Falconsat3Beacon result = new Falconsat3Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Falconsat3BeaconLstat.json", result);
	}

	@Test
	public void testPblist() throws Exception {
		byte[] data = new byte[] { -96, -124, -104, -110, -90, -88, 0, -96, -116, -90, 102, 64, 64, 23, 3, -16, 80, 66, 58, 32, 69, 109, 112, 116, 121, 46, 13 };
		Falconsat3Beacon result = new Falconsat3Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Falconsat3BeaconPblist.json", result);
	}

	@Test
	public void testFileFrame() throws Exception {
		byte[] data = new byte[] { -94, -90, -88, 64, 64, 64, 2, -96, -116, -90, 102, 64, 64, 23, 3, -69, 2, -21, 59, 0, 0, 16, 108, 52, 0, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 10, 32, 32, 32, 32, 32,
				32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 10, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32,
				32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 114, -21 };
		Falconsat3Beacon result = new Falconsat3Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Falconsat3BeaconFileFrame.json", result);
	}

	@Test
	public void testDirFrame() throws Exception {
		byte[] data = new byte[] { -94, -90, -88, 64, 64, 64, 2, -96, -116, -90, 102, 64, 64, 23, 3, -67, 32, 34, 60, 0, 0, 0, 0, 0, 0, -66, 4, 67, 95, -48, 4, 67, 95, -86, 85, 1, 0, 4, 34, 60, 0, 0, 2, 0, 8, 65, 76, 49, 50, 48, 56, 50, 51, 3, 0, 3, 32, 32, 32, 4, 0, 4, 116, 24, 0, 0, 5, 0, 4, -30, -75, 65, 95, 6, 0, 4, -66, 4, 67, 95, 18, 0, 4, -66, 4, 67, 95, 7, 0, 1, 0, 8, 0, 1, -55, 9, 0, 2, -11, -79, 10, 0, 2, 63, 12, 11, 0, 2, 80, 0, 0, 0, 0, -60, 71 };
		Falconsat3Beacon result = new Falconsat3Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Falconsat3BeaconDirFrame.json", result);
	}

	@Test
	public void testTelemetry() throws Exception {
		byte[] data = new byte[] { -88, -104, -102, -110, 64, 64, 2, -96, -116, -90, 102, 64, 64, 3, 3, -16, 48, 88, 81, 95, 0, -71, 15, 1, -127, 14, 2, 118, 13, 3, 104, 4, 4, 117, 4, 5, 122, 4, 6, 123, 4, 7, -54, 3, 8, -26, 1, 9, 54, 0, 10, 90, 7, 11, 110, 0, 12, 98, 5, 13, 110, 0, 14, 108, 5, 15, 17, 0, 16, 15, 0, 17, 20, 0, 18, -58, 0, 19, -1, 15, 20, -2, 15, 21, -60, 1, 22, -38, 4, 23, 64, 1, 24, 122, 5, 25, 104, 4, 26, 107, 4, 27, 41, 0, 28, -106, 0, 29, -37, 0, 30, 97, 5, 31, 12, 0, 32,
				115, 6, 33, -68, 2, 34, -8, 2, 35, -4, 7, 36, -1, 7, 37, -1, 7, 38, -12, 4, 39, -72, 4, 40, -1, 7, 41, -1, 7, 42, 51, 6, 43, -124, 6, 44, 101, 6, 45, 29, 6, 46, 56, 5, 47, -1, 7, 48, 26, 5, 49, 30, 5, 50, -2, 15, 51, -1, 15, 52, 8, 0, 53, 1, 0, 54, 2, 0, 55, 0, 0, 56, 2, 0, 57, 104, 0, 58, -1, 15, 59, -28, 1, 60, -32, 1, 61, -2, 15, 62, 12, 0, 63, 5, 0, 64, -36, 2, 65, 4, 0, 66, 46, 0, 67, -87, 2, 68, 0, 0, 69, -126, 2, 70, 122, 2, 71, 82, 2 };
		Falconsat3Beacon result = new Falconsat3Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Falconsat3BeaconTelemetry.json", result);
	}

	@Test
	public void testTelemetry2() throws Exception {
		byte[] data = new byte[] { -88, -104, -102, 100, 64, 64, 2, -96, -116, -90, 102, 64, 64, 3, 3, -16, 89, 88, 81, 95, 72, -7, 9, 73, -20, 5, 74, 127, 7, 75, -79, 9, 76, -49, 8, 77, -27, 1, 78, -20, 0, 79, 46, 0, 80, 0, 0, 81, 0, 0, 82, 0, 0, 83, 0, 0, 84, 0, 0, 85, 0, 0, 86, 0, 0, 87, 0, 0, 88, 0, 0, 89, 0, 0, 90, 0, 0, 91, 0, 0, 92, 0, 0, 93, 0, 0, 94, 0, 0, 95, 0, 0, 96, 0, 0, 97, 0, 0, 98, 0, 0, 99, 0, 0, 100, 0, 0, 101, 0, 0, 102, 0, 0, 103, 0, 0, 104, 0, 0, 105, 0, 0, 106, 0, 0, 107,
				0, 0, 108, 0, 0, 109, 0, 0, 110, 0, 0, 111, 0, 0, 112, 0, 0, 113, 0, 0, 114, -15, 7, 115, -3, 7, 116, 24, 4, 117, 116, 5, 118, 106, 5, 119, 97, 5, 120, -90, 5, 121, 118, 5, 122, 100, 5, 123, -100, 5, 124, 105, 5, 125, 106, 5, 126, -92, 5, 127, -110, 5, -128, 0, 0, -127, 0, 0, -126, 0, 0, -125, 0, 0, -124, 0, 0, -123, 0, 0, -122, 0, 0 };
		Falconsat3Beacon result = new Falconsat3Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Falconsat3BeaconTelemetry2.json", result);
	}

	@Test
	public void testTlms() throws Exception {
		byte[] data = new byte[] { -88, -104, -102, -90, 64, 64, 2, -96, -116, -90, 102, 64, 64, 3, 3, -16, 67, 48, 58, 66, 68, 32, 67, 49, 58, 48, 48, 32, 67, 50, 58, 70, 49, 32, 67, 51, 58, 53, 48, 32, 67, 52, 58, 70, 49, 32, 67, 53, 58, 48, 48 };
		Falconsat3Beacon result = new Falconsat3Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Falconsat3BeaconTlms.json", result);
	}

	@Test
	public void testBcr() throws Exception {
		byte[] data = new byte[] { -124, -122, -92, 64, 64, 64, 2, -96, -116, -90, 102, 64, 64, 3, 3, -16, 66, 67, 82, 58, 98, 118, 61, 49, 49, 50, 56, 32, 98, 105, 61, 49, 53, 48, 32, 115, 101, 110, 115, 61, 49, 51, 55, 55, 32, 116, 111, 112, 61, 49, 49, 50, 56, 32, 108, 111, 119, 61, 48, 32, 116, 49, 61, 52, 56, 54, 32, 116, 50, 61, 57, 55, 48, 32, 115, 118, 61, 49, 51, 56, 56, 32, 115, 105, 61, 49, 49, 48 };
		Falconsat3Beacon result = new Falconsat3Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Falconsat3BeaconBcr.json", result);
	}

	@Test
	public void testStatus() throws Exception {
		byte[] data = new byte[] { -90, -88, -126, -88, -86, -90, 0, -96, -116, -90, 102, 64, 64, 23, 3, -16, 66, 58, 32, 52, 54, 52, 57, 48, 48, 55, 51, 56, 13 };
		Falconsat3Beacon result = new Falconsat3Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Falconsat3BeaconStatus.json", result);
	}

	@Test
	public void testStatus2() throws Exception {
		byte[] data = new byte[] { -90, -88, -126, -88, -86, -90, 0, -96, -116, -90, 102, 64, 64, 23, 3, -16, 66, 58, 32, 52, 54, 54, 52, 57, 57, 50, 56, 51, 58 };
		Falconsat3Beacon result = new Falconsat3Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Falconsat3BeaconStatus2.json", result);
	}

	@Test
	public void testBbstat() throws Exception {
		byte[] data = new byte[] { -124, -124, -90, -88, -126, -88, 0, -96, -116, -90, 102, 64, 64, 25, 3, -16, 79, 112, 101, 110, 32, 65, 66, 67, 68, 58, 32 };
		Falconsat3Beacon result = new Falconsat3Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Falconsat3BeaconBbstat.json", result);
	}

	@Test
	public void testTlmc() throws Exception {
		byte[] data = new byte[] { -88, -104, -102, -122, 64, 64, 2, -96, -116, -90, 102, 64, 64, 3, 3, -16, 67, 76, 58, 49, 50, 49 };
		Falconsat3Beacon result = new Falconsat3Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Falconsat3BeaconTlmc.json", result);
	}

	@Test
	public void testNotification() throws Exception {
		byte[] data = new byte[] { -86, -90, -126, -116, -126, 64, 2, -96, -116, -90, 102, 64, 64, 3, 3, -16, 62, 82, 101, 108, 111, 97, 100, 32, 99, 111, 109, 112, 108, 101, 116, 101, 44, 32, 66, 66, 83, 32, 97, 110, 100, 32, 100, 105, 103, 105, 32, 111, 112, 101, 110, 44, 32, 85, 110, 112, 114, 111, 116, 111, 32, 65, 80, 82, 83, 32, 118, 105, 97, 32, 80, 70, 83, 51, 45, 49 };
		Falconsat3Beacon result = new Falconsat3Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Falconsat3BeaconNotification.json", result);
	}

}
