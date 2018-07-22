package ru.r2cloud.jradio.sink;

import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.junit.Test;

import ru.r2cloud.jradio.source.WavFileSource;
import ru.r2cloud.jradio.source.WavFileSourceTest;

public class SpectogramTest {

	@Test
	public void testSuccess() throws Exception {
		Spectogram spectogram = new Spectogram(2);
		WavFileSource source = new WavFileSource(SpectogramTest.class.getClassLoader().getResourceAsStream("aausat-4.wav"));
		BufferedImage image = spectogram.process(source);
		source.close();
		try (InputStream is1 = WavFileSourceTest.class.getClassLoader().getResourceAsStream("expectedSpectogram.png")) {
			BufferedImage expected = ImageIO.read(is1);
			for (int i = 0; i < expected.getWidth(); i++) {
				for (int j = 0; j < expected.getHeight(); j++) {
					assertEquals(expected.getRGB(i, j), image.getRGB(i, j));
				}
			}
		}
	}

}
