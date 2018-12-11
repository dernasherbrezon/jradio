package ru.r2cloud.jradio.crc;

import static org.junit.Assert.assertEquals;

import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class Crc16ArcTest {

	@Test
	public void testSuccess() {
		byte[] data = "123456789".getBytes(StandardCharsets.ISO_8859_1);
		int result = Crc16Arc.calculate(data);
		assertEquals(0xBB3D, result);
	}

}
