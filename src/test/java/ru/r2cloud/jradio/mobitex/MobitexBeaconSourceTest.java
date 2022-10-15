package ru.r2cloud.jradio.mobitex;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.dstar1.Dstar1Beacon;
import ru.r2cloud.jradio.source.WavFileSource;

public class MobitexBeaconSourceTest {

	private MobitexBeaconSource<?> input;

	@Test
	public void testSucess() throws Exception {
		WavFileSource source = new WavFileSource(MobitexBeaconSourceTest.class.getClassLoader().getResourceAsStream("dstar_one.wav"));
		FskDemodulator demod = new FskDemodulator(source, 4800);
		input = new MobitexBeaconSource<>(demod, Dstar1Beacon.class);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Dstar1Beacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
