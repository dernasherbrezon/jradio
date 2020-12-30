package ru.r2cloud.jradio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.EOFException;

import org.junit.Test;

import ru.r2cloud.jradio.blocks.UnpackedToPacked;

public class UnpackedToPackedTest {

	@Test
	public void testTotalNumberOfSamples() throws Exception {
		ArrayByteInput data = new ArrayByteInput(1, 0, 0, 0, 1, 1, 0, 1, 1);
		UnpackedToPacked u2p = new UnpackedToPacked(data, 1, Endianness.GR_MSB_FIRST);
		assertEquals((byte) 0b10001101, u2p.readByte());
		assertEquals((byte) 0b10000000, u2p.readByte());
		try {
			u2p.readByte();
			fail("expected eof");
		} catch (EOFException e) {
			// do nothing
		}
		assertEquals(2L, u2p.getContext().getTotalSamples().longValue());
		u2p.close();
	}

}
