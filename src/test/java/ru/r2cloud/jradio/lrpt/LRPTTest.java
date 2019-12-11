package ru.r2cloud.jradio.lrpt;

import java.io.EOFException;
import java.io.IOException;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.ArrayByteInput;

public class LRPTTest {

	private LRPT lrpt;

	@Test(expected = EOFException.class)
	public void testGracefulShutdown() throws IOException {
		lrpt = new LRPT(new ArrayByteInput(1, 2, 3), false, false);
		Thread.currentThread().interrupt();
		lrpt.readBytes();
	}

	@After
	public void stop() throws IOException {

Thread.interrupted();
		if (lrpt != null) {
			lrpt.close();
		}
	}

}
