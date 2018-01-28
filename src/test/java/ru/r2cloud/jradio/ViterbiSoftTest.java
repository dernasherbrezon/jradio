package ru.r2cloud.jradio;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import ru.r2cloud.jradio.fec.ViterbiSoft;

public class ViterbiSoftTest {

//	expected: "035d9dc0";
//	got from channel: "035c7ac0";

	@Test
	public void testDecode() {
		byte[] input = new byte[] { 0, 0, 0, 0, 0, 0, (byte) 130, (byte) 255, 100, (byte) 255, 0, (byte) 255, (byte) 255, (byte) 255, 0, 120, 120, (byte) 130, (byte) 130, (byte) 255, (byte) 255, 120, (byte) 130, 120, (byte) 255, (byte) 255, 0, 0, 0, 0, 0, 0 };
		byte[] expected = new byte[] { (byte) 0x1a };
		byte[] result = ViterbiSoft.decode(input, (byte) 0x4f, (byte) 0x6d, false);
		assertArrayEquals(expected, result);
	}

}
