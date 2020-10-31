package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.EOFException;

import org.junit.Test;

import ru.r2cloud.jradio.ArrayFloatInput;

public class MemoryBufferTest {

	@Test
	public void testBuffer() throws Exception {
		float[] data = new float[] { 0.1f, 0.2f, 0.3f };
		ArrayFloatInput source = new ArrayFloatInput(data);
		MemoryBuffer buffer = new MemoryBuffer(source);
		for (int i = 0; i < data.length; i++) {
			assertEquals(data[i], buffer.readFloat(), 0.0f);
		}
		try {
			buffer.readFloat();
			fail("eof expected");
		} catch (EOFException e) {
			// do nothing
		}
		buffer.close();
	}

}
