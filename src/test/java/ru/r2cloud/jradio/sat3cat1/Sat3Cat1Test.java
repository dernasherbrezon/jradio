package ru.r2cloud.jradio.sat3cat1;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class Sat3Cat1Test {

	private Sat3Cat1 input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(Sat3Cat1Test.class.getClassLoader().getResourceAsStream("sat_3cat_1.wav"));
		FskDemodulator demod = new FskDemodulator(source, 9600);
		SoftToHard s2h = new SoftToHard(demod);
		CorrelateSyncword correlate = new CorrelateSyncword(s2h, 4, "11010011100100011101001110010001", 255 * 8);
		input = new Sat3Cat1(correlate);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Sat3Cat1Beacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
