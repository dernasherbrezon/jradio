package ru.r2cloud.jradio.sat3cat1;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;

public class Sat3Cat1BeaconTest {

	@Test
	public void testDecodeState() throws Exception {
		byte[] data = new byte[] { 0, -80, 92, 2, 91, 121, 88, -1, 22, 33, -44, 22, 3, -79, 22, 33, -44, 22, 33, -44, 22, 34, 2, 22, 34, 14, 22, 31, -17, 22, 0, 37, 22, 0, 9, 22, 0, 0, 22, 0, 4, 22, 0, 47, 22, 0, 15, 22, 1, 28, 22, 1, 14, 22, 1, 23, 22, 1, 24, 22, 1, 39, 22, 1, 20, 22, 0, 11, 22, 0, 103, 22, 0, 0, 22, 0, 0, 22, 0, 22, 22, 1, 95, 22, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		Sat3Cat1Beacon beacon = new Sat3Cat1Beacon();
		beacon.readExternal(data);
		AssertJson.assertObjectsEqual("Sat3Cat1Beacon-State.json", beacon);
	}

}
