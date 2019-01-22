package ru.r2cloud.jradio.util;

import static org.junit.Assert.assertArrayEquals;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import org.junit.Test;

public class StreamUtilsTest {

	@Test
	public void testByteArray() throws IOException {
		byte[] data = new byte[] {0x01, 0x02};
		LittleEndianDataInputStream dis = new LittleEndianDataInputStream(new DataInputStream(new ByteArrayInputStream(data)));
		byte[] result = StreamUtils.toByteArray(dis);
		assertArrayEquals(data, result);
	}
	
}
