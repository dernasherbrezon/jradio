package ru.r2cloud.jradio.mrc100;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;

public class Mrc100BeaconTest {

	@Test
	public void testSignalling() throws Exception {
		byte[] data = new byte[] { 18, 103, 6, -124, 0, 0, 0, 1, 104, 15, 0, -101, -10, 0, 0, 52, 14, 0, 45, 79, 0, 127, 45, -128, 24, 63, 46, -96, 58, 14, 127, 46, -96, 46, 14, -64, 44, 80, 0, -1, 45, -96, 29, 14, 0, 45, 81, 0, 127, 45, -128, 27, -65, 45, -128, 23, 63, 46, -96, 58, 14, 127, 46,
				-96, 46, 14, -65, 46, -96, 52, 14, -64, 44, 82, 0, -1, 45, -96, 35, 14, 0, 45, 83, 0, 127, 45, -128, 34, -65, 45, -128, 23, 63, 46, -96, 58, 14, 127, 46, -96, 52, 14, -65, 46, -96, 58, 14, -64, 44, 84, 0, -1, 45, -96, 35, 14, 0, 45, 85, -105, -46, 33, -63, 86, -122, 126 };
		Mrc100Beacon result = new Mrc100Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Mrc100Beacon-FileDl.json", result);
	}

}
