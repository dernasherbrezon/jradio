package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.EOFException;

import org.junit.Test;

import ru.r2cloud.jradio.ArrayByteInput;
import ru.r2cloud.jradio.PhaseAmbiguityResolver;

public class CorrelateSynchronizationMarkerTest {

	@Test
	public void testSuccess() throws Exception {
		PhaseAmbiguityResolver markers = new PhaseAmbiguityResolver(0b111);
		int[] data = new int[] { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, -1, -2, -3, 1, 1, 1, -4, -5, -6, -7, 1, 0, 1, -8, -9, -10, -11, 1, 1, 1, -12 };
		CorrelateSynchronizationMarker block = new CorrelateSynchronizationMarker(new ArrayByteInput(data), 3, 4, 2, 1, 0, markers.getSynchronizationMarkers());
		for (int i = 0; i < 4; i++) {
			assertEquals("fail at index: " + i, 0, block.readByte());
		}
		for (int i = 0; i < 13; i++) {
			assertEquals("fail at index: " + i, -i, block.readByte());
		}
		try {
			block.readByte();
			fail("EOF expected");
		} catch (EOFException e) {
			// do nothing
		}
		block.close();
	}
}
