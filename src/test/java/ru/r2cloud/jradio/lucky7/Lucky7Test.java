package ru.r2cloud.jradio.lucky7;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class Lucky7Test {

	private Lucky7 input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(Lucky7Test.class.getClassLoader().getResourceAsStream("lucky_7.wav"));
		FskDemodulator demod = new FskDemodulator(source, 4800);
		SoftToHard s2h = new SoftToHard(demod);
		CorrelateSyncword correlate = new CorrelateSyncword(s2h, 3, "0010110111010100", 37 * 8, false);
		input = new Lucky7(correlate);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Lucky7Beacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
