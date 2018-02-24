package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import java.io.EOFException;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.source.InputStreamSource;

public class RailTest {

	private Rail source;

	@Test
	public void test() throws Exception {
		source = new Rail(new InputStreamSource(RailTest.class.getClassLoader().getResourceAsStream("constdec.bin")), -1.0f, 1.0f);
		try (InputStreamSource is = new InputStreamSource(RailTest.class.getClassLoader().getResourceAsStream("rail.bin"))) {
			while (true) {
				float expected = is.readFloat();
				float actual = source.readFloat();
				assertEquals(expected, actual, 0.00001f);
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
