package ru.r2cloud.jradio.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LfsrTest {

	@Test
	public void test() {
		Lfsr lfsr = new Lfsr(0x21, 0x1FF, 8);
		int[] expected = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1 };
		for (int i = 0; i < 20; i++) {
			assertEquals(expected[i], lfsr.nextBit());
		}
	}

}
