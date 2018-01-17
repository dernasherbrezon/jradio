package ru.r2cloud.jradio;

import static org.junit.Assert.assertEquals;

import java.io.EOFException;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.blocks.ClockRecoveryMM;
import ru.r2cloud.jradio.source.InputStreamSource;
import ru.r2cloud.jradio.source.WavFileSource;
import ru.r2cloud.jradio.source.WavFileSourceTest;

public class ClockRecoveryMMTest {

	private ClockRecoveryMM source;

	@Test
	public void test() throws Exception {
		source = new ClockRecoveryMM(new WavFileSource(WavFileSourceTest.class.getClassLoader().getResourceAsStream("aausat-4.wav")), 20.0f, (float) (0.25 * 0.175 * 0.175), 0.005f, 0.175f, 0.005f);
		try (InputStreamSource is = new InputStreamSource(WavFileSourceTest.class.getClassLoader().getResourceAsStream("ClockRecoveryMM.bin"))) {
			while (true) {
				float expected = is.readFloat();
				float actual = source.readFloat();
				assertEquals(expected, actual, 0.0);
			}
		} catch (EOFException e) {
			// do nothing
		}
	}

	@After
	public void stop() throws Exception {
		if (source != null) {
			source.close();
		}
	}

}
