package ru.r2cloud.jradio.source;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.ArrayByteInput;
import ru.r2cloud.jradio.Ax25G3ruhBeaconSource;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.RawBeacon;
import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.blocks.ChannelModel;
import ru.r2cloud.jradio.blocks.FastNoiseSourceComplex;
import ru.r2cloud.jradio.blocks.GfskModulator;
import ru.r2cloud.jradio.blocks.HdlcTransmitter;
import ru.r2cloud.jradio.blocks.NrziEncode;
import ru.r2cloud.jradio.blocks.Scrambler;
import ru.r2cloud.jradio.demod.FskDemodulator;

public class SequentialSourceTest {

	private SequentialSource sim;

	@Test
	public void testSuccess() throws Exception {
		byte[] inputData = new byte[20];
		for (int i = 0; i < inputData.length; i++) {
			inputData[i] = (byte) (i % 2);
		}

		sim = createSequentialSource(48000.0f, 5.0f, 5000.0f, inputData);
		TestUtil.assertFloatInput("sequentialSource.cf32", sim);
		sim.close();

		sim = createSequentialSource(48000.0f, 5.0f, 5000.0f, inputData);
		FskDemodulator demod = new FskDemodulator(sim, (int) (48000.0 / 5), 5000, 1, 2000, false);
		Ax25G3ruhBeaconSource<RawBeacon> source = new Ax25G3ruhBeaconSource<>(demod, RawBeacon.class);
		assertTrue(source.hasNext());
		RawBeacon beacon = source.next();
		assertArrayEquals(inputData, beacon.getRawData());
		source.close();
	}

	private static SequentialSource createSequentialSource(float sampleRate, float sps, float devitation, byte[] inputData) {
		float ebno = 12.0f;
		float noiseVoltage = (float) (Math.sqrt(sps) / Math.pow(10, (ebno / 20)));
		long noiseSeed = 42;

		HdlcTransmitter transmitter = new HdlcTransmitter();
		ArrayByteInput input = new ArrayByteInput(transmitter.encode(inputData));
		input.getContext().setSampleRate(sampleRate / sps);

		Context silenceCtx = new Context();
		silenceCtx.setChannels(2);
		silenceCtx.setSampleRate(sampleRate);
		silenceCtx.setTotalSamples((long) sampleRate / 8);

		List<FloatInput> inputs = new ArrayList<>();
		inputs.add(new TimeConstraintedSource(new FastNoiseSourceComplex(noiseVoltage, noiseSeed, 1024 * 16), silenceCtx));
		inputs.add(new ChannelModel(new GfskModulator(new NrziEncode(new Scrambler(input, 0x21, 0x00, 16)), sps, (float) (2 * Math.PI * devitation / sampleRate), 1.0f), noiseVoltage, 0, 1.0f, null, noiseSeed));
		inputs.add(new TimeConstraintedSource(new FastNoiseSourceComplex(noiseVoltage, noiseSeed, 1024 * 16), silenceCtx));

		Context ssCtx = new Context();
		ssCtx.setSampleRate(sampleRate);
		ssCtx.setChannels(2);
		return new SequentialSource(inputs, ssCtx);
	}

	@After
	public void stop() throws Exception {
		if (sim != null) {
			sim.close();
		}
	}

}
