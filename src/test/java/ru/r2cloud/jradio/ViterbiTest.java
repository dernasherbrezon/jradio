package ru.r2cloud.jradio;

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
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

}
