package ru.r2cloud.jradio.sputnix;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class SputnixPictureDecoderTest {

	@Test
	public void testSuccess() throws Exception {
		List<SputnixBeacon> beacons = new ArrayList<>();
		try (BufferedReader r = new BufferedReader(new InputStreamReader(SputnixPictureDecoderTest.class.getClassLoader().getResourceAsStream("sputnix_beacons.txt")))) {
			String curline = null;
			while ((curline = r.readLine()) != null) {
				curline = curline.trim();
				SputnixBeacon cur = new SputnixBeacon();
				cur.readBeacon(ViterbiTest.hexStringToByteArray(curline));
				beacons.add(cur);
			}
		}
		SputnixPictureDecoder decoder = new SputnixPictureDecoder(beacons);
		assertTrue(decoder.hasNext());
		TestUtil.assertImage("expected/sputnix/sputnix.png", decoder.next());
		assertFalse(decoder.hasNext());
	}
	
}
