package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import java.io.EOFException;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.source.InputStreamSource;
import ru.r2cloud.jradio.source.WavFileSource;

public class RmsTest {

	private Rms source;

	@Test
	public void test() throws Exception {
		WavFileSource stereo = new WavFileSource(RmsTest.class.getClassLoader().getResourceAsStream("stereo.wav"));
		source = new Rms(stereo, 1e-2f);
		try (InputStreamSource is = new InputStreamSource(RmsTest.class.getClassLoader().getResourceAsStream("rms.bin"))) {
			while (true) {
				float expected = is.readFloat();
				float actual = source.readFloat();
				assertEquals(expected, actual, 0.0001f);
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
