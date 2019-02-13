package ru.r2cloud.jradio.crc;

import static org.junit.Assert.assertEquals;

import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class Crc32cTest {
	
	@Test
	public void testSuccess() {
		byte[] data = "123456789".getBytes(StandardCharsets.ISO_8859_1);
		long result = Crc32c.calculate(data);
		assertEquals(0xE3069283L, result);
	}

}
