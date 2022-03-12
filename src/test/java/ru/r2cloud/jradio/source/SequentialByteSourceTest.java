package ru.r2cloud.jradio.source;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.ArrayByteInput;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;

public class SequentialByteSourceTest {

	private SequentialByteSource source;

	@Test
	public void success() throws Exception {
		List<ByteInput> data = new ArrayList<>();
		data.add(new ArrayByteInput(new byte[2]));
		data.add(new ArrayByteInput(new byte[3]));
		data.add(new ArrayByteInput(new byte[4]));
		source = new SequentialByteSource(data, new Context());
		long expectedLength = 9;
		assertEquals(expectedLength, source.getContext().getTotalSamples().longValue());
		for (int i = 0; i < expectedLength; i++) {
			assertEquals(0, source.readByte());
		}
		try {
			source.readByte();
			fail("EOF expected");
		} catch (EOFException e) {
		}
	}

	@After
	public void stop() throws Exception {
		if (source != null) {
			source.close();
		}
	}

}
