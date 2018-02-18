package ru.r2cloud.jradio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.source.InputStreamSource;

public class BufferedByteInputTest {

	private BufferedByteInput input;

	@Test
	public void testSuccess() throws Exception {
		input = new BufferedByteInput(createArray(7), 4, 2);
		assertEquals((byte) 0, input.readByte());
		assertEquals((byte) 1, input.readByte());
		assertEquals((byte) 2, input.readByte());
		assertEquals((byte) 3, input.readByte());
		assertEquals((byte) 4, input.readByte());
		input.reset(2);
		assertEquals((byte) 3, input.readByte());
		assertEquals((byte) 4, input.readByte());

		assertEquals((byte) 5, input.readByte());
		assertEquals((byte) 6, input.readByte());
		assertEndOfStream();
	}

	@Test
	public void testDataIsLessThanMaxResetSize() throws Exception {
		input = new BufferedByteInput(createArray(2), 4, 4);
		assertEquals((byte) 0, input.readByte());
		input.reset(3);
		assertEquals((byte) 0, input.readByte());
		assertEquals((byte) 1, input.readByte());
		assertEndOfStream();
	}

	@Test
	public void testGrowBufferTwice() throws Exception {
		input = new BufferedByteInput(createArray(18), 4, 4);
		for (int i = 0; i < 8; i++) {
			assertEquals((byte) i, input.readByte());
		}
		input.reset(2);
		for (int i = 6; i < 18; i++) {
			assertEquals((byte) i, input.readByte());
		}
		assertEndOfStream();
	}

	@After
	public void stop() throws IOException {
		if (input != null) {
			input.close();
		}
	}

	private void assertEndOfStream() throws IOException {
		try {
			input.readByte();
			fail("expected eof");
		} catch (EOFException e) {
			// do nothing
		}
	}

	private static ByteInput createArray(int max) {
		byte[] buf = new byte[max];
		for (int i = 0; i < buf.length; i++) {
			buf[i] = (byte) i;
		}
		return new InputStreamSource(new ByteArrayInputStream(buf));
	}

}
