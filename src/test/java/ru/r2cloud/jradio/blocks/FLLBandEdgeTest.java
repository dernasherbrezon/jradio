package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import java.io.EOFException;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.source.InputStreamSource;
import ru.r2cloud.jradio.source.WavFileSource;

public class FLLBandEdgeTest {

	private FLLBandEdge source;

	@Test
	public void test() throws Exception {
		int filterSize = 100;
		WavFileSource wav = new WavFileSource(LowPassFilterTest.class.getClassLoader().getResourceAsStream("stereo.wav"));
		source = new FLLBandEdge(wav, 8.0f, 0.35f, filterSize, 0.01f);
		try (InputStreamSource is = new InputStreamSource(CostasLoopTest.class.getClassLoader().getResourceAsStream("fll.bin"))) {
			int index = 0;
			//skip first filterSize output. FLL has history of filterSize + 1, which is causing zero output 
			for (int i = 0; i < filterSize; i++) {
				is.readFloat();
				is.readFloat();
			}
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
