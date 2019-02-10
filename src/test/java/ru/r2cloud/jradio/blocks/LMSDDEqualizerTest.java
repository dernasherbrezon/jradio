package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import java.io.EOFException;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.source.InputStreamSource;
import ru.r2cloud.jradio.source.WavFileSource;

public class LMSDDEqualizerTest {

	private LMSDDEqualizer source;

	@Test
	public void test() throws Exception {
		WavFileSource wav = new WavFileSource(LMSDDEqualizerTest.class.getClassLoader().getResourceAsStream("stereo.wav"));
		Constellation constellation = new Constellation(new float[] {-1.0f, 0.0f, 1.0f, 0.0f}, new int[] {0, 1}, 2, 1);
		source = new LMSDDEqualizer(wav, 2, 0.05f, 2, constellation);
		try (InputStreamSource is = new InputStreamSource(LMSDDEqualizerTest.class.getClassLoader().getResourceAsStream("lmsdd.bin"))) {
			int index = 0;
			while (true) {
				float expected = is.readFloat();
				float actual = source.readFloat();
				assertEquals("failed at index: " + index, expected, actual, 0.0001f);
				index++;
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
