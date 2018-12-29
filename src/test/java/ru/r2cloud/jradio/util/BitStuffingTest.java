package ru.r2cloud.jradio.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BitStuffingTest {

	@Test
	public void testDestuff() throws Exception {
		byte[] data = new byte[] { (byte) 0b1111_1000, 0b0101_1010, 0b0010_0000 };
		byte[] result = BitStuffing.destuffOnes(data, 5);
		assertEquals(2, result.length);
		assertEquals((byte) 0b1111_1000, result[0]);
		assertEquals((byte) 0b1011_0100, result[1]);
	}

}
