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

public class Geoscan2PictureDecoderTest {

	@Test
	public void testSuccess() throws Exception {
		List<Geoscan2Beacon> beacons = new ArrayList<>();
		try (BufferedReader r = new BufferedReader(new InputStreamReader(Geoscan2PictureDecoderTest.class.getClassLoader().getResourceAsStream("geoscan2_beacons.txt")))) {
			String curline = null;
			while ((curline = r.readLine()) != null) {
				curline = curline.trim();
				Geoscan2Beacon cur = new Geoscan2Beacon();
				cur.readBeacon(ViterbiTest.hexStringToByteArray(curline));
				beacons.add(cur);
			}
		}
		Geoscan2PictureDecoder decoder = new Geoscan2PictureDecoder(beacons);
		assertTrue(decoder.hasNext());
		TestUtil.assertImage("expected/geoscan/geoscan2.png", decoder.next());
		assertFalse(decoder.hasNext());
	}

}
