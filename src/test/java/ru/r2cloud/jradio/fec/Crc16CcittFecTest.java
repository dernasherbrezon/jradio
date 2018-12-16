package ru.r2cloud.jradio.fec;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.nio.charset.StandardCharsets;

import org.junit.Test;

import ru.r2cloud.jradio.crc.Crc16Ccitt;

public class Crc16CcittFecTest {

	@Test
	public void test1BitErrorCorrection() {
		byte[] data = "123456789".getBytes(StandardCharsets.ISO_8859_1);
		int expectedCrc = Crc16Ccitt.calculate(data);
		byte[] corruptedData = new byte[data.length];
		System.arraycopy(data, 0, corruptedData, 0, data.length);
		// flip last bit
		corruptedData[corruptedData.length - 1] ^= 0x1;
		boolean result = Crc16CcittFec.fix1bitUsingCrc(corruptedData, expectedCrc);
		assertTrue(result);
		assertArrayEquals(data, corruptedData);
	}

}
