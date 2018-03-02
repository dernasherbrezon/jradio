package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import java.io.EOFException;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.source.InputStreamSource;

public class FloatToCharTest {

	private FloatToChar source;

	@Test
	public void test() throws Exception {
		source = new FloatToChar(new InputStreamSource(FloatToCharTest.class.getClassLoader().getResourceAsStream("rail.bin")), 127.0f);
		try (InputStreamSource is = new InputStreamSource(FloatToCharTest.class.getClassLoader().getResourceAsStream("f2char.bin"))) {
			int index = 0;
			while (true) {
				byte expected = is.readByte();
				byte actual = source.readByte();
				assertEquals("failed at index: " + index, expected, actual);
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
