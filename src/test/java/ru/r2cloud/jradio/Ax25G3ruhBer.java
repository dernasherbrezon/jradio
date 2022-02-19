package ru.r2cloud.jradio;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.r2cloud.jradio.blocks.ChannelModel;
import ru.r2cloud.jradio.blocks.FastNoiseSourceComplex;
import ru.r2cloud.jradio.blocks.GfskModulator;
import ru.r2cloud.jradio.blocks.HdlcTransmitter;
import ru.r2cloud.jradio.blocks.NrziEncode;
import ru.r2cloud.jradio.blocks.PackedToUnpacked;
import ru.r2cloud.jradio.blocks.Scrambler;
import ru.r2cloud.jradio.demod.FskDemodulator;
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
	// 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0
	// 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0
	// checksum = false
	// 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 0.1375 0.075 0.075
	// 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0
	
	// length 131
	// checksum = true
	//  1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0
	// checksum = false
	//  1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 1.0 0.1717557251908397 0.13931297709923665 0.0916030534351145 0.05343511450381679 0.022900763358778626 0.01717557251908397 0.011450381679389313 0.0057251908396946565 0.0057251908396946565 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0

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
			ArrayByteInput input = new ArrayByteInput(transmitter.encode(inputData));
			input.getContext().setSampleRate(baudRate);

			inputs.add(new TimeConstraintedSource(new FastNoiseSourceComplex(noiseVoltage, 42, 1024 * 16), silenceCtx));
			inputs.add(new ChannelModel(new GfskModulator(new NrziEncode(new Scrambler(input, 0x21, 0x00, 16)), sampleRate / baudRate, (float) (2 * Math.PI * devitation / sampleRate), 1.0f), noiseVoltage, 0, 1.0f, null, 42));
		}
		inputs.add(new TimeConstraintedSource(new FastNoiseSourceComplex(noiseVoltage, 42, 1024 * 16), silenceCtx));

		Context ssCtx = new Context();
		ssCtx.setSampleRate(sampleRate);
		ssCtx.setChannels(2);
		SequentialSource sim = new SequentialSource(inputs, ssCtx);

		FskDemodulator demod = new FskDemodulator(sim, (int) (48000.0 / 5), 5000, 1, 2000, false);
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
