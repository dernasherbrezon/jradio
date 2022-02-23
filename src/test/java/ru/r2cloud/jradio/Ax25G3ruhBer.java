package ru.r2cloud.jradio;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.r2cloud.jradio.blocks.ChannelModel;
import ru.r2cloud.jradio.blocks.FastNoiseSourceComplex;
import ru.r2cloud.jradio.blocks.GfskModulator;
import ru.r2cloud.jradio.blocks.HdlcTransmitter;
import ru.r2cloud.jradio.blocks.NrziEncode;
import ru.r2cloud.jradio.blocks.PackedToUnpacked;
import ru.r2cloud.jradio.blocks.Scrambler;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.SequentialByteSource;
import ru.r2cloud.jradio.source.SequentialSource;
import ru.r2cloud.jradio.source.TimeConstraintedSource;

public class Ax25G3ruhBer {

	private static int numberOfFrames = 100;
	private static int frameLength = 131;
	private static float sampleRate = 48000.0f;
	private static float devitation = 5000.0f;
	private static float baudRate = 9600.0f;
	private static long silenceLengthSamples = (long) sampleRate / 8;

	// length 20
	// checksum = true
	// 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 0.99 0.99 0.96 0.95 0.68 0.53 0.37 0.27 0.16 0.11 0.08 0.08 0.09 0.06 0.06 0.06 0.06 0.04 0.04 0.06
	// checksum = false
	// 0.9840625 0.97925 0.98975 0.9946875 0.9946875 0.9894375 0.984625 0.9946875 0.9946875 0.9946875 0.9946875 0.9879375 0.9946875 0.947625 0.905125 0.77825 0.715375 0.5909375 0.51475 0.2965 0.2109375 0.163375 0.1105625 0.0775625 0.0758125 0.0554375 0.0650625 0.0750625 0.0546875 0.0546875 0.0546875 0.0546875 0.0346875 0.0346875 0.0546875

	// length 131
	// checksum = true
	// 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 0.96 0.89 0.7 0.41 0.22 0.12 0.09 0.09 0.06 0.06 0.06 0.06 0.05 0.04 0.06
	// checksum = false
	// 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 0.9927862595419847 1.0 0.9751526717557252 0.8884446564885496 0.7935973282442748 0.6203721374045802 0.47770038167938933 0.31206106870229006 0.21568702290076336 0.1688740458015267 0.14463740458015267 0.09211832061068702 0.0908206106870229 0.07028625954198474 0.08005725190839695 0.08005725190839695 0.06 0.06 0.06 0.06 0.05 0.04 0.06

	public static void main(String[] args) throws Exception {
		StringBuilder b = new StringBuilder();
		for (float ebno = 0.0f; ebno < 17.01f; ebno += 0.5f) {
			b.append(" ").append(calculateBer(ebno));
		}
		System.out.println(b.toString());
	}

	private static double calculateBer(float ebno) throws IOException {
		float noiseVoltage = (float) (Math.sqrt(sampleRate / baudRate) / Math.pow(10, (ebno / 20)));

		Context silenceCtx = new Context();
		silenceCtx.setChannels(2);
		silenceCtx.setSampleRate(sampleRate);
		silenceCtx.setTotalSamples(silenceLengthSamples);

		HdlcTransmitter transmitter = new HdlcTransmitter();

		byte[] inputData = new byte[frameLength];
		for (int i = 0; i < inputData.length; i++) {
			inputData[i] = (byte) (i % 2);
		}

		List<FloatInput> inputs = new ArrayList<>();
		for (int i = 0; i < numberOfFrames; i++) {
			List<ByteInput> frameData = new ArrayList<>();
			byte[] preamble = new byte[3];
			Arrays.fill(preamble, (byte) 0xaa);
			frameData.add(new PackedToUnpacked(new ArrayByteInput(preamble), 1, Endianness.GR_MSB_FIRST));
			frameData.add(new NrziEncode(new Scrambler(new ArrayByteInput(transmitter.encode(inputData)), 0x21, 0x00, 16)));

			Context bb = new Context();
			bb.setSampleRate(baudRate);
			SequentialByteSource frames = new SequentialByteSource(frameData, bb);

			inputs.add(new TimeConstraintedSource(new FastNoiseSourceComplex(noiseVoltage, (i + 1) * 42, 1024 * 16), silenceCtx));
			inputs.add(new ChannelModel(new GfskModulator(frames, sampleRate / baudRate, (float) (2 * Math.PI * devitation / sampleRate), 1.0f), noiseVoltage, 0, 1.0f, null, (i + 1) * 42));
		}
		inputs.add(new TimeConstraintedSource(new FastNoiseSourceComplex(noiseVoltage, (numberOfFrames + 1) * 42, 1024 * 16), silenceCtx));

		Context ssCtx = new Context();
		ssCtx.setSampleRate(sampleRate);
		ssCtx.setChannels(2);
		FloatInput next = new SequentialSource(inputs, ssCtx);

		FskDemodulator demod = new FskDemodulator(next, (int) baudRate, devitation, 1, 2000, false);
		Ax25G3ruhBeaconSource<RawBeacon> source = new Ax25G3ruhBeaconSource<>(demod, RawBeacon.class, true);

		int totalBits = numberOfFrames * inputData.length * 8;
		int correctBits = 0;
		while (source.hasNext()) {
			RawBeacon cur = source.next();
			if (cur.getRawData().length != inputData.length) {
				continue;
			}
			PackedToUnpacked actual = new PackedToUnpacked(new ArrayByteInput(cur.getRawData()), 1, Endianness.GR_MSB_FIRST);
			PackedToUnpacked expected = new PackedToUnpacked(new ArrayByteInput(inputData), 1, Endianness.GR_MSB_FIRST);
			while (true) {
				try {
					if (actual.readByte() == expected.readByte()) {
						correctBits++;
					}
				} catch (EOFException e) {
					break;
				}
			}
		}

		source.close();
		return (double) (totalBits - correctBits) / totalBits;
	}

}
