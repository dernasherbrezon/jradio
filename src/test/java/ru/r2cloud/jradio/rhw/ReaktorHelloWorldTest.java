package ru.r2cloud.jradio.rhw;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.blocks.MultiplyConst;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.cc11xx.Cc11xxReceiver;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class ReaktorHelloWorldTest {

	private ReaktorHelloWorld input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(ReaktorHelloWorldTest.class.getClassLoader().getResourceAsStream("rhw.wav"));
		MultiplyConst mc = new MultiplyConst(source, 10.0f);
		FskDemodulator demod = new FskDemodulator(mc, 9600, 5000.0f, 1, 2000.0f, false);
		SoftToHard s2h = new SoftToHard(demod);
		CorrelateSyncword correlate = new CorrelateSyncword(s2h, 4, "00110101001011100011010100101110", 120 * 8, false);
		Cc11xxReceiver cc11 = new Cc11xxReceiver(correlate, true, true);
		input = new ReaktorHelloWorld(cc11);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("ReaktorHelloWorldBeacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
