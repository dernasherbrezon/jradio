package ru.r2cloud.jradio;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.blocks.ClockRecoveryMM;
import ru.r2cloud.jradio.source.WavFileSource;

public class ClockRecoveryMMTest {

	private ClockRecoveryMM source;

	@Test
	public void test() throws Exception {
		source = new ClockRecoveryMM(new WavFileSource(WavFileSourceTest.class.getClassLoader().getResourceAsStream("aausat-4.wav")), 20.0f, (float) (0.25 * 0.175 * 0.175), 0.005f, 0.175f, 0.005f);
		try (InputStream is = WavFileSourceTest.class.getClassLoader().getResourceAsStream("ClockRecoveryMM.bin")) {
			Float expected = null;
			while ((expected = WavFileSourceTest.readFloat(is)) != null) {
				float actual = source.readFloat();
				assertEquals(expected, actual, 0.0);
			}
		}
	}

	@After
	public void stop() throws Exception {
		if (source != null) {
			source.close();
		}
	}

}
