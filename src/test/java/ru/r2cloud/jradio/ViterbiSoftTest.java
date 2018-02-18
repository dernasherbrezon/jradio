package ru.r2cloud.jradio;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import ru.r2cloud.jradio.fec.Viterbi;
import ru.r2cloud.jradio.fec.ViterbiSoft;

public class ViterbiSoftTest {

	@Test
	public void testEncodeDecode() {
		byte[] original = ViterbiTest.hexStringToByteArray("1ACFFC1D");
		byte[] input = Viterbi.encode(original, (byte) 0x4f, (byte) 0x6d, false);
		byte[] soft = convertToSoft(input);
		// emulate single bit failure
		if (soft[0] == Byte.MAX_VALUE) {
			soft[0] = Byte.MIN_VALUE;
		} else {
			soft[0] = Byte.MAX_VALUE;
		}
		byte[] result = ViterbiSoft.decode(soft, (byte) 0x4f, (byte) 0x6d, false);
		assertArrayEquals(original, result);
	}

	public static byte[] convertToSoft(byte[] input) {
		byte[] soft = new byte[input.length * 8];
		// convert to soft
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < 8; j++) {
				if ((input[i] & (1 << (7 - j))) > 0) {
					soft[i * 8 + j] = Byte.MAX_VALUE;
				} else {
					soft[i * 8 + j] = Byte.MIN_VALUE;
				}
			}
		}
		return soft;
	}

}
