package ru.r2cloud.jradio.sink;

import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

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
		Waterfall waterfall = new Waterfall(new WavFileSource(WaterfallTest.class.getClassLoader().getResourceAsStream("aausat-4.wav")), 100, 1024);
		BufferedImage image = waterfall.save();
		File actual = new File(tempFolder.getRoot(), UUID.randomUUID().toString());
		ImageIO.write(image, "png", new FileOutputStream(actual));
		waterfall.close();
		try (InputStream is1 = WavFileSourceTest.class.getClassLoader().getResourceAsStream("expectedWaterfall.png"); InputStream is2 = new FileInputStream(actual)) {
			while (true) {
				try {
					int expected = is1.read();
					if (expected == -1) {
						break;
					}
					assertEquals(expected, is2.read());
				} catch (EOFException e) {
					break;
				}
			}
		}
	}

}
