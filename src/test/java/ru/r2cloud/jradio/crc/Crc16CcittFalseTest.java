package ru.r2cloud.jradio.crc;

import static org.junit.Assert.assertEquals;

import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class Crc16CcittFalseTest {

	@Test
	public void testSuccess() {
		byte[] data = "123456789".getBytes(StandardCharsets.ISO_8859_1);
		int result = Crc16CcittFalse.calculate(data, 0, data.length);
		assertEquals(0x29B1, result);
	}
}
