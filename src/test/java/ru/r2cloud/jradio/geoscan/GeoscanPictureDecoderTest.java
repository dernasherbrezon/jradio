package ru.r2cloud.jradio.geoscan;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class GeoscanPictureDecoderTest {

	@Test
	public void testSuccess() throws Exception {
		List<GeoscanBeacon> beacons = new ArrayList<>();
		try (BufferedReader r = new BufferedReader(new InputStreamReader(GeoscanPictureDecoderTest.class.getClassLoader().getResourceAsStream("geoscan_beacons.txt")))) {
			String curline = null;
			while ((curline = r.readLine()) != null) {
				curline = curline.trim();
				GeoscanBeacon cur = new GeoscanBeacon();
				cur.readBeacon(ViterbiTest.hexStringToByteArray(curline));
				beacons.add(cur);
			}
		}
		GeoscanPictureDecoder decoder = new GeoscanPictureDecoder(beacons);
		assertTrue(decoder.hasNext());
		TestUtil.assertImage("expected/geoscan/geoscan.png", decoder.next());
		assertFalse(decoder.hasNext());
	}

}
