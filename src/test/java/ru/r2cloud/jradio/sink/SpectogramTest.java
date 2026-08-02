package ru.r2cloud.jradio.sink;

import java.awt.image.BufferedImage;

import org.junit.Test;

import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.source.WavFileSource;

public class SpectogramTest {

	@Test
	public void testSuccess() throws Exception {
		Spectogram spectogram = new Spectogram(100, 10);
		WavFileSource source = new WavFileSource(SpectogramTest.class.getClassLoader().getResourceAsStream("aausat-4.wav"));
		BufferedImage gray = spectogram.process(source);
		source.close();
		TestUtil.assertImage("expectedSpectogramGray.png", gray);
		BufferedImage rgb = spectogram.convertToRgb(gray);
		TestUtil.assertImage("expectedSpectogramRgb.png", rgb);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEmptyFile() throws Exception {
		Spectogram spectogram = new Spectogram(200);
		WavFileSource source = new WavFileSource(SpectogramTest.class.getClassLoader().getResourceAsStream("empty.wav"));
		spectogram.process(source);
		source.close();
	}

}
