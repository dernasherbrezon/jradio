package ru.r2cloud.jradio.aausat4;

import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.blocks.ClockRecoveryMM;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.FixedLengthTagger;
import ru.r2cloud.jradio.blocks.FloatToChar;
import ru.r2cloud.jradio.blocks.LowPassFilter;
import ru.r2cloud.jradio.blocks.Multiply;
import ru.r2cloud.jradio.blocks.MultiplyConst;
import ru.r2cloud.jradio.blocks.QuadratureDemodulation;
import ru.r2cloud.jradio.blocks.Rail;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.blocks.Window;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.detection.GmskFrequencyCorrection;
import ru.r2cloud.jradio.detection.PeakDetection;
import ru.r2cloud.jradio.detection.PeakInterval;
import ru.r2cloud.jradio.detection.PeakValueSource;
import ru.r2cloud.jradio.source.SigSource;
import ru.r2cloud.jradio.source.WavFileSource;
import ru.r2cloud.jradio.source.Waveform;
import ru.r2cloud.jradio.util.RepeatedWavSource;
import ru.r2cloud.jradio.util.ThroughputStream;

public class AAUSAT4Test {

	private AAUSAT4 input;

	public static void main(String[] args) throws Exception {
		final ThroughputStream throughputStream = new ThroughputStream(new RepeatedWavSource("aausat-4.wav", 1));
		ClockRecoveryMM clockRecovery = new ClockRecoveryMM(throughputStream, 20.0f, (float) (0.25 * 0.175 * 0.175), 0.005f, 0.175f, 0.005f);
		Rail rail = new Rail(clockRecovery, -1.0f, 1.0f);
		FloatToChar f2char = new FloatToChar(rail, 127.0f);
		CorrelateAccessCodeTag correlateTag = new CorrelateAccessCodeTag(f2char, 10, "010011110101101000110100010000110101010101000010", true);
		AAUSAT4 input = new AAUSAT4(new TaggedStreamToPdu(new FixedLengthTagger(correlateTag, AAUSAT4.VITERBI_TAIL_SIZE + 8))); // 8 for fsm
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					System.out.println(throughputStream.getCurrent() + " bytes/s");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						return;
					}
				}
			}
		});
		t.start();
		while (input.hasNext()) {
			try {
				input.next();
			} catch (Exception e) {
				// do nothing
			}
		}
		input.close();
		t.interrupt();

		System.out.println("average " + throughputStream.getAverage() + " bytes/s");
		// average 17697814 bytes/s
	}

	@Test
	public void testPeakBasedFrequencyCorrection() throws Exception {
		List<PeakInterval> peaks;
		try (WavFileSource source = new WavFileSource(getStream())) {
			PeakDetection detection = new PeakDetection(100, -80.0f, 3);
			peaks = detection.process(source);
		}
		WavFileSource source = new WavFileSource(getStream());
		SigSource source2 = new SigSource(Waveform.COMPLEX, (long) source.getContext().getSampleRate(), new PeakValueSource(peaks, new GmskFrequencyCorrection(2400, 10)), 1.0f);
		Multiply mul = new Multiply(source, source2);
		QuadratureDemodulation qd = new QuadratureDemodulation(mul, 0.4f);
		LowPassFilter lpf = new LowPassFilter(qd, 1.0, 1500.0f, 100, Window.WIN_HAMMING, 6.76);
		MultiplyConst mc = new MultiplyConst(lpf, 1.0f);
		setupDemodulator(mc);
		assertTrue(input.hasNext());

	}

	private static InputStream getStream() throws Exception {
		return AAUSAT4Test.class.getClassLoader().getResourceAsStream("aausat-4-with-offset-2.wav");
	}

	@Test
	public void testSuccess() throws Exception {
		WavFileSource source = new WavFileSource(AAUSAT4Test.class.getClassLoader().getResourceAsStream("aausat-4.wav"));
		setupDemodulator(source);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("AAUSAT4Beacon.json", input.next());
	}

	private void setupDemodulator(FloatInput source) {
		FskDemodulator demod = new FskDemodulator(source, 2400);
		CorrelateAccessCodeTag correlateTag = new CorrelateAccessCodeTag(demod, 10, "010011110101101000110100010000110101010101000010", true);
		input = new AAUSAT4(new TaggedStreamToPdu(new FixedLengthTagger(correlateTag, AAUSAT4.VITERBI_TAIL_SIZE + 8))); // 8 for fsm
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
