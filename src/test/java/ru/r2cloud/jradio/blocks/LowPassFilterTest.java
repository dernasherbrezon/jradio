package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.FileInputStream;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.source.InputStreamSource;
import ru.r2cloud.jradio.source.WavFileSource;

public class LowPassFilterTest {

	private LowPassFilter source;

	@Test
	public void test() throws Exception {
		source = new LowPassFilter(new WavFileSource(new BufferedInputStream(new FileInputStream("/Users/dernasherbrezon/Downloads/meteor.wav"))), 1.0, 222222.0, 60000.0, 100.0, Window.WIN_HAMMING, 6.76);
		try (InputStreamSource is = new InputStreamSource(new FileInputStream("/Users/dernasherbrezon/Downloads/LowPassFilter.bin"))) {
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
