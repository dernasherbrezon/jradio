package ru.r2cloud.jradio.detection;

import static org.junit.Assert.assertEquals;

import java.io.EOFException;
import java.util.List;

import org.junit.Test;

import ru.r2cloud.jradio.blocks.Multiply;
import ru.r2cloud.jradio.source.InputStreamSource;
import ru.r2cloud.jradio.source.SigSource;
import ru.r2cloud.jradio.source.WavFileSource;
import ru.r2cloud.jradio.source.Waveform;

public class GmskFrequencyCorrectionTest {

	@Test
	public void testSuccess() throws Exception {
		List<PeakInterval> peaks;
		try (WavFileSource source = new WavFileSource(GmskFrequencyCorrectionTest.class.getClassLoader().getResourceAsStream("aausat-4-with-offset.wav"))) {
			PeakDetection detection = new PeakDetection(100);
			peaks = detection.process(source);
		}
		
		WavFileSource source = new WavFileSource(GmskFrequencyCorrectionTest.class.getClassLoader().getResourceAsStream("aausat-4-with-offset.wav"));
		SigSource source2 = new SigSource(Waveform.COMPLEX, (long) source.getContext().getSampleRate(), new PeakValueSource(peaks, new GmskFrequencyCorrection()), 1.0f);
		try (Multiply mul = new Multiply(source, source2, true); InputStreamSource is = new InputStreamSource(GmskFrequencyCorrectionTest.class.getClassLoader().getResourceAsStream("expectedAausat4Correction.bin"))) {
			while (true) {
				float expected = is.readFloat();
				float actual = mul.readFloat();
				assertEquals(expected, actual, 0.0001f);
			}
		} catch (EOFException e) {
			// do nothing
		}
	}
	
}
