package ru.r2cloud.jradio.alsat1n;

import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class Alsat1nTest {

	private Alsat1n input;

	@Test
	public void testSuccess() throws Exception {
		WavFileSource source = new WavFileSource(new BufferedInputStream(Alsat1nTest.class.getClassLoader().getResourceAsStream("alsat1n.wav")));
		FskDemodulator demod = new FskDemodulator(source, 9600);
		input = new Alsat1n(demod);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Alsat1nBeacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
