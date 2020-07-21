package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ru.r2cloud.jradio.ArrayByteInput;

public class ChunksToSymbolsTest {

	@Test
	public void testSuccess() throws Exception {
		ArrayByteInput data = new ArrayByteInput(0b0, 0b1);
		@SuppressWarnings("resource")
		ChunksToSymbolsComplex chunksToSymbols = new ChunksToSymbolsComplex(data, new float[] { -1, 0, 1, 0 });
		assertEquals(-1, chunksToSymbols.readFloat(), 0.0f);
		assertEquals(0, chunksToSymbols.readFloat(), 0.0f);
		assertEquals(1, chunksToSymbols.readFloat(), 0.0f);
		assertEquals(0, chunksToSymbols.readFloat(), 0.0f);
	}

}
