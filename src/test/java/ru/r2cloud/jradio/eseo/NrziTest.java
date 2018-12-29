package ru.r2cloud.jradio.eseo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NrziTest {
	
	@Test
	public void testSuccess() {
		byte[] data = new byte[] { (byte) 0b1111_1000, (byte) 0b1011_1010, (byte) 0b1101_0101};
		Nrzi.decode(data);
		assertEquals(0b01111011, data[0]);
		assertEquals(0b00011000, data[1]);
		assertEquals(0b01000000, data[2]);
	}
}
