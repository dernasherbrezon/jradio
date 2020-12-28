package ru.r2cloud.jradio.suomi100;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.gomx1.AX100Decoder;
import ru.r2cloud.jradio.source.WavFileSource;

public class Suomi100Test {

	private Suomi100 input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(Suomi100Test.class.getClassLoader().getResourceAsStream("suomi_100.wav"));
		FskDemodulator demod = new FskDemodulator(source, 9600);
		CorrelateSyncword correlate = new CorrelateSyncword(demod, 4, "10010011000010110101000111011110", 255 * 8, true);
		AX100Decoder ax100 = new AX100Decoder(correlate, false, true, true);
		input = new Suomi100(ax100);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Suomi100Beacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
