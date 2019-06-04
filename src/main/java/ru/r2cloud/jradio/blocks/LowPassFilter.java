package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.CircularArray;
import ru.r2cloud.jradio.util.Metrics;

public class LowPassFilter implements FloatInput {

	private final MetricRegistry registry = Metrics.getRegistry();
	private final Meter samples;

	private final FloatInput source;
	private final FIRFilter filter;
	private final CircularArray array;
	private final int decimation;
	private final Context context;

	public LowPassFilter(FloatInput source, int decimation, double gain, double cutoffFrequency, double transitionWidth, Window windowType, double beta) {
		if (decimation < 1) {
			throw new IllegalArgumentException("decimation expected to be more or equal 1. got: " + decimation);
		}
		if (source.getContext().getChannels() != 1) {
			throw new IllegalArgumentException("not a float input: " + source.getContext().getChannels());
		}
		this.source = source;
		this.decimation = decimation;
		float[] taps = Firdes.lowPass(gain, source.getContext().getSampleRate(), cutoffFrequency, transitionWidth, windowType, beta);
		this.filter = new FIRFilter(taps);
		array = new CircularArray(taps.length);
		if (registry != null) {
			samples = registry.meter(LowPassFilter.class.getName());
		} else {
			samples = null;
		}
		context = new Context(source.getContext());
		context.setSampleRate(context.getSampleRate() / decimation);
		if (context.getTotalSamples() != null) {
			context.setTotalSamples(context.getTotalSamples() / decimation);
		}
	}

	public LowPassFilter(FloatInput source, double gain, double cutoffFrequency, double transitionWidth, Window windowType, double beta) {
		this(source, 1, gain, cutoffFrequency, transitionWidth, windowType, beta);
	}

	@Override
	public float readFloat() throws IOException {
		array.add(source.readFloat());
		float result = filter.filter(array);
		if (samples != null) {
			samples.mark();
		}
		for (int i = 0; i < decimation - 1; i++) {
			array.add(source.readFloat());
		}
		return result;
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
