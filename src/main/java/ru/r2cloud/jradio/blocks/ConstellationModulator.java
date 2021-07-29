package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;

public class ConstellationModulator implements FloatInput {

	private final PolyphaseArbResamplerComplex filter;

	public ConstellationModulator(ByteInput input, Constellation constellation, float samplesPerSymbol, boolean differential, boolean preDifferentialMapping, float excessBw) {
		if (samplesPerSymbol < 2) {
			throw new IllegalArgumentException("samples per symbol should be >= 2. Got: " + samplesPerSymbol);
		}

		ByteInput next = new UnpackedToChunks(input, constellation.getBitsPerSymbol());
		if (preDifferentialMapping && constellation.isApplyPreDiffCode()) {
			next = new MapBlock(next, constellation.getPreDiffCode());
		}

		double arity = Math.pow(2, constellation.getBitsPerSymbol());
		if (differential) {
			next = new DifferentialEncoder(next, (int) arity);
		}

		ChunksToSymbolsComplex chunksToSymbols = new ChunksToSymbolsComplex(next, constellation.getConstell());

		int nfilts = 32;
		int ntaps = nfilts * 11 * (int) samplesPerSymbol;
		float[] rrcTaps = Firdes.rootRaisedCosine(nfilts, nfilts, 1.0f, excessBw, ntaps);

		this.filter = new PolyphaseArbResamplerComplex(chunksToSymbols, samplesPerSymbol, rrcTaps, nfilts);
	}

	@Override
	public float readFloat() throws IOException {
		return filter.readFloat();
	}

	@Override
	public void close() throws IOException {
		filter.close();
	}

	@Override
	public Context getContext() {
		return filter.getContext();
	}

}
