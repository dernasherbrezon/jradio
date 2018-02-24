package ru.r2cloud.jradio.ccsds;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import ru.r2cloud.jradio.ViterbiTest;
import ru.r2cloud.jradio.fec.ccsds.ReedSolomon;

public class ReedSolomonTest {

	@Test
	public void encode() {
		byte[] data = ViterbiTest.hexStringToByteArray("eec1d49d7082582c93ada7b746ce5a97");
		byte[] expected = ViterbiTest.hexStringToByteArray("eec1d49d7082582c93ada7b746ce5a97e6de0634fb399bdb61ab215b64672ffc0e68e7ea60cb6ade775cb2507cb7613f");
		byte[] result = ReedSolomon.encode(data);
		assertArrayEquals(expected, result);
	}

	@Test
	public void decode() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("eec1d49d7082582c93ada7b746ce5a97e6de0634fb399bdb61ab215b64672ffc0e68e7ea60cb6ade775cb2507cb7613f");
		byte[] expected = ViterbiTest.hexStringToByteArray("eec1d49d7082582c93ada7b746ce5a97");
		byte[] result = ReedSolomon.decode(data);
		assertArrayEquals(expected, result);
	}

	@Test
	public void testDecodeInputWithError() throws Exception {
		byte[] input = ViterbiTest.hexStringToByteArray("dec1d49d7082582c93ada7b746ce5a97e6de0634fb399bdb61ab215b64672ffc0e68e7ea60cb6ade775cb2507cb7613f");
		byte[] expected = ViterbiTest.hexStringToByteArray("eec1d49d7082582c93ada7b746ce5a97");
		byte[] result = ReedSolomon.decode(input);
		assertArrayEquals(expected, result);
	}
}
