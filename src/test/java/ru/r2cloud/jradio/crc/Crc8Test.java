package ru.r2cloud.jradio.crc;

import static org.junit.Assert.assertEquals;

import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class Crc8Test {

	@Test
	public void testSuccess() {
		byte[] data = "123456789".getBytes(StandardCharsets.ISO_8859_1);
		assertEquals(0xF4, Crc8.calculate(data, 0, data.length));
	}
}
