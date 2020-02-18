package ru.r2cloud.jradio.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.EOFException;
import java.io.IOException;

import org.junit.Test;

public class GapDataInputStreamTest {

	@Test
	public void testData() throws IOException {
		GapData source = new GapData(1);
		source.write(new byte[] { 0x12, 0x34, 0x12, 0x34, 0x56, (byte) 0x9A, (byte) 0x9A, (byte) 0x9A, 0x12 });
		GapDataInputStream is = new GapDataInputStream(source);
		assertEquals(0x3412, is.readLittleEndianShort().shortValue());
		assertEquals(0x9A563412L, is.readLittleEndianUnsignedInt().longValue());
		assertEquals(0x9A, is.readUnsignedByte().intValue());
		assertEquals(0x9A12, is.readUnsignedShort().intValue());
	}

	@Test
	public void testBoundaries() throws IOException {
		GapData source = new GapData(3);
		source.gap(2);
		source.write(new byte[] { 0x12, 0x34, 0x56, 0x78, (byte) 0x9A });
		source.gap(2);
		GapDataInputStream is = new GapDataInputStream(source);
		assertNull(is.readUnsignedByte());
		// boundary between the gap and the data
		assertNull(is.readUnsignedShort());
		assertEquals(0x3456, is.readUnsignedShort().intValue());
		assertEquals(0x78, is.readUnsignedByte().intValue());
		// boundary between the data and the gap
		assertNull(is.readUnsignedShort());
		assertNull(is.readUnsignedByte());
	}

	@Test(expected = EOFException.class)
	public void testEOF() throws IOException {
		GapData source = new GapData(1);
		source.gap(1);
		GapDataInputStream is = new GapDataInputStream(source);
		is.readUnsignedShort();
	}

	@Test(expected = EOFException.class)
	public void testSkipByteBeyound() throws IOException {
		GapData source = new GapData(1);
		source.gap(1);
		GapDataInputStream is = new GapDataInputStream(source);
		is.skipBytes(2);
	}

	@Test
	public void testSkip2Blocks() throws IOException {
		GapData source = new GapData(4);
		source.write(new byte[] { 0x12, 0x34 });
		source.write(new byte[] { 0x12, 0x34 });
		source.write(new byte[] { 0x12, 0x34 });
		source.write(new byte[] { 0x12, 0x34 });
		GapDataInputStream is = new GapDataInputStream(source);
		assertEquals(0x12, is.readUnsignedByte().intValue());
		is.skipBytes(6);
		assertEquals(0x34, is.readUnsignedByte().intValue());
	}

}
