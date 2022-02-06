package ru.r2cloud.jradio.blocks;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.ArrayByteInput;
import ru.r2cloud.jradio.TestUtil;

public class FskModulatorTest {

	private FskModulator modulator;

	@Test
	public void test() throws Exception {
		// just some random data
		int[] data = new int[46];
		for (int i = 0; i < data.length; i++) {
			data[i] = i % 2;
		}
		data[20] = 1;
		data[21] = 1;

		float sampleRate = 8000.0f;
		float baudRate = 45.45f;
		float sps = sampleRate / baudRate;
		ArrayByteInput input = new ArrayByteInput(false, data);
		input.getContext().setSampleRate(sampleRate / sps);
		modulator = new FskModulator(input, sps, 1000, 170);
		TestUtil.assertFloatInput("rtty.cf32", modulator);
	}

	@After
	public void stop() throws Exception {
		if (modulator != null) {
			modulator.close();
		}
	}

}
