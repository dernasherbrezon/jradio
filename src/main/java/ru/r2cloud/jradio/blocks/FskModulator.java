package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.ComplexFloatInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatValueSource;
import ru.r2cloud.jradio.util.NumericallyControlledOscillator;

public class FskModulator extends ComplexFloatInput implements FloatValueSource {

	private final ByteInput source;
	private final int sps;
	private final float[] complex = new float[2];
	private final Context context;
	private final NumericallyControlledOscillator nco;

	private final float markFreq;
	private final float spaceFreq;

	private float currentFreq;
	private int currentSymbol = 0;

	public FskModulator(ByteInput source, float samplesPerSymbol, float centerFreq, float shift) {
		this.source = source;
		this.nco = new NumericallyControlledOscillator(this, 1.0);
		this.sps = (int) (samplesPerSymbol + 0.5f);
		this.currentSymbol = sps;
		this.context = new Context(source.getContext());
		context.setSampleRate(context.getSampleRate() * sps);
		if (context.getTotalSamples() != null) {
			context.setTotalSamples(context.getTotalSamples() * sps);
		}
		context.setChannels(2);

		markFreq = (centerFreq + shift / 2) / context.getSampleRate();
		spaceFreq = (centerFreq - shift / 2) / context.getSampleRate();

	}

	@Override
	public float[] readComplex() throws IOException {
		if (currentSymbol >= sps) {
			if (source.readByte() == 1) {
				currentFreq = markFreq;
			} else {
				currentFreq = spaceFreq;
			}
			currentSymbol = 0;
		}
		nco.sincos(complex);
		currentSymbol++;
		return complex;
	}

	@Override
	public float getValue() {
		return currentFreq;
	}

	@Override
	public void close() throws IOException {
		source.close();
	}

	@Override
	public Context getContext() {
		return context;
	}

}
