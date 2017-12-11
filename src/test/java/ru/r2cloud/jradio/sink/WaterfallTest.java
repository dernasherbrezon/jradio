package ru.r2cloud.jradio.sink;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import org.junit.Test;

import ru.r2cloud.jradio.WavFileSourceTest;
import ru.r2cloud.jradio.source.WavFileSource;

public class WaterfallTest {

	@Test
	public void testSuccess() throws Exception {
		BufferedImage image = new BufferedImage(1024, 800, BufferedImage.TYPE_INT_RGB);
		Waterfall waterfall = new Waterfall(new WavFileSource(WavFileSourceTest.class.getClassLoader().getResourceAsStream("aausat-4.wav")), 1024, 1, 0.0, 44100, 10);
		waterfall.save(image);
		ImageIO.write(image, "png", new FileOutputStream("test.png"));
		waterfall.close();
	}

}
