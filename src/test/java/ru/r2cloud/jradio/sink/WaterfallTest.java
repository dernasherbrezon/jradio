package ru.r2cloud.jradio.sink;

import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import ru.r2cloud.jradio.WavFileSourceTest;
import ru.r2cloud.jradio.source.WavFileSource;

public class WaterfallTest {

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	@Test
	public void testSuccess() throws Exception {
		Waterfall waterfall = new Waterfall(100, 1024);
		WavFileSource source = new WavFileSource(WaterfallTest.class.getClassLoader().getResourceAsStream("aausat-4.wav"));
		BufferedImage image = waterfall.process(source);
		source.close();
		try (InputStream is1 = WavFileSourceTest.class.getClassLoader().getResourceAsStream("expectedWaterfall.png")) {
			BufferedImage expected = ImageIO.read(is1);
			for (int i = 0; i < expected.getWidth(); i++) {
				for (int j = 0; j < expected.getHeight(); j++) {
					assertEquals(expected.getRGB(i, j), image.getRGB(i, j));
				}
			}
		}
	}

}
