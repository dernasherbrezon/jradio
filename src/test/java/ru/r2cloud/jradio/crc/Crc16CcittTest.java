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
	public void testReverse() {
		byte[] data = "123456789".getBytes(StandardCharsets.ISO_8859_1);
		assertEquals(0x906E, Crc16Ccitt.calculateReverse(data));
	}

}
