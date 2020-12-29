package ru.r2cloud.jradio.opssat;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.ArrayByteInput;
import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class OpsSatTest {

	private OpsSat input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(OpsSatTest.class.getClassLoader().getResourceAsStream("ops_sat.wav"));
		FskDemodulator demod = new FskDemodulator(source, 9600);
		SoftToHard s2h = new SoftToHard(demod);
		input = new OpsSat(s2h);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("OpsSatBeacon.json", input.next());
	}

	@Test
	public void testMaxMessageSize() throws Exception {
		input = new OpsSat(new ArrayByteInput(false, false, 0x01));
		assertNull(input.parseBeacon(new byte[] { 0x01, 0x02 }));
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
