package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.CircularComplexArray;
import ru.r2cloud.jradio.util.Metrics;

public class RootRaisedCosineFilter implements FloatInput {

	private final MetricRegistry registry = Metrics.getRegistry();
	private final Meter samples;

	private final FloatInput source;
	private final FIRFilter filter;

	private final CircularComplexArray array;

	private boolean real = true;

	private final float[] currentComplex = new float[2];

	public RootRaisedCosineFilter(FloatInput source, float gain, float symbolRate, float alpha, int numTaps) {
		this.source = source;
		float[] taps = Firdes.rootRaisedCosine(gain, source.getContext().getSampleRate(), symbolRate, alpha, numTaps);
		this.filter = new FIRFilter(taps);
		array = new CircularComplexArray(taps.length);
		if (registry != null) {
			samples = registry.meter(RootRaisedCosineFilter.class.getName());
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
