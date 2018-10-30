package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import java.io.EOFException;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.source.InputStreamSource;
import ru.r2cloud.jradio.source.WavFileSource;
import ru.r2cloud.jradio.source.WavFileSourceTest;

public class LowPassFilterComplexTest {

	private LowPassFilterComplex source;

	@Test
	public void test() throws Exception {
		source = new LowPassFilterComplex(new WavFileSource(WavFileSourceTest.class.getClassLoader().getResourceAsStream("meteor_small.wav")), 1.0, 60000.0, 100.0, Window.WIN_HAMMING, 6.76);
		try (InputStreamSource is = new InputStreamSource(LowPassFilterComplexTest.class.getClassLoader().getResourceAsStream("LowPassFilter.bin"))) {
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
