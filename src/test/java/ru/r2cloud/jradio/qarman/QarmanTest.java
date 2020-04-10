package ru.r2cloud.jradio.qarman;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class QarmanTest {

	private Qarman input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		float gainMu = 0.175f * 3;
		WavFileSource source = new WavFileSource(QarmanTest.class.getClassLoader().getResourceAsStream("qarman.wav"));
		FskDemodulator demod = new FskDemodulator(source, 9600, gainMu);
		SoftToHard s2h = new SoftToHard(demod);
		input = new Qarman(s2h);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("QarmanBeacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
