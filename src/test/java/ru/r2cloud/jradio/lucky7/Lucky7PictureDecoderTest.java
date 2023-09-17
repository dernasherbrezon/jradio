package ru.r2cloud.jradio.lucky7;

import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ru.r2cloud.jradio.BeaconInputStream;
import ru.r2cloud.jradio.TestUtil;

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
		TestUtil.assertImage("expected/lucky7/lucky7.png", decoder.next());
	}

}
