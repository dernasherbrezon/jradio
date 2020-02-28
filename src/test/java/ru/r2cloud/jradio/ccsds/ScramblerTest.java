package ru.r2cloud.jradio.ccsds;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class ScramblerTest {

	@Test
	public void testSuccess() {
		byte[] actual = new byte[] { 1, 2, 3, 4, 5, 56, 7, 8, 23, 9, 9, 0, 34 };
		byte[] expected = new byte[] { -2, 74, 13, -60, -97, 53, 119, -76, -103, 37, -102, -83, -123 };
		Scrambler.shuffle(actual);
		assertArrayEquals(expected, actual);
	}

}
