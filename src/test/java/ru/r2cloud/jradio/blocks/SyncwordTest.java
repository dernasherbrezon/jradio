package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SyncwordTest {

	@Test
	public void testSnr() {
		Syncword syncword = new Syncword("010011110101101000110100010000110101010101000010");
		byte[] syncwordSoftBits = new byte[] { -2, 2, -2, -19, 3, 20, 20, 19, -3, 3, -2, 6, 19, -4, 6, -5, -19, -16, 10, 20, -5, 8, -6, -18, -16, 11, -8, -18, -16, -14, 15, 18, -5, 12, -6, 12, -6, 12, -5, 12, -5, 13, -11, -15, -16, -10, 12, -10 };
		assertEquals(5.1616545f, syncword.calculateSnr(syncwordSoftBits), 0.0f);
	}

}
