package ru.r2cloud.jradio.eseo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RandomizeTest {

	@Test
	public void testSuccess() {
		byte[] data = new byte[] { (byte) 0b1111_1000, (byte) 0b1011_0101, 0b0010_0010 };
		Randomize.shuffle(data);
		assertEquals((byte) 0b1111_1000, data[0]);
		assertEquals((byte) 0b1011_1010, data[1]);
		assertEquals((byte) 0b1101_0101, data[2]);
	}

}
