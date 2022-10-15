package ru.r2cloud.jradio.tubix20;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.blocks.MultiplyConst;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;
import ru.r2cloud.jradio.technosat.TechnosatBeacon;

public class TUBiX20BeaconSourceTest {

	private TUBiX20BeaconSource<?> input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(TUBiX20BeaconSourceTest.class.getClassLoader().getResourceAsStream("technosat.wav"));
		// test data is inverted for some reason
		MultiplyConst mc = new MultiplyConst(source, -1.0f);
		FskDemodulator demod = new FskDemodulator(mc, 4800, 5000.0f, 2, 2000, false);
		input = new TUBiX20BeaconSource<>(demod, TechnosatBeacon.class);
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
