package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ru.r2cloud.jradio.ArrayByteInput;

public class InvertBitsTest {

	@Test
	public void testSuccess() throws Exception {
		InvertBits input = new InvertBits(new ArrayByteInput(-128, 127, -128));
		assertEquals(127, input.readByte());
		assertEquals(-128, input.readByte());
		assertEquals(127, input.readByte());
		input.close();
	}

}
