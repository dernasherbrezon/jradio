package ru.r2cloud.jradio.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.EOFException;

import org.junit.Test;

import ru.r2cloud.jradio.ArrayFloatInput;

public class BufferedFloatInputTest {

	@Test
	public void testSuccess() throws Exception {
		BufferedFloatInput buf = new BufferedFloatInput(new ArrayFloatInput(1.0f, 2.0f, 3.0f, 4.0f, 5.0f), 6);
		assertEquals(1.0f, buf.readFloat(), 0.0f);
		assertEquals(2.0f, buf.readFloat(), 0.0f);
		assertEquals(3.0f, buf.readFloat(), 0.0f);
		assertEquals(4.0f, buf.readFloat(), 0.0f);
		buf.resetBack(2);
		assertEquals(3.0f, buf.readFloat(), 0.0f);
		assertEquals(4.0f, buf.readFloat(), 0.0f);
		assertEquals(5.0f, buf.readFloat(), 0.0f);
		try {
			buf.readFloat();
			fail("eof expected");
		} catch (EOFException e) {
			// do nothing
		}
		buf.close();
	}

}
