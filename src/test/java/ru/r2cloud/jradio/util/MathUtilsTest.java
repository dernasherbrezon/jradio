package ru.r2cloud.jradio.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MathUtilsTest {

	@Test
	public void testRounding() {
		assertEquals(3.4f, MathUtils.round(3.45f, 1), 0.0f);
	}

	@Test
	public void testNorm() {
		assertEquals(25.0, MathUtils.norm(new float[] { 3.0f, 4.0f }), 0.0f);
	}

	@Test
	public void testExp() {
		float[] result = MathUtils.exp(0, (float) Math.PI);
		assertEquals(-1.0, result[0], 0.00001);
		assertEquals(0.0, result[1], 0.00001);
	}

	@Test
	public void testAbs() {
		assertEquals(1.41421f, MathUtils.abs(1.0f, 1.0f), 0.00001);
	}

	@Test
	public void testReverseBitsInByte() {
		assertEquals(0b1100_0101, MathUtils.reverseBitsInByte(0b1010_0011));
	}

}
