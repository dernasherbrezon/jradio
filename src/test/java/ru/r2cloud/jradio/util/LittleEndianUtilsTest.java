package ru.r2cloud.jradio.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LittleEndianUtilsTest {

	@Test
	public void testConvertUnsignedInt() {
		long unsignedInt = 0b1010_0101_1111_0000_1000_0001_0001_1010L;
		long expected 	 = 0b0001_1010_1000_0001_1111_0000_1010_0101L;
		assertEquals(expected, LittleEndianUtils.convertUnsignedInt(unsignedInt));
	}
}
