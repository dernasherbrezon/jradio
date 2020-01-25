package ru.r2cloud.jradio.jy1sat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.imageio.ImageIO;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.BeaconInputStream;
import ru.r2cloud.ssdv.SsdvDecoder;
import ru.r2cloud.ssdv.SsdvImage;

public class Jy1satSsdvPacketSourceTest {

	@Test
	public void testSuccess() throws Exception {
		int total = 0;
		try (BeaconInputStream<Jy1satBeacon> bis = new BeaconInputStream<>(Jy1satSsdvPacketSourceTest.class.getClassLoader().getResourceAsStream("jy1sat.bin"), Jy1satBeacon.class)) {
			SsdvDecoder decoder = new SsdvDecoder(new Jy1satSsdvPacketSource(bis));
			while (decoder.hasNext()) {
				SsdvImage image = decoder.next();
				total++;
				assertSsdvImage("jy1sat", image);
			}
		}
		assertEquals(1, total);
	}

	@Test(expected = NoSuchElementException.class)
	public void testIllegalToCallNextFirst() throws Exception {
		try (BeaconInputStream<Jy1satBeacon> bis = new BeaconInputStream<>(Jy1satSsdvPacketSourceTest.class.getClassLoader().getResourceAsStream("jy1sat.bin"), Jy1satBeacon.class)) {
			new Jy1satSsdvPacketSource(bis).next();
		}
	}

	@Test
	public void testInvalidPayload() throws Exception {
		Jy1satBeacon beacon = new Jy1satBeacon();
		beacon.setPayload(new byte[2]);

		List<Jy1satBeacon> beacons = new ArrayList<>();
		beacons.add(beacon);
		SsdvDecoder decoder = new SsdvDecoder(new Jy1satSsdvPacketSource(beacons.iterator()));
		assertFalse(decoder.hasNext());
	}

	private static void assertSsdvImage(String expectedName, SsdvImage cur) throws IOException {
		try (InputStream is1 = Jy1satSsdvPacketSourceTest.class.getClassLoader().getResourceAsStream("expected/ssdv/" + expectedName + ".png")) {
			BufferedImage expected = ImageIO.read(is1);
			for (int i = 0; i < expected.getWidth(); i++) {
				for (int j = 0; j < expected.getHeight(); j++) {
					assertEquals("failure in image: " + expectedName, expected.getRGB(i, j), cur.getImage().getRGB(i, j));
				}
			}
		}
		AssertJson.assertObjectsEqual("ssdv/" + expectedName + ".json", cur);
	}

}
