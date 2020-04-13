package ru.r2cloud.jradio.util;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;

import org.junit.Test;

public class LsbBitInputStreamTest {

	@Test
	public void testBitsAsString() throws Exception {
		LsbBitInputStream is = new LsbBitInputStream(new ByteArrayInputStream(new byte[] { (byte) 0xca, (byte) 0xfe, 0x02 }));
		assertEquals("02FECA", is.readBitsAsString(24));
	}

}
