package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ConstellationTest {

	@Test
	public void testBitsPerSymbol() {
		Constellation constellation = new Constellation(new float[] {-1.0f, 0.0f, 1.0f, 0.0f}, new int[] {0, 1}, 2, 1);
		assertEquals(1, constellation.getBitsPerSymbol());
		constellation = new Constellation(new float[] { -1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, -1.0f }, new int[] { 0, 1, 3, 2 }, 4, 1);
		assertEquals(2, constellation.getBitsPerSymbol());
	}
	
}
