package ru.r2cloud.jradio.blocks;

import java.io.EOFException;
import java.io.IOException;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.CircularComplexArray;
import ru.r2cloud.jradio.util.MathUtils;
import ru.r2cloud.jradio.util.Metrics;

public class FrequencyXlatingFIRFilter implements FloatInput {

	private final MetricRegistry registry = Metrics.getRegistry();
	private final Meter samples;

	private final float[] currentComplex = new float[2];

	private final FloatInput source;
	private final CircularComplexArray array;
	private final ComplexFIRFilter filter;
	private final Rotator rotator;
	private final int decimation;
	private final Context context;

	private boolean real = true;
	private EOFException endOfStream = null;

	public FrequencyXlatingFIRFilter(FloatInput source, float[] taps, int decimation, double centerFreq) {
		if (decimation < 1) {
			throw new IllegalArgumentException("decimation expected to be more or equal 1. got: " + decimation);
		}
		if (source.getContext().getChannels() != 2) {
			throw new IllegalArgumentException("not a complex input: " + source.getContext().getChannels());
		}
		this.source = source;
		this.decimation = decimation;
		float[] tapsReal = new float[taps.length];
		float[] tapsImg = new float[taps.length];
		float fwT0 = (float) (2 * Math.PI * centerFreq / source.getContext().getSampleRate());
		for (int i = 0; i < taps.length; i++) {
			float[] complex = MathUtils.exp(0, i * fwT0);
			float[] curResult = new float[2];
			MathUtils.multiply(curResult, taps[i], 0, complex[0], complex[1]);
			tapsReal[i] = curResult[0];
			tapsImg[i] = curResult[1];
		}
		this.filter = new ComplexFIRFilter(tapsReal, tapsImg);
		array = new CircularComplexArray(taps.length);
		rotator = new Rotator(new float[] { 1.0f, 0.0f }, MathUtils.exp(0.0f, -fwT0 * decimation));
		if (registry != null) {
			samples = registry.meter(FrequencyXlatingFIRFilter.class.getName());
		} else {
			samples = null;
		}
		context = new Context(source.getContext());
		context.setSampleRate(context.getSampleRate() / decimation);
		context.setTotalSamples(context.getTotalSamples() / decimation);
	}

	@Override
	public float readFloat() throws IOException {
		float result;
		if (real) {
			if (endOfStream != null) {
				throw endOfStream;
			}
			array.add(source.readFloat(), source.readFloat());
			filter.filterComplex(currentComplex, array);
			rotator.rotate(currentComplex, currentComplex);
			if (samples != null) {
				samples.mark();
			}
			for (int i = 0; i < decimation - 1; i++) {
				try {
					array.add(source.readFloat(), source.readFloat());
				} catch (EOFException e) {
					endOfStream = e;
				}
			}
			result = currentComplex[0];
		} else {
			result = currentComplex[1];
		}
		real = !real;
		return result;
	}

	@Override
	public Context getContext() {
		return context;
	}

	@Override
	public void close() throws IOException {
		source.close();
	}
}
