package ru.r2cloud.jradio.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CorrelateAccessCodeTest {

	@Test
	public void testSuccess2Byte() {
		CorrelateAccessCode code = new CorrelateAccessCode(1, "0111111001111110");
		assertEquals(3, code.indexOf(new byte[] { 0x10, 0x7e, 0x50, 0x7e, 0x7e, 0x70 }));
	}
	
	@Test
	public void testSuccess2ByteWithThreshold() {
		CorrelateAccessCode code = new CorrelateAccessCode(2, "0111111001111110");
		assertEquals(3, code.indexOf(new byte[] { 0x10, 0x7e, 0x50, 0x7c, 0x5e, 0x70 }));
	}
	
	@Test
	public void testSuccessLastIndexOf() {
		CorrelateAccessCode code = new CorrelateAccessCode(1, "0111111001111110");
		assertEquals(6, code.lastIndexOf(new byte[] { 0x10, 0x7e, 0x50, 0x7e, 0x7e, 0x70, 0x7e, 0x7e }));
	}
}
