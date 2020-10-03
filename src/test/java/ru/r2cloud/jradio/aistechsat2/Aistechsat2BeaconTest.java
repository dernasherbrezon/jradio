package ru.r2cloud.jradio.aistechsat2;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;

public class Aistechsat2BeaconTest {

	@Test
	public void testObc() throws Exception {
		byte[] data = new byte[] {-126, -13, -99, 0, 0, 17, 30, 0, 14, 8, 1, -1, -1, 0, -121, 16, 3, 25, -97, 32, 3, -24, 72, 105, 4, 23, 60, -35, 0, 1, 0, 0, 1, 0, 3, 75, 95, 119, 121, -105, 0, 1, 1, 0, 0, 83, 0, 82, -126, 59, 40, 56, -60, 5, 68, -20, 68, 48, -69, 20, 66, -84, 78, -59, 0, -99, -102, -36, 0, 0, 7, -8, 0, 33, 86, -45, -63, 2, -88, -54, -65, -23, -40, -99, -65, -57, -113, 120, 65, 8, -73, 30, 0, 0, 0, 0, 3, -32, 0, 0, 0, 0, 0, 0, 2, 77, 0, 0, 0, 0, 0, 0, 0, 81, 96, 0, 0, 0, 1, 2, 118, 51, 46, 48, 46, 48, 45, 97, 118, 114, 51, 50, 45, 50, 48, 49, 56, 48, 57, 49, 52, 45, 49, 54, 48, 54, 0, 0, 0, 23, 5, 0, 0, 0, 54, -68, -49};
		Aistechsat2Beacon beacon = new Aistechsat2Beacon();
		beacon.readBeacon(data);
		AssertJson.assertObjectsEqual("Aistechsat2Beacon-obc.json", beacon);
	}
	
}
