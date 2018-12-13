package ru.r2cloud.jradio.gmsk;

import static org.junit.Assert.assertEquals;

import java.io.EOFException;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.blocks.ComplexConjugate;
import ru.r2cloud.jradio.blocks.FloatToComplex;
import ru.r2cloud.jradio.blocks.Multiply;
import ru.r2cloud.jradio.source.InputStreamSource;
import ru.r2cloud.jradio.source.SigSource;
import ru.r2cloud.jradio.source.WavFileSource;
import ru.r2cloud.jradio.source.Waveform;

public class AfGmskTest {

	private ComplexConjugate source;

	// af gmsk has 2 frequencies more than 0. shift them to be more 0 and less 0 for binary slicer
	// sigsource required for 1channel recording
	@Test
	public void testShiftFrequency() throws Exception {
		WavFileSource wavFile = new WavFileSource(AfGmskTest.class.getClassLoader().getResourceAsStream("au02.wav"));
		FloatToComplex f2c = new FloatToComplex(wavFile);
		SigSource sigSource = new SigSource(Waveform.COMPLEX, (long) wavFile.getContext().getSampleRate(), -3600.0f, 1.0);
		Multiply multiply = new Multiply(f2c, sigSource);
		source = new ComplexConjugate(multiply);
		try (InputStreamSource is = new InputStreamSource(AfGmskTest.class.getClassLoader().getResourceAsStream("afgmsk.bin"))) {
			while (true) {
				float expected = is.readFloat();
				float actual = source.readFloat();
				assertEquals(expected, actual, 0.0001f);
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
