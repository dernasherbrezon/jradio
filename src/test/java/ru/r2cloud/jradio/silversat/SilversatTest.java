package ru.r2cloud.jradio.silversat;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.Il2pBeaconSource;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.il2p.Il2pBeacon;
import ru.r2cloud.jradio.qarman.QarmanTest;
import ru.r2cloud.jradio.source.WavFileSource;

public class SilversatTest {

	private Il2pBeaconSource<Il2pBeacon> input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		input = new Il2pBeaconSource<>(new FskDemodulator(createSource(), 9600), 255, Il2pBeacon.class);
		assertTrue(input.hasNext());
		Il2pBeacon next = input.next();
		AssertJson.assertObjectsEqual("Silversat.json", next);
	}

	private static WavFileSource createSource() throws UnsupportedAudioFileException, IOException {
		return new WavFileSource(QarmanTest.class.getClassLoader().getResourceAsStream("silversat.wav"));
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
