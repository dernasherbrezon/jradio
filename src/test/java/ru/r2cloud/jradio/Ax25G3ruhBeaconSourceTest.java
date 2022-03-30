package ru.r2cloud.jradio;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ru.r2cloud.jradio.blocks.ChannelModel;
import ru.r2cloud.jradio.blocks.FastNoiseSourceComplex;
import ru.r2cloud.jradio.blocks.GfskModulator;
import ru.r2cloud.jradio.blocks.HdlcTransmitter;
import ru.r2cloud.jradio.blocks.NrziEncode;
import ru.r2cloud.jradio.blocks.Scrambler;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.SequentialSource;
import ru.r2cloud.jradio.source.TimeConstraintedSource;

public class Ax25G3ruhBeaconSourceTest {

	@Test
	public void testCorrelateSyncword() throws Exception {
		byte[] inputData = new byte[20];
		for (int i = 0; i < inputData.length; i++) {
			inputData[i] = (byte) (i % 2);
		}

		float sampleRate = 48000.0f;
		float sps = 5.0f;
		float deviation = 5000.0f;

		float ebno = 17.0f;
		float noiseVoltage = (float) (Math.sqrt(sps) / Math.pow(10, (ebno / 20)));
		long noiseSeed = 42;

		HdlcTransmitter transmitter = new HdlcTransmitter(12, 10);

		Context silenceCtx = new Context();
		silenceCtx.setChannels(2);
		silenceCtx.setSampleRate(sampleRate);
		silenceCtx.setTotalSamples((long) sampleRate);

		List<FloatInput> inputs = new ArrayList<>();
		inputs.add(new TimeConstraintedSource(new FastNoiseSourceComplex(noiseVoltage, noiseSeed, 1024 * 16), silenceCtx));
		inputs.add(new ChannelModel(new GfskModulator(new NrziEncode(new Scrambler(new ArrayByteInput(transmitter.encode(inputData)), 0x21, 0x00, 16)), sps, (float) (2 * Math.PI * deviation / sampleRate), 1.0f), noiseVoltage, 0, 1.0f, null, noiseSeed));
		inputs.add(new TimeConstraintedSource(new FastNoiseSourceComplex(noiseVoltage, noiseSeed, 1024 * 16), silenceCtx));

		Context ssCtx = new Context();
		ssCtx.setSampleRate(sampleRate);
		ssCtx.setChannels(2);
		FloatInput next = new SequentialSource(inputs, ssCtx);

		FskDemodulator demod = new FskDemodulator(next, (int) (sampleRate / sps), deviation, 1, 2000, false);
		try (Ax25G3ruhBeaconSource<RawBeacon> source = new Ax25G3ruhBeaconSource<>(demod, RawBeacon.class, true, new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 }, "11110111", 53792)) {
			assertTrue(source.hasNext());
			assertNotNull(source.next());
		}
	}

}
