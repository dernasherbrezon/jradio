package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class WindowTest {

	@Test
	public void testHamming() {
		assertArrayEquals(new float[] { 0.07999999821186066f, 0.5400000214576721f, 1.0f, 0.5400000214576721f, 0.07999999821186066f }, Window.WIN_HAMMING.build(5, 0.0), 0.0f);
	}

	@Test
	public void testGaussian() {
		float[] actual = Window.WIN_GUASSIAN.build(5, 0.4);
		assertArrayEquals(new float[] { 0.13533528f, 0.60653067f, 1.0f, 0.60653067f, 0.13533528f }, actual, 0.0f);
	}

}
