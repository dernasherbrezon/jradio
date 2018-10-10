package ru.r2cloud.jradio.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MathUtilsTest {

	@Test
	public void testExp() {
		float[] result = MathUtils.exp(0, (float)Math.PI);
		assertEquals(-1.0, result[0], 0.00001);
		assertEquals(0.0, result[1], 0.00001);
	}
	
}
