package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import java.io.EOFException;

import org.junit.Test;

import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.source.InputStreamSource;
import ru.r2cloud.jradio.source.WavFileSource;

public class FractionalResamplerTest {

	@Test
	public void test() throws Exception {
		try (FloatInput source = new FractionalResampler(new InputStreamSource(FractionalResamplerTest.class.getClassLoader().getResourceAsStream("constellation_modulator_diff.bin")), 1.0f, 1.1f)) {
			TestUtil.assertFloatInput("fractional_resampler.bin", source);
		}
	}

	@Test
	public void testTotalSamples() throws Exception {
		try (FloatInput source = new FractionalResampler(new WavFileSource(FractionalResamplerTest.class.getClassLoader().getResourceAsStream("stereo.wav")), 1.0f, 1.1f)) {
			assertEquals(90909.086f, source.getContext().getSampleRate(), 0.0f);
			float expectedTotalSamples = 11695.0f;
			assertEquals(expectedTotalSamples, source.getContext().getTotalSamples(), 0.0f);
			long actualSamples = 0;
			while (!Thread.currentThread().isInterrupted()) {
				try {
					source.readFloat();
					source.readFloat();
					actualSamples++;
				} catch (EOFException e) {
					break;
				}
			}
			// 8 is the tap of interpolation filter
			assertEquals(expectedTotalSamples, actualSamples, 8.0f);
		}
	}

}
