package ru.r2cloud.jradio.eseo.funcube;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EseoFuncubeBeaconTest {

	@Test
	public void testParse() throws Exception {
		byte[] data = new byte[] { (byte) 0b11010101, (byte) 0b00000100 };
		EseoFuncubeBeacon beacon = new EseoFuncubeBeacon();
		beacon.readExternal(data);
		assertEquals(0b11100000, beacon.getSatId());
		assertEquals(0b10101000, beacon.getFrameType());
	}
}
