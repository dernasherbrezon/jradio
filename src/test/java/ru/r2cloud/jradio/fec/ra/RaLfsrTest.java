package ru.r2cloud.jradio.fec.ra;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RaLfsrTest {

	@Test
	public void testSuccess() {
		RaLfsr lfsr = new RaLfsr(65, 32, 33, 64, 6);
		int[] expected = new int[] { 29, 62, 6, 8, 25, 60, 18, 11, 53, 42, 1, 48, 27, 61, 46, 2, 7, 51, 41, 36, 15, 55, 43, 37, 34, 31, 63, 47, 39, 35, 33, 32 };
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], lfsr.next());
		}
		expected = new int[] { 33, 35, 39, 47, 63, 31, 34, 37, 43, 55, 15, 36, 41, 51, 7, 2, 46, 61, 27, 48, 1, 42, 53, 11, 18, 60, 25, 8, 6, 62, 29, 0 };
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], lfsr.prev());
		}
	}

	@Test
	public void testReset() {
		RaLfsr lfsr = new RaLfsr(65, 32, 33, 64, 6);
		assertEquals(29, lfsr.next());
		lfsr.reset();
		assertEquals(29, lfsr.next());
	}

	@Test
	public void testSeqNo2() {
		RaLfsr lfsr = new RaLfsr(105, 8, 11, 64, 6);
		int[] expected = new int[] { 45, 18, 41, 16, 25, 8, 29, 10, 39, 15, 3, 61, 58, 43, 17, 4, 38, 54, 50, 49, 20, 36, 34, 53, 22, 42, 47, 19, 5, 62, 44, 31 };
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], lfsr.next());
		}
	}

}
