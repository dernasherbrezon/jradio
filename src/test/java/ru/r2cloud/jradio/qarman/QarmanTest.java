package ru.r2cloud.jradio.qarman;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Collections;

import javax.sound.sampled.UnsupportedAudioFileException;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.Ax25G3ruhBeaconSource;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.sink.SnrCalculator;
import ru.r2cloud.jradio.source.WavFileSource;

public class QarmanTest {

	private Ax25G3ruhBeaconSource<QarmanBeacon> input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		input = new Ax25G3ruhBeaconSource<>(new FskDemodulator(createSource(), 9600), QarmanBeacon.class);
		assertTrue(input.hasNext());
		QarmanBeacon next = input.next();
		SnrCalculator.enrichSnr(createSource(), Collections.singletonList(next), 10000, 1);
		AssertJson.assertObjectsEqual("QarmanBeacon.json", next);
	}

	private static WavFileSource createSource() throws UnsupportedAudioFileException, IOException {
		return new WavFileSource(QarmanTest.class.getClassLoader().getResourceAsStream("qarman.wav"));
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
