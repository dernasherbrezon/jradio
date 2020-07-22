package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ru.r2cloud.jradio.ArrayByteInput;
import ru.r2cloud.jradio.ByteInput;

public class ChunksToSymbolsTest {

	@Test
	public void test() throws Exception {
		ByteInput input = new ArrayByteInput(new byte[] { 1, 0, 1, 0, 0, 1 });
		ChunksToSymbols block = new ChunksToSymbols(input, new float[] { -1, 1 });
		assertEquals(1.0f, block.readFloat(), 0.0f);
		assertEquals(-1.0f, block.readFloat(), 0.0f);
		assertEquals(1.0f, block.readFloat(), 0.0f);
		assertEquals(-1.0f, block.readFloat(), 0.0f);
		assertEquals(-1.0f, block.readFloat(), 0.0f);
		assertEquals(1.0f, block.readFloat(), 0.0f);
		block.close();
	}

}
