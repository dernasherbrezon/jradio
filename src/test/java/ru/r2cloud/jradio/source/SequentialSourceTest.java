package ru.r2cloud.jradio.source;

import static org.junit.Assert.assertEquals;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.ArrayByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.blocks.ChannelModel;
import ru.r2cloud.jradio.blocks.FastNoiseSourceComplex;
import ru.r2cloud.jradio.blocks.GfskModulator;

public class SequentialSourceTest {

	private SequentialSource sim;

	@Test
	public void testSuccess() throws Exception {
		float sampleRate = 48000.0f;
		float sps = 5.0f;
		float devitation = 5000.0f;
		float ebno = 10.0f;
		float noiseVoltage = (float) (Math.sqrt(sps) / Math.pow(10, (ebno / 20)));
		long noiseSeed = 42;

		int[] inputData = new int[100];
		for (int i = 0; i < inputData.length; i++) {
			inputData[i] = i % 2;
		}

		ArrayByteInput input = new ArrayByteInput(false, inputData);
		input.getContext().setSampleRate(sampleRate / sps);

		Context silenceCtx = new Context();
		silenceCtx.setChannels(2);
		silenceCtx.setSampleRate(sampleRate);
		silenceCtx.setTotalSamples((long) sampleRate / 8);

		List<FloatInput> inputs = new ArrayList<>();
		inputs.add(new TimeConstraintedSource(new FastNoiseSourceComplex(noiseVoltage, noiseSeed, 1024 * 16), silenceCtx));
		inputs.add(new ChannelModel(new GfskModulator(input, sps, (float) (2 * Math.PI * devitation / sampleRate), 1.0f), noiseVoltage, 0, 1.0f, null, noiseSeed));
		inputs.add(new TimeConstraintedSource(new FastNoiseSourceComplex(noiseVoltage, noiseSeed, 1024 * 16), silenceCtx));

		sim = new SequentialSource(inputs, new Context());
		try (InputStreamSource is = new InputStreamSource(SequentialSourceTest.class.getClassLoader().getResourceAsStream("sequentialSource.cf32"))) {
			while (true) {
				assertEquals(is.readFloat(), sim.readFloat(), 0.0);
			}
		} catch (EOFException e) {
			// do nothing
		}
	}

	@After
	public void stop() throws Exception {
		if (sim != null) {
			sim.close();
		}
	}

}
