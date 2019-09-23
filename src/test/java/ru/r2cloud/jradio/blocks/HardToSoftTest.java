package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ru.r2cloud.jradio.ArrayByteInput;

public class HardToSoftTest {

	@Test
	public void testSuccess() throws Exception {
		ArrayByteInput input = new ArrayByteInput(new int[] { 0b1010_0011, 0b0101_1111 });
		HardToSoft block = new HardToSoft(input);
		assertEquals(126, block.readByte());
		assertEquals(-127, block.readByte());
		assertEquals(126, block.readByte());
		assertEquals(-127, block.readByte());
		assertEquals(-127, block.readByte());
		assertEquals(-127, block.readByte());
		assertEquals(126, block.readByte());
		assertEquals(126, block.readByte());

		assertEquals(-127, block.readByte());
		assertEquals(126, block.readByte());
		assertEquals(-127, block.readByte());
		assertEquals(126, block.readByte());
		assertEquals(126, block.readByte());
		assertEquals(126, block.readByte());
		assertEquals(126, block.readByte());
		assertEquals(126, block.readByte());
		block.close();
	}

}
