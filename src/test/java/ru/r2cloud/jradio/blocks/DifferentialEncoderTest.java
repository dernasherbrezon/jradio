package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ru.r2cloud.jradio.ArrayByteInput;

public class DifferentialEncoderTest {

	@Test
	public void testSuccess() throws Exception {
		ArrayByteInput data = new ArrayByteInput(0, 1, 0, 1, 1, 0);
		@SuppressWarnings("resource")
		DifferentialEncoder encoder = new DifferentialEncoder(data, 2);
		assertEquals(0, encoder.readByte());
		assertEquals(1, encoder.readByte());
		assertEquals(1, encoder.readByte());
		assertEquals(0, encoder.readByte());
		assertEquals(1, encoder.readByte());
		assertEquals(1, encoder.readByte());
	}

}
