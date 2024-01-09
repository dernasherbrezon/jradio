package ru.r2cloud.jradio.sstk1;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class StratosatTk1PictureDecoderTest {

	@Test
	public void testSuccess() throws Exception {
		List<StratosatTk1Beacon> beacons = new ArrayList<>();
		try (BufferedReader r = new BufferedReader(new InputStreamReader(StratosatTk1PictureDecoderTest.class.getClassLoader().getResourceAsStream("stratosattk1_beacons.txt")))) {
			String curline = null;
			while ((curline = r.readLine()) != null) {
				curline = curline.trim();
				StratosatTk1Beacon cur = new StratosatTk1Beacon();
				cur.readBeacon(ViterbiTest.hexStringToByteArray(curline));
				beacons.add(cur);
			}
		}
		StratosatTk1PictureDecoder decoder = new StratosatTk1PictureDecoder(beacons);
		assertTrue(decoder.hasNext());
		TestUtil.assertImage("expected/stratosattk1/picture.png", decoder.next());
		assertFalse(decoder.hasNext());
	}

}
