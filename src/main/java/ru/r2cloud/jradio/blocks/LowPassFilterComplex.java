package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.CircularComplexArray;
import ru.r2cloud.jradio.util.Metrics;

public class LowPassFilterComplex implements FloatInput {

	private final MetricRegistry registry = Metrics.getRegistry();
	private final Meter samples;
	private final float[] currentComplex = new float[2];

	private final FloatInput source;
	private final FIRFilter filter;
	private final CircularComplexArray array;

	private boolean real = true;

	public LowPassFilterComplex(FloatInput source, double gain, double cutoffFrequency, double transitionWidth, Window windowType, double beta) {
		if (source.getContext().getChannels() != 2) {
			throw new IllegalArgumentException("not a complex input: " + source.getContext().getChannels());
		}
		this.source = source;
		float[] taps = Firdes.lowPass(gain, source.getContext().getSampleRate(), cutoffFrequency, transitionWidth, windowType, beta);
		this.filter = new FIRFilter(taps);
		array = new CircularComplexArray(taps.length);
		if (registry != null) {
			samples = registry.meter(LowPassFilterComplex.class.getName());
		} else {
			samples = null;
		}
	}

	@Override
	public float readFloat() throws IOException {
		float result;
		if (real) {
			array.add(source.readFloat(), source.readFloat());
			filter.filterComplex(currentComplex, array);
			if (samples != null) {
				samples.mark();
			}
			result = currentComplex[0];
		} else {
			result = currentComplex[1];
		}
		real = !real;
		return result;
	}

	@Override
	public void close() throws IOException {
		source.close();
	}

	@Override
	public Context getContext() {
		return source.getContext();
	}
}
