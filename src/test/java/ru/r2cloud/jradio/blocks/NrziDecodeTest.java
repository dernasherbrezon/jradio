package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.ArrayByteInput;

public class NrziDecodeTest {

	private NrziDecode source;

	@Test
	public void testSuccess() throws Exception {
		// 0b1111_1000
		source = new NrziDecode(new ArrayByteInput(1, 1, 1, 1, 1, 0, 0, 0));
		// 0b01111011
		assertEquals(0, source.readByte());
		assertEquals(1, source.readByte());
		assertEquals(1, source.readByte());
		assertEquals(1, source.readByte());
		assertEquals(1, source.readByte());
		assertEquals(0, source.readByte());
		assertEquals(1, source.readByte());
		assertEquals(1, source.readByte());
	}

	@After
	public void stop() throws Exception {
		if (source != null) {
			source.close();
		}
	}
}
