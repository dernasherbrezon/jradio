package ru.r2cloud.jradio.crc;

import static org.junit.Assert.assertEquals;

import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class Crc16CcittTest {

	@Test
	public void success() {
		byte[] data = "123456789".getBytes(StandardCharsets.ISO_8859_1);
		assertEquals(0x31C3, Crc16Ccitt.calculate(data));
	}

	@Test
	public void testAppendedCrcIsZero() {
		byte[] content = "123456789".getBytes(StandardCharsets.ISO_8859_1);
		byte[] data = new byte[content.length + 2];
		System.arraycopy(content, 0, data, 0, content.length);
		data[data.length - 2] = 0x31;
		data[data.length - 1] = (byte) 0xC3;
		assertEquals(0, Crc16Ccitt.calculate(data));
	}

	@Test
	public void testReverse() {
		byte[] data = "123456789".getBytes(StandardCharsets.ISO_8859_1);
		assertEquals(0x906E, Crc16Ccitt.calculateReverse(data));
	}

	@Test
	public void testReverseBits() {
		byte[] data = "123456789".getBytes(StandardCharsets.ISO_8859_1);
		byte[] unpacked = unpackLittleEndian(data);
		assertEquals(0x906E, Crc16Ccitt.calculateReverseLsbBits(unpacked, 0, unpacked.length));
	}

	private static byte[] unpackLittleEndian(byte[] data) {
		byte[] result = new byte[data.length * 8];
		for (int i = 0; i < data.length; i++) {
			int cur = data[i] & 0xFF;
			for (int j = 0; j < 8; j++) {
				result[i * 8 + j] = (byte) ((cur >> j) & 0x1);
			}
		}
		return result;
	}

}
