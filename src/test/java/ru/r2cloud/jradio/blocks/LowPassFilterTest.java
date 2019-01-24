package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import java.io.EOFException;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.source.InputStreamSource;
import ru.r2cloud.jradio.source.WavFileSource;

public class LowPassFilterTest {

	private LowPassFilter source;

	@Test
	public void test() throws Exception {
		source = new LowPassFilter(new WavFileSource(LowPassFilterTest.class.getClassLoader().getResourceAsStream("aausat-4.wav")), 5, 1.0, 1000.0, 600.0, Window.WIN_HAMMING, 6.76);
		try (InputStreamSource is = new InputStreamSource(LowPassFilterTest.class.getClassLoader().getResourceAsStream("low_pass_decim.bin"))) {
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
