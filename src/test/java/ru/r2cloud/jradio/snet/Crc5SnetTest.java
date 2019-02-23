package ru.r2cloud.jradio.snet;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Crc5SnetTest {

	@Test
	public void testSuccess() {
		byte[] data = new byte[] { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1 };
		assertEquals(12, Crc5Snet.calculateCrc5(data));
	}

}
