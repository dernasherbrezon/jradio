package ru.r2cloud.jradio.ax25;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;

public class Ax25NoRepeaterBeaconTest {

	@Test
	public void testSuccess() throws Exception {
		Ax25NoRepeaterBeacon beacon = new Ax25NoRepeaterBeacon();
		beacon.readBeacon(new byte[] { -90, -98, -104, -88, -114, -90, 64, -90, -88, -118, -96, 100, 64, 64, 3, -16, 8, 2, -56, 65, 0, 72, 16, 3, 25, 16, 2, 0, 1, -16, -10, 0, 64, -1, 14, -26, 105, -34, 8, -45, 0, 0, -61, 80, 0, 0, 0, 0, 0, 0, 0, 0, 11, -21, -62, 0, 0, 0, 0, 0, 0, 0, 0, 0, 88, -8,
				-123, 78, -60, -63, 112, 118, -35, -45, 119, 65, 40, 55, 52, -73, 0, 0, 0, 3, 58, -56, -2, 15, -1, -2, 1, -17, 0, -8, 14 });
		AssertJson.assertObjectsEqual("Ax25NoRepeaterBeacon.json", beacon);
	}

}
