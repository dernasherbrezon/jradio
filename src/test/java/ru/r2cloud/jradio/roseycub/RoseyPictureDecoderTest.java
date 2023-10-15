package ru.r2cloud.jradio.roseycub;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class RoseyPictureDecoderTest {

	@Test
	public void testSuccess() throws Exception {
		List<RoseyCubesatBeacon> beacons = new ArrayList<>();
		try (BufferedReader r = new BufferedReader(new InputStreamReader(RoseyPictureDecoderTest.class.getClassLoader().getResourceAsStream("rosey_beacons.txt")))) {
			String curline = null;
			while ((curline = r.readLine()) != null) {
				curline = curline.trim();
				RoseyCubesatBeacon cur = new RoseyCubesatBeacon();
				cur.readBeacon(ViterbiTest.hexStringToByteArray(curline));
				beacons.add(cur);
			}
		}
		RoseyPictureDecoder decoder = new RoseyPictureDecoder(beacons);
		assertTrue(decoder.hasNext());
		TestUtil.assertImage("expected/rosey/rosey.png", decoder.next());
		assertFalse(decoder.hasNext());
	}
}
