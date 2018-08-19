package ru.r2cloud.jradio.sink;

import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

	public static void main(String[] args) throws Exception {
		Spectogram spectogram = new Spectogram(2);
		File[] f = new File("/Users/dernasherbrezon/git/r2cloud/ao73analysis").listFiles();
		for (File cur : f) {
			if (cur.getName().endsWith(".png") || cur.getName().contains("DS_Store")) {
				continue;
			}
			WavFileSource source = new WavFileSource(new BufferedInputStream(new FileInputStream(cur)));
			BufferedImage image = spectogram.process(source);
			source.close();
			ImageIO.write(image, "png", new FileOutputStream(new File(cur.getParentFile(), cur.getName() + ".png")));
		}
	}

}
