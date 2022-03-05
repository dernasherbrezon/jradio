package ru.r2cloud.jradio;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.special.Erf;

import com.google.gson.Gson;

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
	private static float sampleRate = 48000.0f;
	private static float devitation = 5000.0f;
	private static float baudRate = 9600.0f;
	private static long silenceLengthSamples = (long) sampleRate / 4;

	public static void main(String[] args) throws Exception {
		calculateForPreamble();
		calculateForDifferentLengths();
	}

	private static void calculateForPreamble() throws IOException {
		Gson gson = new Gson();
		List<Ax25G3ruhBerStat> stats = new ArrayList<>();
		for (float ebno = 0.0f; ebno < 17.01f; ebno += 0.5f) {
			Ax25G3ruhBerStat cur = new Ax25G3ruhBerStat();
			cur.setEbno(ebno);
			if (ebno > 13.5f) {
				cur.setFskBer(0.0);
			} else {
				cur.setFskBer(0.5 * Erf.erfc(Math.sqrt(Math.pow(10.0, ebno / 10) / 2)));
			}
			cur.setPrepend0(calculateBer(ebno, 0, 20, true));
			cur.setPrepend3(calculateBer(ebno, 3, 20, true));
			cur.setPrepend6(calculateBer(ebno, 6, 20, true));
			cur.setPrepend9(calculateBer(ebno, 9, 20, true));
			stats.add(cur);
		}
		Ax25G3ruhBerStat[] array = stats.toArray(new Ax25G3ruhBerStat[0]);
		System.out.println(gson.toJson(array));
	}

	private static void calculateForDifferentLengths() throws IOException {
		Gson gson = new Gson();
		List<Ax25G3ruhBerStatLength> stats = new ArrayList<>();
		for (float ebno = 0.0f; ebno < 17.01f; ebno += 0.5f) {
			Ax25G3ruhBerStatLength cur = new Ax25G3ruhBerStatLength();
			cur.setEbno(ebno);
			if (ebno > 13.5f) {
				cur.setFskBer(0.0);
			} else {
				cur.setFskBer(0.5 * Erf.erfc(Math.sqrt(Math.pow(10.0, ebno / 10) / 2)));
			}
			cur.setAx25Byte20Checksum(calculateBer(ebno, 0, 20, true));
			cur.setAx25Byte20NoChecksum(calculateBer(ebno, 0, 20, false));
			cur.setAx25Byte131Checksum(calculateBer(ebno, 0, 131, true));
			cur.setAx25Byte131NoChecksum(calculateBer(ebno, 0, 131, false));
			stats.add(cur);
		}
		Ax25G3ruhBerStatLength[] array = stats.toArray(new Ax25G3ruhBerStatLength[0]);
		System.out.println(gson.toJson(array));
	}

	private static double calculateBer(float ebno, int prepend, int frameLength, boolean checksum) throws IOException {
		float noiseVoltage = (float) (Math.sqrt(sampleRate / baudRate) / Math.pow(10, (ebno / 20)));

		Context silenceCtx = new Context();
		silenceCtx.setChannels(2);
		silenceCtx.setSampleRate(sampleRate);
		silenceCtx.setTotalSamples(silenceLengthSamples);

		HdlcTransmitter transmitter = new HdlcTransmitter(prepend, 0);

		byte[] inputData = new byte[frameLength];
		for (int i = 0; i < inputData.length; i++) {
			inputData[i] = (byte) (i % 2);
		}

		List<FloatInput> inputs = new ArrayList<>();
		for (int i = 0; i < numberOfFrames; i++) {
			inputs.add(new TimeConstraintedSource(new FastNoiseSourceComplex(noiseVoltage, (i + 1) * 42, 1024 * 16), silenceCtx));
			NrziEncode source = new NrziEncode(new Scrambler(new ArrayByteInput(transmitter.encode(inputData)), 0x21, 0x00, 16));
			inputs.add(new ChannelModel(new GfskModulator(source, sampleRate / baudRate, (float) (2 * Math.PI * devitation / sampleRate), 1.0f), noiseVoltage, 0, 1.0f, null, (i + 1) * 42));
		}
		inputs.add(new TimeConstraintedSource(new FastNoiseSourceComplex(noiseVoltage, (numberOfFrames + 1) * 42, 1024 * 16), silenceCtx));

		Context ssCtx = new Context();
		ssCtx.setSampleRate(sampleRate);
		ssCtx.setChannels(2);
		FloatInput next = new SequentialSource(inputs, ssCtx);

		FskDemodulator demod = new FskDemodulator(next, (int) baudRate, devitation, 1, 2000, false);
		Ax25G3ruhBeaconSource<RawBeacon> source = new Ax25G3ruhBeaconSource<>(demod, RawBeacon.class, checksum);

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
