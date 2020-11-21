package ru.r2cloud.jradio.aistechsat3;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.Ax100BeaconSource;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.entrysat.EntrysatTest;
import ru.r2cloud.jradio.source.WavFileSource;

public class Aistechsat3Test {

	private Ax100BeaconSource<?> input;

	@Test
	public void testSuccess() throws Exception {
		WavFileSource source = new WavFileSource(EntrysatTest.class.getClassLoader().getResourceAsStream("aistechsat3.wav"));
		FskDemodulator demod = new FskDemodulator(source, 9600);
		input = new Ax100BeaconSource<>(demod, 255, Aistechsat3Beacon.class);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Aistechsat3Beacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
