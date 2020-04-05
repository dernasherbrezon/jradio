package ru.r2cloud.jradio.qarman;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.blocks.BinarySlicer;
import ru.r2cloud.jradio.blocks.ClockRecoveryMM;
import ru.r2cloud.jradio.blocks.LowPassFilter;
import ru.r2cloud.jradio.blocks.MultiplyConst;
import ru.r2cloud.jradio.blocks.Window;
import ru.r2cloud.jradio.source.WavFileSource;

public class QarmanTest {

	private Qarman input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		float gainMu = 0.175f * 3;
		WavFileSource source = new WavFileSource(QarmanTest.class.getClassLoader().getResourceAsStream("qarman.wav"));
		MultiplyConst mc = new MultiplyConst(source, 1);
		LowPassFilter lpf = new LowPassFilter(mc, 1.0, 10000, 600, Window.WIN_HAMMING, 6.76);
		ClockRecoveryMM clockRecovery = new ClockRecoveryMM(lpf, lpf.getContext().getSampleRate() / 9600, (float) (0.25 * gainMu * gainMu), 0.5f, gainMu, 0.05f);
		BinarySlicer bs = new BinarySlicer(clockRecovery);
		input = new Qarman(bs);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("QarmanBeacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
