package ru.r2cloud.jradio.fec;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class Bch15Test {

	@Test
	public void testSuccess() throws Exception {
		byte[] in = new byte[] { 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0 };
		Bch15.decode(in, 0, 7);
		byte[] expected = new byte[] { 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0 };
		assertArrayEquals(expected, in);
	}

}
