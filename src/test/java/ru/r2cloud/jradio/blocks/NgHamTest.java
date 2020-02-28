package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import ru.r2cloud.jradio.ArrayMessageInput;

public class NgHamTest {

	private NgHam input;

	@Test
	public void testSuccess() throws Exception {
		input = new NgHam(new ArrayMessageInput(new byte[] { 77, -38, 87, -3, 72, 62, -112, -61, 61, 53, -6, -35, 112, -77, -15, -25, -56, -71, 49, 0, 110, 80, -61, 8, -94, -66, 62, 10, 16, -15, -120, -106, -51, -22, -79, -2, -102, -27, -127, -88, 16, 15, 123, 5, -90, 108, -92, -123, 105, 60, -100, -5, 46, -42, 103, -121, 115, 126, 41, 26, 34, -17, -107, 48, -101, -43, -95, -30, 41, 123, 62, 12, 120, 108, 48, 39, -85, 78, 78, -17, -34, 64, 6 }));
		assertArrayEquals(new byte[] { 0, 48, 80, 89, 48, 69, 70, 83, 92, 32, 92, 64, 127, -1, -1, 90, -7, 45, 15, 58, 0, 1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 10, -8, 0, -100, 10, -18, 2, 25, -1, 75, -1, -54, 7, -79, 0, 78, 0, 45, -1, -30, 54, 0, 85, 14, 3, 12 }, input.readBytes());
	}

}
