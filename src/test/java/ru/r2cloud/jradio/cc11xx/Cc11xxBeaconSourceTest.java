package ru.r2cloud.jradio.cc11xx;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.blocks.MultiplyConst;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.rhw.ReaktorHelloWorldBeacon;
import ru.r2cloud.jradio.source.WavFileSource;

public class Cc11xxBeaconSourceTest {

	private Cc11xxBeaconSource<?> input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(Cc11xxBeaconSourceTest.class.getClassLoader().getResourceAsStream("rhw.wav"));
		MultiplyConst mc = new MultiplyConst(source, 10.0f);
		FskDemodulator demod = new FskDemodulator(mc, 9600, 5000.0f, 1, 2000.0f, false);
		input = new Cc11xxBeaconSource<>(demod, ReaktorHelloWorldBeacon.class);
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
