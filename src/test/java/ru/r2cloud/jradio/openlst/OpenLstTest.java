package ru.r2cloud.jradio.openlst;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class OpenLstTest {

	@Test
	public void testSuccess() throws Exception {
		OpenLst fec = new OpenLst(100);
		byte[] input = new byte[] { 1, 2, 3 };
		byte[] encoded = fec.encode(input);
		assertArrayEquals(new byte[] { (byte) 0x1a, (byte) 0x1d, (byte) 0xd6, (byte) 0x0b, (byte) 0x0b, (byte) 0x08, (byte) 0x94, (byte) 0xa8, (byte) 0xc7, (byte) 0x95, (byte) 0xa1, (byte) 0x2a, (byte) 0x9e, (byte) 0x70, (byte) 0x47, (byte) 0x06 }, encoded);
		byte[] actual = fec.decode(encoded);
		assertArrayEquals(input, actual);
	}

}
