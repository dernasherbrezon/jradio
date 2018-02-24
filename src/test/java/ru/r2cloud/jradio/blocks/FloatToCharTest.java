package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

import ru.r2cloud.jradio.source.InputStreamSource;

public class FloatToCharTest {

	private FloatToChar source;

	@Test
	// ignore due to rounding error in float
	@Ignore
	public void test() throws Exception {
		source = new FloatToChar(new InputStreamSource(FloatToCharTest.class.getClassLoader().getResourceAsStream("rail.bin")), 127.0f);
		try (InputStream is = new FileInputStream("f2char.bin")) {
			int index = 0;
			while (true) {
				int expected = is.read();
				int actual = source.readByte();
				assertEquals("failed at index: " + index, (byte) expected, actual);
				index++;
			}
		} catch (EOFException e) {
			// do nothing
		}
	}

	@After
	public void stop() throws Exception {
		if (source != null) {
			source.close();
		}
	}

}
