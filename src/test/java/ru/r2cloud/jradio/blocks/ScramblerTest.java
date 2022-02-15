package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ru.r2cloud.jradio.ArrayByteInput;

public class ScramblerTest {

	@Test
	public void scrambleDescramble() throws Exception {
		int length = 16;
		int[] data = new int[48];
		for (int i = 0; i < data.length; i++) {
			data[i] = i % 2;
		}
		ArrayByteInput source = new ArrayByteInput(data);
		Scrambler scrambler = new Scrambler(source, 0x21, 0x00, length);
		Descrambler descr = new Descrambler(scrambler, 0x21, 0x00, length);
		assertEquals(length + 1 + data.length, descr.getContext().getTotalSamples().intValue());
		// skip auto sync
		for (int i = 0; i < (length + 1); i++) {
			descr.readByte();
		}
		for (int i = 0; i < data.length; i++) {
			assertEquals("failed at index: " + i, data[i], descr.readByte());
		}
		descr.close();
	}

}
