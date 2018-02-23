package ru.r2cloud.jradio.meteor;

import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import ru.r2cloud.jradio.lrpt.VCDU;

public class MeteorImageTest {

	@Test
	public void success() throws Exception {
		byte[] vcduData = toBytes("vcdu.bin");
		VCDU vcdu = new VCDU();
		vcdu.readExternal(null, vcduData);
		List<VCDU> data = new ArrayList<>();
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

	private static byte[] toBytes(String source) throws IOException {
		InputStream is = MeteorImageTest.class.getClassLoader().getResourceAsStream(source);
		if (is == null) {
			throw new IllegalArgumentException("cannot find in classpath: " + source);
		}
		byte[] buffer = IOUtils.toByteArray(is);
		is.close();
		return buffer;
	}

}
