package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class AdditiveScramblerTest {

	@Test
	public void testSuccess() throws Exception {
		byte[] data = new byte[] { 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0 };
		AdditiveScrambler scrambler = new AdditiveScrambler(0x21, 0x1ff, 8, 8);
		scrambler.shuffle(data);
		byte[] expected = new byte[] { -1, -32, 29, -101, -20, -123, 51, 37, -21, 122, -46, 57, 113, -106, 87, 10 };
		assertArrayEquals(expected, data);
	}

}
