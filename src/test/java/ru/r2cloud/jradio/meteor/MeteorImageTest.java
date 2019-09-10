package ru.r2cloud.jradio.meteor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.blocks.Constellation;
import ru.r2cloud.jradio.demod.QpskDemodulator;
import ru.r2cloud.jradio.lrpt.Vcdu;
import ru.r2cloud.jradio.source.WavFileSource;

public class MeteorImageTest {

	private static final Logger LOG = LoggerFactory.getLogger(MeteorImageTest.class);

	@Test
	public void success() throws Exception {
		byte[] vcduData = toBytes("vcdu.bin");
		Vcdu vcdu = new Vcdu();
		vcdu.readExternal(null, vcduData);
		List<Vcdu> data = new ArrayList<>();
		data.add(vcdu);
		MeteorImage image = new MeteorImage(data.iterator());
		BufferedImage actual = image.toBufferedImage();
		try (InputStream is1 = MeteorImageTest.class.getClassLoader().getResourceAsStream("expected8bitsoft.png")) {
			BufferedImage expected = ImageIO.read(is1);
			for (int i = 0; i < expected.getWidth(); i++) {
				for (int j = 0; j < expected.getHeight(); j++) {
					assertEquals(expected.getRGB(i, j), actual.getRGB(i, j));
				}
			}
		}
	}

	@Test
	public void testNoImage() throws Exception {
		MeteorImage image = new MeteorImage(new ArrayList<Vcdu>().iterator());
		assertNull(image.toBufferedImage());
	}

	public static byte[] toBytes(String source) throws IOException {
		InputStream is = MeteorImageTest.class.getClassLoader().getResourceAsStream(source);
		if (is == null) {
			throw new IllegalArgumentException("cannot find in classpath: " + source);
		}
		byte[] buffer = IOUtils.toByteArray(is);
		is.close();
		return buffer;
	}

	// performance test
	public static void main(String[] args) throws Exception {
		int symbolRate = 72000;
		LOG.info("started");
		String filename = "your file is here";
		WavFileSource source = new WavFileSource(new BufferedInputStream(new FileInputStream(filename)));
		Constellation constel = new Constellation(new float[] { -1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, -1.0f }, new int[] { 0, 1, 3, 2 }, 4, 1);
		QpskDemodulator qpskDemod = new QpskDemodulator(source, symbolRate, constel);
		MeteorM meteor = new MeteorMN2(qpskDemod);
		MeteorImage image = new MeteorImage(meteor);
		LOG.info("decoded");
		BufferedImage actual = image.toBufferedImage();
		if (actual != null) {
			ImageIO.write(actual, "png", new File("./target/output_fixed.png"));
		}
		meteor.close();
		LOG.info("done");
	}

}
