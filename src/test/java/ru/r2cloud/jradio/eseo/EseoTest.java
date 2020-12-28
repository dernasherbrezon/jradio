package ru.r2cloud.jradio.eseo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class EseoTest {

	private Eseo input;

	@Test
	public void testSuccess() throws Exception {
		WavFileSource source = new WavFileSource(EseoTest.class.getClassLoader().getResourceAsStream("eseo.wav"));
		FskDemodulator demod = new FskDemodulator(source, 9600);
		CorrelateSyncword correlate = new CorrelateSyncword(demod, 1, EseoBeacon.FLAG, 257 * 8, false);
		input = new Eseo(correlate);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Eseo.json", input.next());
	}

}
