package ru.r2cloud.jradio.eseo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class EseoTest {

	private Eseo input;

	@Test
	public void testSuccess() throws Exception {
		WavFileSource source = new WavFileSource(EseoTest.class.getClassLoader().getResourceAsStream("eseo.wav"));
		FskDemodulator demod = new FskDemodulator(source, 9600);
		SoftToHard s2h = new SoftToHard(demod);
		CorrelateSyncword correlate = new CorrelateSyncword(s2h, 1, EseoBeacon.FLAG, 257 * 8);
		input = new Eseo(correlate);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Eseo.json", input.next());
	}

}
