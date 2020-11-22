package ru.r2cloud.jradio.delfic3;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.demod.BpskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class DelfiC3Test {

	private DelfiC3 input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(DelfiC3Test.class.getClassLoader().getResourceAsStream("delfic3.wav"));
		BpskDemodulator bpsk = new BpskDemodulator(source, 1200, 1, 1400, true);
		input = new DelfiC3(bpsk);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("DelfiC3PayloadBeacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
