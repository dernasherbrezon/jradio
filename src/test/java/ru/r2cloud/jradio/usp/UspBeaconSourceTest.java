package ru.r2cloud.jradio.usp;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class UspBeaconSourceTest {

	private UspBeaconSource<UspBeacon> input;

	@Test
	public void testSuccess() throws Exception {
		WavFileSource source = new WavFileSource(UspBeaconSourceTest.class.getClassLoader().getResourceAsStream("orbicraft-z.wav"));
		FskDemodulator demod = new FskDemodulator(source, 9600);
		input = new UspBeaconSource<>(demod, UspBeacon.class);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("OrbicraftZ-longframe.json", input.next());
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("OrbicraftZ-shortframe.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
