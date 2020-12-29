package ru.r2cloud.jradio.technosat;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;
import ru.r2cloud.jradio.tubix20.CMX909bBeacon;

public class TechnosatTest {

	private Technosat input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(TechnosatTest.class.getClassLoader().getResourceAsStream("technosat.wav"));
		FskDemodulator demod = new FskDemodulator(source, 4800, 5000.0f, 2, 2000, false);
		SoftToHard s2h = new SoftToHard(demod);
		CorrelateSyncword correlate = new CorrelateSyncword(s2h, 4, "111011110000111011110000", CMX909bBeacon.MAX_SIZE * 8);
		input = new Technosat(correlate);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("TechnosatBeacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
