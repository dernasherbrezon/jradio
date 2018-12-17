package ru.r2cloud.jradio.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DeinterleaveTest {

	@Test
	public void testDeinterleaveBits() {
		byte[] data = new byte[] { 0b0010_0101, 0b0101_1100 };
		byte[] result = Deinterleave.deinterleaveBits(data);
		assertEquals(data.length, result.length);
		assertEquals(0b0100_0010, result[0]);
		assertEquals(0b0011_1110, result[1]);
	}

}
