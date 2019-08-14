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

	@Test
	public void testEncodeDecode() throws Exception {
		int[] arr = new int[] { 0, 1, 0, 1, 1, 0 };
		ArrayByteInput data = new ArrayByteInput(arr);
		DifferentialEncoder encoder = new DifferentialEncoder(data, 2);
		DifferentialDecoder decoder = new DifferentialDecoder(encoder, 2);
		for (int i = 0; i < arr.length; i++) {
			assertEquals("failure at index:" + i, arr[i], decoder.readByte());
		}
		decoder.close();
	}

}
