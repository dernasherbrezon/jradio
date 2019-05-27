package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ru.r2cloud.jradio.ArrayByteInput;

public class MapBlockTest {

	@Test
	public void testSuccess() throws Exception {
		ArrayByteInput data = new ArrayByteInput(1, 2, 3);
		@SuppressWarnings("resource")
		MapBlock map = new MapBlock(data, new int[] { 0, 3, 2, 1 });
		assertEquals(3, map.readByte());
		assertEquals(2, map.readByte());
		assertEquals(1, map.readByte());
	}

}
