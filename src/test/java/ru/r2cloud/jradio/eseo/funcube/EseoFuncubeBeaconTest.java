package ru.r2cloud.jradio.eseo.funcube;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EseoFuncubeBeaconTest {

	@Test
	public void testParse() throws Exception {
		byte[] data = new byte[256];
		data[0] = (byte) 0b11010101;
		data[1] = (byte) 0b00000100;
		EseoFuncubeBeacon beacon = new EseoFuncubeBeacon();
		beacon.readExternal(data);
		assertEquals(0b00000100, beacon.getHeader().getId());
		assertEquals(0b00010101, beacon.getHeader().getFrameType());
	}
}
