package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ru.r2cloud.jradio.ArrayByteInput;

public class InvertBitsTest {

	@Test
	public void testSuccess() throws Exception {
		InvertBits input = new InvertBits(new ArrayByteInput(0, 1, 0));
		assertEquals(1, input.readByte());
		assertEquals(0, input.readByte());
		assertEquals(1, input.readByte());
		input.close();
	}

}
