package ru.r2cloud.jradio.blocks;

import java.io.IOException;
import java.util.Arrays;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.Endianness;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.MathUtils;

public class GfskModulator implements FloatInput {

	private final FloatInput input;

	public GfskModulator(ByteInput source, float samplesPerSymbol, float sensitivity, float bt) {
		if (samplesPerSymbol < 2) {
			throw new IllegalArgumentException("samples per symbol should be >= 2. Got: " + samplesPerSymbol);
		}
		int ntaps = (int) (4 * samplesPerSymbol);
		ByteInput next = new PackedToUnpacked(source, 1, Endianness.GR_MSB_FIRST);
		FloatInput fnext = new ChunksToSymbols(next, new float[] { -1, 1 });
		float[] gaussianTaps = Firdes.gaussian(1.0f, samplesPerSymbol, bt, ntaps);
		float[] sqwave = new float[(int) samplesPerSymbol];
		Arrays.fill(sqwave, 1);
		float[] taps = MathUtils.convolve(gaussianTaps, sqwave);
		fnext = new InterpFIRFilter(fnext, (int) samplesPerSymbol, taps);
		fnext = new FrequencyModulator(fnext, sensitivity);
		this.input = new MultiplyConst(fnext, 0.999f);
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

	@Override
	public float readFloat() throws IOException {
		return input.readFloat();
	}

	@Override
	public Context getContext() {
		return input.getContext();
	}

}
