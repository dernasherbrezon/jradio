package ru.r2cloud.jradio.lucky7;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.Test;

import ru.r2cloud.jradio.BeaconInputStream;

public class Lucky7PictureDecoderTest {

	@Test
	public void testSuccess() throws Exception {
		List<Lucky7Beacon> beacons = new ArrayList<>();
		try (BeaconInputStream<Lucky7Beacon> bis = new BeaconInputStream<>(new BufferedInputStream(Lucky7PictureDecoderTest.class.getClassLoader().getResourceAsStream("lucky7.bin")), Lucky7Beacon.class)) {
			while (bis.hasNext()) {
				Lucky7Beacon beacon = bis.next();
				beacons.add(beacon);
			}
		}
		Lucky7PictureDecoder decoder = new Lucky7PictureDecoder(beacons);
		assertTrue(decoder.hasNext());
		try (InputStream is1 = Lucky7PictureDecoderTest.class.getClassLoader().getResourceAsStream("expected/lucky7/lucky7.png")) {
			BufferedImage expected = ImageIO.read(is1);
			BufferedImage actual = decoder.next();
			for (int i = 0; i < expected.getWidth(); i++) {
				for (int j = 0; j < expected.getHeight(); j++) {
					assertEquals("failure in image", expected.getRGB(i, j), actual.getRGB(i, j));
				}
			}
		}
	}

}
