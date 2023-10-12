package ru.r2cloud.jradio.sharjahsat;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class Sharjahsat1PictureDecoderTest {

	@Test
	public void testSuccess() throws Exception {
		List<Sharjahsat1Beacon> beacons = new ArrayList<>();
		try (BufferedReader r = new BufferedReader(new InputStreamReader(Sharjahsat1PictureDecoderTest.class.getClassLoader().getResourceAsStream("sharjahsat1_beacons.txt")))) {
			String curline = null;
			while ((curline = r.readLine()) != null) {
				curline = curline.trim();
				Sharjahsat1Beacon cur = new Sharjahsat1Beacon();
				cur.readBeacon(ViterbiTest.hexStringToByteArray(curline));
				beacons.add(cur);
			}
		}
		Sharjahsat1PictureDecoder decoder = new Sharjahsat1PictureDecoder(beacons);
		assertTrue(decoder.hasNext());
		TestUtil.assertImage("expected/sharjahsat/Sharjahsat1.png", decoder.next());
		assertFalse(decoder.hasNext());
	}

}
