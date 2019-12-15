package ru.r2cloud.jradio.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

import org.junit.Test;

public class StreamUtilsTest {

	@Test
	public void testByteArray() throws IOException {
		byte[] data = new byte[] { 0x01, 0x02 };
		LittleEndianDataInputStream dis = new LittleEndianDataInputStream(new DataInputStream(new ByteArrayInputStream(data)));
		byte[] result = StreamUtils.toByteArray(dis);
		assertArrayEquals(data, result);
	}

	@Test
	public void testUnsignedInt() throws IOException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(new byte[] { (byte) 0xFF, 0x00, 0x00, 0x00 }));
		assertEquals(0xFF000000L, StreamUtils.readUnsignedInt(dis));
	}

	@Test(expected = EOFException.class)
	public void testEOFException() throws IOException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(new byte[] { (byte) 0xFF, 0x00, 0x00 }));
		StreamUtils.readUnsignedInt(dis);
	}

}
