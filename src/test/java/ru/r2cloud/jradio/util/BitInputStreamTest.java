package ru.r2cloud.jradio.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

public class BitInputStreamTest {
	
	@Test
	public void testSuccess() throws IOException {
		byte[] data = new byte[] {
				(byte)0b1101_0101,
				0b0010_1010,
				0b0011_0001,
				0b0100_1000
		};
		BitInputStream bis = new BitInputStream(data);
		assertEquals(0b1101_0101_00, bis.readUnsignedInt(10));
		assertTrue(bis.readBoolean());
		assertEquals(0b0101, bis.readUnsignedInt(4));
		assertEquals(0b0_0011_0001_0, bis.readUnsignedInt(10));
	}

	@Test
	public void testUnsignedLong() throws IOException {
		byte[] data = new byte[] {
				(byte)0b1101_0101,
				0b0010_1010,
				0b0011_0001,
				0b0100_1000
		};
		BitInputStream bis = new BitInputStream(data);
		assertEquals(0b1101_0101_0010_1010_0011_0001_0100_1000L, bis.readUnsignedLong(32));
	}
}
