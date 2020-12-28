package ru.r2cloud.jradio.florsat;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.blocks.MultiplyConst;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class Floripasat1Test {

	private Floripasat1 input;
	private Floripasat1Downlink downlink;

	@Test
	public void testSuccess() throws Exception {
		WavFileSource source = new WavFileSource(Floripasat1Test.class.getClassLoader().getResourceAsStream("floripasat_1.wav"));
		MultiplyConst mc = new MultiplyConst(source, 10.0f);
		FskDemodulator demod = new FskDemodulator(mc, 1200, 5000.0f, 8, 2000.0f, false);
		// 80 is for test packet only. in prod should be 255
		CorrelateSyncword correlate = new CorrelateSyncword(demod, 5, "01011101111001100010101001111110", (80 + 3) * 8, false);
		input = new Floripasat1(correlate);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Floripasat1Beacon.json", input.next());
	}

	@Test
	public void testDownlink() throws Exception {
		WavFileSource source = new WavFileSource(Floripasat1Test.class.getClassLoader().getResourceAsStream("floripasatDownlink.wav"));
		FskDemodulator demod = new FskDemodulator(source, 2400, 5000.0f, 4, 2000.0f, false);
		CorrelateSyncword correlate = new CorrelateSyncword(demod, 5, "01011101111001100010101001111110", (255 + 3) * 8, false);
		downlink = new Floripasat1Downlink(correlate);
		assertTrue(downlink.hasNext());
		AssertJson.assertObjectsEqual("Floripasat1DownlinkBeacon.json", downlink.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
		if (downlink != null) {
			downlink.close();
		}
	}
}
