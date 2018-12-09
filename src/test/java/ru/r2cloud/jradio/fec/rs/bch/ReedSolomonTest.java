package ru.r2cloud.jradio.fec.rs.bch;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import ru.r2cloud.jradio.fec.ViterbiTest;

public class ReedSolomonTest {

	@Test
	public void testEncodeDecode() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("4E6572766F75736C792049206C6F6164656420746865207477696E206475636B732061626F61726420746865207265766F6C76696E6720706C6174666F726D2E00");
		ReedSolomon rs = new ReedSolomon(32);
		byte[] result = rs.encode(data);
		byte[] expected = ViterbiTest.hexStringToByteArray("4E6572766F75736C792049206C6F6164656420746865207477696E206475636B732061626F61726420746865207265766F6C76696E6720706C6174666F726D2E0092C4AADC10EA5C0B970C3537D0C0390C5BC173DDCC1527C940C401DC79E2F095");
		assertArrayEquals(expected, result);
		//some errors
		result[10] = (byte) (result[10] - 1);
		result[16] = (byte) (result[10] + 1);
		byte[] decoded = rs.decode(result);
		assertArrayEquals(data, decoded);
	}

}
