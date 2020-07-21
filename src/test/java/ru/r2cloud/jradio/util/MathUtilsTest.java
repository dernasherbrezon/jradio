package ru.r2cloud.jradio.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MathUtilsTest {

	@Test
	public void testSincos() {
		float[] complex = new float[2];
		MathUtils.sincos(-168515, complex);
		assertEquals(-0.000246525f, complex[0], 0.0f);
		assertEquals(1.0f, complex[1], 0.0f);
	}

	@Test
	public void testFloatToFixed() {
		assertEquals(1025347913, MathUtils.floatToFixed(1.5f));
	}

	@Test
	public void testFloatToFixed2() {
		float[] complex = new float[2];
		for (int i = 0; i < 8; i++) {
			float angle = (float) (i * Math.PI / 4);
			int fixed = MathUtils.floatToFixed(angle);
			MathUtils.sincos(fixed, complex);
			assertEquals(Math.sin(angle), complex[1], 0.00001f);
			assertEquals(Math.cos(angle), complex[0], 0.00001f);
		}
	}

	@Test
	public void testConvolve() {
		assertArrayEquals(new float[] { 0, 1, 2.5f, 4, 1.5f }, MathUtils.convolve(new float[] { 0, 1, 0.5f }, new float[] { 1, 2, 3 }), 0.0f);
	}

	@Test
	public void testConvolve2() {
		assertArrayEquals(new float[] { 1, 3, 5, 7, 9, 11, 13, 15, 8 }, MathUtils.convolve(new float[] { 1, 2, 3, 4, 5, 6, 7, 8 }, new float[] { 1, 1 }), 0.0f);
	}

	@Test
	public void testLcm() {
		assertEquals(36, MathUtils.lcm(12, 18));
	}

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
