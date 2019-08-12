package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ru.r2cloud.jradio.ArrayByteInput;

public class ScramblerTest {

	@Test
	public void scrambleDescramble() throws Exception {
		int length = 16;
		int polyLength = length + 1;
		int[] data = new int[48];
		int dataLength = data.length - polyLength;
		for (int i = 0; i < dataLength; i++) {
			data[i] = i % 2;
		}
		ArrayByteInput source = new ArrayByteInput(data);
		Scrambler scrambler = new Scrambler(source, 0x21, 0x00, length);
		Descrambler descr = new Descrambler(scrambler, 0x21, 0x00, length);
		// skip auto sync
		for (int i = 0; i < polyLength; i++) {
			descr.readByte();
		}
		for (int i = 0; i < dataLength; i++) {
			assertEquals("failed at index: " + i, data[i], descr.readByte());
		}
		descr.close();
	}

}
