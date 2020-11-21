package ru.r2cloud.jradio.kunspf;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.Ax100BeaconSource;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class KunsPfTest {

	private Ax100BeaconSource<?> input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(KunsPfTest.class.getClassLoader().getResourceAsStream("1kuns_pf.wav"));
		FskDemodulator demod = new FskDemodulator(source, 1200, 5000.0f, 5, 2000);
		// // 73 choosen as an estimated packet length in test.
		// // in real prod, it better to have max - 255
		input = new Ax100BeaconSource<>(demod, 70, KunsPfBeacon.class);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("KunsPfBeacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
