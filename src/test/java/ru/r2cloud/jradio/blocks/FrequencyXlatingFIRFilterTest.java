package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import java.io.EOFException;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.source.InputStreamSource;
import ru.r2cloud.jradio.source.WavFileSource;
import ru.r2cloud.jradio.source.WavFileSourceTest;

public class FrequencyXlatingFIRFilterTest {

	private FrequencyXlatingFIRFilter source;

	@Test
	public void test() throws Exception {
		float[] taps = Firdes.lowPass(1.0, 222222.0f, 222222.0f / 2, 1000, Window.WIN_HAMMING, 6.76);
		source = new FrequencyXlatingFIRFilter(new WavFileSource(WavFileSourceTest.class.getClassLoader().getResourceAsStream("meteor_small.wav")), taps, 10, -10000);
		try (InputStreamSource is = new InputStreamSource(LowPassFilterTest.class.getClassLoader().getResourceAsStream("xlating.bin"))) {
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
