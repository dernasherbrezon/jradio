package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.source.WavFileSource;
import ru.r2cloud.jradio.source.WavFileSourceTest;

public class LowPassFilterTest {

	private LowPassFilter source;
	
	@Test
	public void test() throws Exception {
		source = new LowPassFilter(new WavFileSource(new BufferedInputStream(new FileInputStream("/Users/dernasherbrezon/Downloads/meteor.wav"))), 1.0, 222222.0, 60000.0, 100.0, Window.WIN_HAMMING, 6.76);
		try (InputStream is = new FileInputStream("/Users/dernasherbrezon/Downloads/LowPassFilter.bin")) {
			Float expected = null;
			while ((expected = WavFileSourceTest.readFloat(is)) != null) {
				float actual = source.readFloat();
				assertEquals(expected, actual, 0.0001f);
			}
		}
	}
	
	@After
	public void stop() throws Exception {
		if (source != null) {
			source.close();
		}
	}
	
}
