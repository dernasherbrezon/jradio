package ru.r2cloud.jradio.crc;

import static org.junit.Assert.assertEquals;

import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class Crc16Ibm3740Test {

	@Test
	public void success() {
		byte[] data = "123456789".getBytes(StandardCharsets.ISO_8859_1);
		assertEquals(0x29b1, Crc16Ibm3740.calculate(data, 0, data.length));
	}

}
