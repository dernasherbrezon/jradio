package ru.r2cloud.jradio.fec;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import ru.r2cloud.jradio.fec.Viterbi;

public class ViterbiTest {

	@Test
	public void testEncode() {
		byte[] input = hexStringToByteArray("0ec1d49d7082582c93ada7b746ce5a97");
		byte[] expected = hexStringToByteArray("558fab4d3e790e2274af0a479c013770a2f889df13fefd825417b794470f2403afe5");
		byte[] result = Viterbi.encode(input, (byte) 0x4f, (byte) 0x6d, true);
		assertArrayEquals(expected, result);
	}

	@Test
	public void testDecode() {
		byte[] input = hexStringToByteArray("558fab4d3e790e2274af0a479c013770a2f889df13fefd825417b794470f2403afe5");
		byte[] expected = hexStringToByteArray("0ec1d49d7082582c93ada7b746ce5a97");
		byte[] result = Viterbi.decode(input, (byte) 0x4f, (byte) 0x6d, true);
		assertArrayEquals(expected, result);
	}

	@Test
	public void testDecodeInputWithError() {
		byte[] input = hexStringToByteArray("558fab4d3e780e2274af0a479c013770a2f889df13fefd825417b794470f2403afe5");
		byte[] expected = hexStringToByteArray("0ec1d49d7082582c93ada7b746ce5a97");
		byte[] result = Viterbi.decode(input, (byte) 0x4f, (byte) 0x6d, true);
		assertArrayEquals(expected, result);
	}

	public static byte[] hexStringToByteArray(String s) {
		int len = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ' ') {
				continue;
			}
			len++;
		}
		byte[] data = new byte[len / 2];
		int index = 0;
		for (int i = 0; i < s.length();) {
			if (s.charAt(i) == ' ') {
				i++;
				continue;
			}
			data[index] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
			i += 2;
			index++;
		}
		return data;
	}

}
