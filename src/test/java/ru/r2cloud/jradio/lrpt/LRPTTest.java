package ru.r2cloud.jradio.lrpt;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.EOFException;
import java.io.IOException;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.ArrayByteInput;

public class LRPTTest {

	private LRPT lrpt;

	@Test
	public void testGracefulShutdown() throws IOException {
		lrpt = new LRPT(new ArrayByteInput(1, 2, 3), false, false);
		Thread.currentThread().interrupt();
		try {
			lrpt.readBytes();
			fail("EOF expected");
		} catch (EOFException e) {
			assertTrue(Thread.interrupted());
		}
	}

	@After
	public void stop() throws IOException {
		if (lrpt != null) {
			lrpt.close();
		}
	}

}
