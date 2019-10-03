package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ru.r2cloud.jradio.ArrayByteInput;

public class HardToSoftTest {

	@Test
	public void testSuccess() throws Exception {
		ArrayByteInput input = new ArrayByteInput(new int[] { 0b1010_0011, 0b0101_1111 });
		HardToSoft block = new HardToSoft(input);
		assertEquals(Byte.MAX_VALUE, block.readByte());
		assertEquals(Byte.MIN_VALUE, block.readByte());
		assertEquals(Byte.MAX_VALUE, block.readByte());
		assertEquals(Byte.MIN_VALUE, block.readByte());
		assertEquals(Byte.MIN_VALUE, block.readByte());
		assertEquals(Byte.MIN_VALUE, block.readByte());
		assertEquals(Byte.MAX_VALUE, block.readByte());
		assertEquals(Byte.MAX_VALUE, block.readByte());

		assertEquals(Byte.MIN_VALUE, block.readByte());
		assertEquals(Byte.MAX_VALUE, block.readByte());
		assertEquals(Byte.MIN_VALUE, block.readByte());
		assertEquals(Byte.MAX_VALUE, block.readByte());
		assertEquals(Byte.MAX_VALUE, block.readByte());
		assertEquals(Byte.MAX_VALUE, block.readByte());
		assertEquals(Byte.MAX_VALUE, block.readByte());
		assertEquals(Byte.MAX_VALUE, block.readByte());
		block.close();
	}

}
