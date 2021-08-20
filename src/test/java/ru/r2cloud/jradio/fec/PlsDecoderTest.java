package ru.r2cloud.jradio.fec;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlsDecoderTest {

	private PlsDecoder decoder;

	@Test
	public void testSuccess() {
		decoder = new PlsDecoder();
		int actual = decoder.decode(new byte[] { -40, -64, 67, -55, -72, 91, -73, -73, 116, 38, -39, -40, 37, -34, -86, -65, 100, 53, -29, 28, -24, 38, 67, -32, 52, -43, -69, 86, 53, 40, -35, -54, -49, -56, -61, -62, -52, 65, 65, -45, -75, -69, -46, 43, -34, 71, 48, 34, -37, 61, 70, 34, 68, -53,
				-77, -50, 34, -35, 52, -33, 38, 32, 60, 82 }, 0);
		assertEquals(0, actual);
		actual = decoder.decode(new byte[] { -30, 35, 59, 49, -43, -71, -56, 53, 65, -51, -63, 61, 47, 41, -37, 65, 52, -47, -73, -72, -57, -47, 55, 78, 47, 55, -49, -57, 59, -56, -66, 71, -31, 48, -28, 26, -42, -56, 60, 25, -28, 28, -45, -61, -59, -45, 41, -50, -75, -44, 42, -35, 63, 40, -35, 33,
				66, 64, 53, 62, 53, -36, 34, -32 }, 0);
		assertEquals(1, actual);
	}

}
