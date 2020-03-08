package ru.r2cloud.jradio.smogp;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.blocks.ClockRecoveryMM;
import ru.r2cloud.jradio.blocks.FloatToChar;
import ru.r2cloud.jradio.blocks.LowPassFilter;
import ru.r2cloud.jradio.blocks.MultiplyConst;
import ru.r2cloud.jradio.blocks.Rail;
import ru.r2cloud.jradio.blocks.Window;
import ru.r2cloud.jradio.source.WavFileSource;

public class SmogPShortTest {

	private SmogPShort input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		float gainMu = 0.175f * 3;
		WavFileSource source = new WavFileSource(SmogPShortTest.class.getClassLoader().getResourceAsStream("smog_p_short.wav"));
		MultiplyConst mc = new MultiplyConst(source, 10.0f);
		LowPassFilter lpf = new LowPassFilter(mc, 5, 1.0, 700, 50, Window.WIN_HAMMING, 6.76);
		ClockRecoveryMM clockRecovery = new ClockRecoveryMM(lpf, lpf.getContext().getSampleRate() / 1250, (float) (0.25 * gainMu * gainMu), 0.5f, gainMu, 0.005f);
		Rail rail = new Rail(clockRecovery, -1.0f, 1.0f);
		FloatToChar f2char = new FloatToChar(rail, 127.0f);
		input = new SmogPShort(f2char);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("SmogPShortBeacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
