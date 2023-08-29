package ru.r2cloud.jradio.usp;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;

public class UspBeaconTest {

	@Test
	public void testFullUSPFrame() throws Exception {
		byte[] data = { 8, -1, 27, 0, -92, 100, -126, -100, -116, 64, 96, -92, -90, 96, 96, -90, 64, 111, 0, -16, -31, -1, 2, 0, 1, 0, 3, 0, 0, 38, 6, 61, 86, -113, 23, -116, 104, 84, -30, 119, -60, -1, -65, 0, 0, 0, 0, 0 };
		UspBeacon beacon = new UspBeacon();
		beacon.readBeacon(data);
		AssertJson.assertObjectsEqual("UspBeaconEof.json", beacon);
	}

	@Test
	public void testShortAx25Frame() throws Exception {
		byte[] data = { -92, 100, -126, -100, -116, 64, 96, -92, -90, 96, 96, -90, 64, 111, 0, -16, -31, -1, 2, 0, 1, 0, 3, 0, 0, 38, 6 };
		UspBeacon beacon = new UspBeacon();
		beacon.readBeacon(data);
		AssertJson.assertObjectsEqual("UspBeaconEof.json", beacon);
	}

}
